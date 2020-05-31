
package com.tq.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.tq.entities.AccountEntity;
import com.tq.entities.Customer;
import com.tq.entities.Favourite;
import com.tq.entities.OrderDetails;
import com.tq.entities.Orders;
import com.tq.service.AccountService;
import com.tq.service.CustomerService;
import com.tq.service.FavouriteService;
import com.tq.service.OrderDetailService;
import com.tq.service.OrderService;
import com.tq.util.JavaUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/home")
	public String viewHome(Model model, Principal principal) {
		AccountEntity user = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "user/home";
	}

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@Autowired
	JavaUtils javaUtils;

	@RequestMapping("/ViewOrder/{page}")
	public String viewOrderPage(Principal principal, Model model, @PathVariable("page") int page) {
		AccountEntity user = javaUtils.getAccountAuthentication();
		if(user == null) {
			return "login";
		}
		model.addAttribute("user", user);
		List<Customer> customers = customerService.findByAccountId(user.getId());

		List<Orders> orders = new ArrayList<Orders>();
		List<Orders> orderReturn = new ArrayList<Orders>();
		if (!CollectionUtils.isEmpty(customers)) {
			for (Customer customer : customers) {
				if (!CollectionUtils.isEmpty(customer.getOrders())) {
					orders.addAll(customer.getOrders());
				}
			}

			Collections.sort(orders, new Comparator<Orders>() {
				@Override
				public int compare(Orders order1, Orders order2) {
					return order2.getDateCreate().compareTo(order1.getDateCreate());
				}
			});
			if (orders.size() > 0) {

				int pages = (int) Math.ceil(orders.size() / 7.0);
				model.addAttribute("pages", pages);

				if (page * 7 <= orders.size()) {
					orderReturn = orders.subList((page - 1) * 7, page * 7);
				} else {
					orderReturn = orders.subList((page - 1) * 7, orders.size() - 1);
				}

			} else {
				return "redirect:user/home";
			}
			model.addAttribute("current", page);
			model.addAttribute("action", "user/ViewOrder");
			model.addAttribute("orders", orderReturn);
		}else {
			return "user/home";
		}
		return "user/ManageOrder";
	}

	@Autowired
	OrderDetailService orderDetailService;

	@RequestMapping("/ViewOrderDetail/{orderId}")
	public String ViewOrderDetailPage(Principal principal, Model model, @PathVariable("orderId") int orderId) {
		AccountEntity user = javaUtils.getAccountAuthentication();
		if(user == null) {
			return "login";
		}
		model.addAttribute("user", user);
		List<OrderDetails> details = orderDetailService.getListOrderDetailByOrderId(orderId);
		if (!CollectionUtils.isEmpty(details)) {
			model.addAttribute("details", details);
			model.addAttribute("info", orderService.findById(orderId));
			double temp = 0;
			for (OrderDetails dt : details) {
				temp += dt.getTotalPrice();
			}
			model.addAttribute("total", temp);
		}

		return "user/ViewOrderDetail";
	}

	@GetMapping("/SearchOrder/{page}")
	public String SearchOrderFromTo(Model model, @RequestParam("from") String from, @RequestParam("to") String to,
			@PathVariable("page") int page) {
		AccountEntity user = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		Date dateFrom = ConvertStringToDate(from + " 00:00:00");
		Date dateTo = ConvertStringToDate(to + " 23:59:59");

		List<Orders> ordersReturn = new ArrayList<Orders>();
		if (dateFrom.compareTo(dateTo) > 0) {
			model.addAttribute("msg", "> Invalid Date, Please Select Another Date");
			model.addAttribute("orders", ordersReturn);
			return "user/ManageOrder";

		}

		List<Customer> customer1 = customerService.findByAccountId(user.getId());
		List<Orders> orders = orderService.SearchByDateFromTo(dateFrom, dateTo);
		for (Customer c : customer1) {
			for (int j = 0; j < orders.size(); j++) {
				if (c.getCustomerId() == orders.get(j).getCustomer().getCustomerId()) {
					ordersReturn.add(orders.get(j));
				}
			}
		}
		Collections.sort(ordersReturn, new Comparator<Orders>() {
			@Override
			public int compare(Orders order1, Orders order2) {
				return order2.getDateCreate().compareTo(order1.getDateCreate());
			}
		});
		List<Orders> lastList = new ArrayList<Orders>();
		if (ordersReturn.size() > 0) {
			int pages = (int) Math.ceil(ordersReturn.size() / 7.0);
			model.addAttribute("pages", pages);

			if (page * 7 <= ordersReturn.size()) {
				lastList = ordersReturn.subList((page - 1) * 7, page * 7);
			} else {
				lastList = ordersReturn.subList((page - 1) * 7, ordersReturn.size() - 1);
			}

		}
		model.addAttribute("current", page);
		model.addAttribute("action", "user/SearchOrder");
		model.addAttribute("orders", lastList);

		return "user/ManageOrder";
	}

	public Date ConvertStringToDate(String date) {
		try {
			Date date2 = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date2 = df.parse(date);
			return date2;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Autowired
	AccountService accountService;
	
	
	@RequestMapping(value = "/Favourites/{accountId}")
	public String ViewPageFavourite(@PathVariable("accountId") int accountId, Model model) {
		AccountEntity ac = javaUtils.getAccountAuthentication();
		if(ac == null) {
			return "login";
		}
		else{
		
			List<Favourite> favourites = favouriteService.getListFavouritesByAccount(ac);
			model.addAttribute("favourites", favourites);
			model.addAttribute("user", ac);
		}

		return "user/ViewFavourite";
	}

	@Autowired
	FavouriteService favouriteService;

	@RequestMapping(value = "/DeleteFavourite/{favouriteId}")
	public String DeleteFavourite(@PathVariable("favouriteId") int favouriteId) {
		AccountEntity user = javaUtils.getAccountAuthentication();
		
		favouriteService.DeleteFavourite(favouriteId);
		String temp = "redirect:/user/Favourites/" + user.getId();
		return temp;
	}

	@RequestMapping(value = "/ViewAccount", method = RequestMethod.GET)
	public String ViewAndUpdateAccount(Model model) {
		AccountEntity accountEntity = javaUtils.getAccountAuthentication();
		if (accountEntity != null) {
			AccountEntity entity = accountService.findAccountById(accountEntity.getId());
			model.addAttribute("user", entity);

		}

		model.addAttribute("action", "update-account");
		return "user/ViewAccount";
	}

	@RequestMapping(value = "/update-account", method = RequestMethod.POST)
	public String UpdateAccount(@ModelAttribute("account") @Valid AccountEntity accountEntity, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("account", new AccountEntity());
			model.addAttribute("action", "UpdateAccount");
			return "user/ViewAccount";
		}
		return "";
	}
}
