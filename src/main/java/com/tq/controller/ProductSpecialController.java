package com.tq.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tq.entities.ProductEntity;
import com.tq.service.CategoryService;
import com.tq.service.ProductService;
import com.tq.util.JavaUtils;

@Controller
public class ProductSpecialController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	JavaUtils javaUtilsService;

	@RequestMapping(value = "SaleOff/{page}", method = RequestMethod.GET)
	public String ViewAllProductSale(Model model, @PathVariable("page") int page) {
		model.addAttribute("categories", categoryService.getAll());

		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		for (ProductEntity entity : productService.GetAllProduct()) {
			if (entity.CheckExistPromotion() >= 0) {
				listProducts.add(entity);
			}
		}
		Collections.sort(listProducts, new Comparator<ProductEntity>() {

			@Override
			public int compare(ProductEntity o1, ProductEntity o2) {
				if (o1.getPrice() > o2.getPrice()) {
					return 0;
				}
				return 1;
			}
		});
		double numberPage = Math.ceil(listProducts.size() / 12.0);
		List<ProductEntity> listReturn = new ArrayList<ProductEntity>();
		try {
			if (!CollectionUtils.isEmpty(listProducts)) {
				if (page * 12 < listProducts.size()) {
					listReturn = listProducts.subList((page - 1) * 12, page * 12);
				} else {
					listReturn = listProducts.subList((page - 1) * 12, listProducts.size() - 1);

				}
			}
			model.addAttribute("products", listReturn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Error";
		}

		model.addAttribute("current", page);
		model.addAttribute("pages", numberPage);
		model.addAttribute("action", "SaleOff");
		/* model.addAttribute("products", entities); */

		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		return "ViewAll";
	}
}
