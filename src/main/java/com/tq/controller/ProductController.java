package com.tq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tq.service.CategoryService;
import com.tq.service.ProductService;
import com.tq.util.JavaUtils;

@Controller
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	JavaUtils javaUtils;
	@GetMapping ("/SearchProducts")
	public String searchProductPage(Model model, @RequestParam("search") String txtSearch) {
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("products", productService.findByName(txtSearch));
		model.addAttribute("user", javaUtils.getAccountAuthentication());
		return "ViewAll";
	}
}
