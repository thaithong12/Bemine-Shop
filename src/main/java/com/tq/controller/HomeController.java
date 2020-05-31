
package com.tq.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tq.entities.AccountEntity;
import com.tq.entities.ProductCategory;
import com.tq.entities.ProductDetails;
import com.tq.entities.ProductEntity;
import com.tq.service.AccountService;
import com.tq.service.CategoryService;
import com.tq.service.ColorService;
import com.tq.service.OrderDetailService;
import com.tq.service.ProductDetailService;
import com.tq.service.ProductService;
import com.tq.service.SizeService;
import com.tq.util.JavaUtils;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private ColorService colorService;

	@Autowired
	ProductDetailService productDetailService;

	@Autowired
	AccountService accountService;

	@Autowired
	JavaUtils javaUtilsService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String viewHome(Model model) {

		model.addAttribute("categories", categoryService.getAll());
		List<ProductEntity> hotProducts = orderDetailService.getHotProduct();
		List<ProductEntity> newProducts = productService.getNewProducts();
		if (hotProducts.size() > 0 && newProducts.size() > 0) {
			model.addAttribute("hotproducts1", hotProducts.subList(0, 4));
			model.addAttribute("hotproducts2", hotProducts.subList(4, 8));
			model.addAttribute("newproducts1", newProducts.subList(0, 4));
			model.addAttribute("newproducts2", newProducts.subList(4, 8));
		}

		model.addAttribute("user", javaUtilsService.getAccountAuthentication());

		return "home";
	}

	/*
	 * public boolean CheckAuthenticationInCurrent() { if
	 * (SecurityContextHolder.getContext().getAuthentication() != null &&
	 * SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
	 * !(SecurityContextHolder.getContext().getAuthentication() instanceof
	 * AnonymousAuthenticationToken)) { return true; } else { return false; } }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLogin(Model model, @RequestParam(value = "isError", required = false) boolean isError) {
		if (isError == true) {
			model.addAttribute("message", "Login fail ! Email or Password is incorrect !");
		}
		if (javaUtilsService.CheckAuthenticationInCurrent()) {
			return "redirect:/";
		}
		model.addAttribute("account", new AccountEntity());
		return "login";
	}

	@RequestMapping(value = "/ViewAll/{page}", method = RequestMethod.GET)
	public String viewAll(Model model, @PathVariable("page") Integer page) {
		model.addAttribute("categories", categoryService.getAll());
		double numberPage = Math.ceil(productService.GetAllProduct().size() / 12.0);
		PageRequest pageRequest = new PageRequest(page - 1, 12);
		Page<ProductEntity> page2 = productService.PagingProduct(pageRequest);
		List<ProductEntity> entities = page2.getContent();

		model.addAttribute("current", page);
		model.addAttribute("pages", numberPage);
		model.addAttribute("action", "ViewAll");
		model.addAttribute("products", entities);
		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		return "ViewAll";
	}

	@RequestMapping(value = "CategoryProduct/{categoryId}/{page}")
	public String viewAllByCategory(Model model, @PathVariable("categoryId") int categoryId,
			@PathVariable("page") int page) {
		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		ProductCategory productCategory = categoryService.findByCategoryId(categoryId);
		List<ProductEntity> productEntities = productCategory.getProductEntities();
		double numberPage = Math.ceil(productEntities.size() / 12.0);
		PageRequest pageRequest = new PageRequest(page - 1, 12);

		Page<ProductEntity> page2 = productService.PagingCategoryProduct(productCategory, pageRequest);
		List<ProductEntity> entities = page2.getContent();
		model.addAttribute("current", page);
		model.addAttribute("pages", numberPage);
		model.addAttribute("products", entities);
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("action", categoryId + "");
		return "ViewAll";
	}

	@RequestMapping(value = "/ViewDetail/{id}")
	public String ViewDetail(Model model, @PathVariable("id") int id) {
		System.out.println(id);
		ProductEntity entity = productService.findOne(id);
		if (entity.getProductId() > 0) {
			model.addAttribute("product", entity);
			model.addAttribute("status", checkAvaiable(id));
			model.addAttribute("categories", categoryService.getAll());
			model.addAttribute("sizes", sizeService.getSizeByProductDetail(entity.getProductDetails()));
			model.addAttribute("colors", colorService.getColorByProductDetail(entity.getProductDetails()));
			List<ProductEntity> entities = new ArrayList<ProductEntity>();

			List<ProductDetails> details = productDetailService.getSepecialDealProduct();
			if (!CollectionUtils.isEmpty(details)) {
				for (ProductDetails dt : details) {
					entities.add(dt.getProductEntity());
				}
			}
			model.addAttribute("special", entities);
			model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		}
		return "ViewDetail";
	}

	public String checkAvaiable(int id) {

		int check = productDetailService.getQuantity(id);
		if (check > 0) {
			return "In Stock";
		} else
			return "Out Stock";

	}

	@RequestMapping(value = "/TypeProduct/{gender}/{page}")
	public String ViewTypeProductMale(Model model, @PathVariable("gender") String gender,
			@PathVariable("page") int page) {
		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		double numberPage = Math.ceil(productService.getListByType(gender).size() / 12.0);
		PageRequest pageRequest = new PageRequest(page - 1, 12);
		Page<ProductEntity> page2 = productService.getListByTypePaging(gender, pageRequest);
		List<ProductEntity> entities = page2.getContent();
		model.addAttribute("current", page);
		model.addAttribute("pages", numberPage);
		model.addAttribute("products", entities);
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("action", "TypeProduct/" + gender);
		return "ViewAll";
	}
}
