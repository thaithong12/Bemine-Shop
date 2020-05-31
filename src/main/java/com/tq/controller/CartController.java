package com.tq.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.tq.entities.Cart;
import com.tq.entities.Customer;
import com.tq.entities.OrderDetails;
import com.tq.entities.Orders;
import com.tq.entities.ProductEntity;
import com.tq.enums.StatusOrder;
import com.tq.service.AccountService;
import com.tq.service.CategoryService;
import com.tq.service.ColorService;
import com.tq.service.CustomerService;
import com.tq.service.OrderDetailService;
import com.tq.service.OrderService;
import com.tq.service.ProductDetailService;
import com.tq.service.ProductService;
import com.tq.service.SizeService;
import com.tq.util.JavaUtils;

@Controller
@SessionAttributes("cart")
public class CartController {
	@Autowired
	ProductDetailService productDetailService;
	@Autowired
	ColorService colorService;

	@Autowired
	SizeService sizeService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	JavaUtils javaUtilsService;
	
	@Autowired
	JavaMailSender sender;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	AccountService accountService;
	
	@RequestMapping("/ViewCart")
	public String ViewCart(HttpSession httpSession, Model model) {
	
		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		if(httpSession.getAttribute("cart") == null || CollectionUtils.isEmpty((List<Cart>) httpSession.getAttribute("cart"))) {
			return "redirect:/";
		}
		List<ProductEntity> listEntity = new ArrayList<ProductEntity>();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date now = new Date();
		model.addAttribute("now", df.format(now));
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
			model.addAttribute("listItem", carts);

			for (Cart c : carts) {
				ProductEntity entity = productService.findOne(c.getProductId());
				listEntity.add(entity);
			}
			if (listEntity.size() > 0) {
				Set<ProductEntity> productEntities = new HashSet<ProductEntity>(listEntity);
				listEntity.clear();
				listEntity.addAll(productEntities);
				model.addAttribute("listP", productEntities);
			}

		}
		return "ViewCart";
	}

	@GetMapping("/CheckOut")
	public String ViewCheckOut(HttpSession session, Model model,HttpSession httpSession) {

		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		if(httpSession.getAttribute("cart") == null || CollectionUtils.isEmpty((List<Cart>) httpSession.getAttribute("cart"))) {
			return "redirect:/";
		}
		model.addAttribute("categories", categoryService.getAll());
		if (session.getAttribute("cart") != null) {
			int temp = 0;
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			model.addAttribute("listcart", carts);
			for (Cart cart : carts) {
				temp += cart.getPrice() * cart.getQuantity();
			}
			model.addAttribute("total", temp);
		}

		return "ValidateInfo";
	}

	@RequestMapping("/thank")
	public String PageThank() {
		return "ThankYou";
	}

	@RequestMapping("/Error")
	public String PageError() {
		return "Error";
	}

	@RequestMapping( value = "/sending", method = RequestMethod.POST)
	@Transactional
	public String ConfirmOrderAndSentEmail(HttpServletRequest request, HttpSession httpSession,Model model)
			throws MessagingException {

		try {
			if (httpSession.getAttribute("cart") != null && !CollectionUtils.isEmpty((List<Cart>)httpSession.getAttribute("cart"))) {
				List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");

				double temp = 0;
				for (Cart cart : carts) {
					temp += cart.getPrice() * cart.getQuantity();
				}
				
				
				Customer customer = new Customer();
				Customer cus = customerService.CheckCustomerExist(request.getParameter("phone"),request.getParameter("email"));
				
				if(cus != null) {
					if(cus.getCustomerId() >0) {
					cus.setCustomerName(request.getParameter("fullname"));
					cus.setEmail(request.getParameter("email"));
					cus.setPhoneNumber(request.getParameter("phone"));
					cus.setAddress(request.getParameter("address"));
					String userId = request.getParameter("userId");

					if (!StringUtils.isEmpty(userId)) {
						cus.setAccountEntity(
								accountService.findAccountById(Integer.parseInt(request.getParameter("userId"))));
					}
					customer = cus;
					}
				}
				else {
					customer.setCustomerName(request.getParameter("fullname"));
					customer.setEmail(request.getParameter("email"));
					customer.setPhoneNumber(request.getParameter("phone"));
					customer.setAddress(request.getParameter("address"));
					String userId = request.getParameter("userId");

					if (!StringUtils.isEmpty(userId)) {
						customer.setAccountEntity(
								accountService.findAccountById(Integer.parseInt(request.getParameter("userId"))));
					}
				}

				Customer newC = customerService.saveCustomer(customer);
				if (newC.getCustomerId() > 0) {
					List<OrderDetails> details = new ArrayList<OrderDetails>();
					for (Cart ca : carts) {
						OrderDetails detail = new OrderDetails();
						detail.setColor(ca.getColorName());
						detail.setSize(ca.getSizeName());
						detail.setPrice(ca.getPrice());
						detail.setQuantity(ca.getQuantity());
						detail.setProductEntity(productService.findOne(ca.getProductId()));
						details.add(detail);
					}

					Orders order = new Orders(request.getParameter("note"), StatusOrder.PROCESSING, temp, customer);
					Orders newOrder = orderService.saveOrder(order);
					if (order.getOrderId() > 0) {
						for (OrderDetails dt : details) {
							dt.setOrders(newOrder);
							orderDetailService.SaveOrderDetail(dt);
						}
					}

				} 

				Date date = new Date();

				MimeMessage message = sender.createMimeMessage();

				boolean multipart = true;

				MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
				String htmlMsg = "<!DOCTYPE html>\r\n" + "<html lang=\"vi\">\r\n" + "\r\n" + "<head>\r\n"
						+ "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n" + "</head>";
				htmlMsg += "<body style=\"width: 100%; padding: 0; margin: 0px; overflow: hidden;\">\r\n"
						+ "	<div style=' background: url(\"https://aliceandquinn.files.wordpress.com/2019/04/1286.jpg?w=940\") no-repeat center center fixed; \r\n"
						+ "  -webkit-background-size: cover;\r\n" + "  -moz-background-size: cover;\r\n"
						+ "  -o-background-size: cover;\r\n"
						+ "  background-size: cover;height: 100%; display: flex;justify-content: center;align-items: center;'>\r\n"
						+ "		<div>\r\n" + "			<div style=\"width: 100%;\">\r\n" + "				\r\n"
						+ "				<img src='https://theme.hstatic.net/1000135148/1000474095/14/logo.png?v=79' alt=\"\" style=\"margin-left: 46%;margin-top: 5%;margin-bottom: 3%;\">\r\n"
						+ "			</div>\r\n"
						+ "			<div class=\"container\" style=\" margin-left: 12%;width: 820px;height: 100%;background-color: white;opacity: 0.8;text-align: center;margin-bottom: 6%;\">\r\n"
						+ "				<div style=\"padding-top:  5%;\">\r\n"
						+ "					<img src='https://ci4.googleusercontent.com/proxy/9PEMQQWSL2KACm-NrsrYuawHxPt8jS0M9QHcN7OyTneHheAaS7B1fwvrBY-MM74V8R7vEiWY1hOhSvgyBLI=s0-d-e1-ft#https://vietpn.com/images/newsletter/ok.png' alt=\"\" style=\"\">\r\n"
						+ "\r\n" + "				</div>\r\n"
						+ "				<div style=\"margin-bottom:17px;color:#2d363d;font-family:Helvetica,Arial,sans-serif;font-size:28px;font-weight:700;text-align:center;word-wrap:break-word;margin-top: 1%;\">				\r\n"
						+ "						Hello " + request.getParameter("fullname") + "!\r\n"
						+ "				</div>\r\n"
						+ "				<div style=\"margin-bottom:10px;color:#40c778;font-family:Helvetica,Arial,sans-serif;font-size:18px;font-weight:400;text-align:center\">\r\n"
						+ "					Your Orders Is : \r\n" + "\r\n" + "				</div>\r\n"
						+ "					<div style=\"padding: 0% 12%; padding-bottom: 4%;\">\r\n"
						+ "				\r\n"
						+ "					<table class=\"\" style=\"color: black;border-collapse: collapse;border-spacing: 0;width: 100%;display: table;\">\r\n"
						+ "					<thead>\r\n"
						+ "						<tr style=\"border-bottom: 1px solid #ddd;\">\r\n"
						+ "							<th style=\"padding: 8px 8px;display: table-cell;\r\n"
						+ "    text-align: left;\r\n" + "    vertical-align: top;\">Product</th>\r\n"
						+ "							<th style=\"padding: 8px 8px;\r\n" + "    display: table-cell;\r\n"
						+ "    text-align: left;\r\n" + "    vertical-align: top;\">Size</th>\r\n"
						+ "							<th style=\"padding: 8px 8px;\r\n" + "    display: table-cell;\r\n"
						+ "    text-align: left;\r\n" + "    vertical-align: top;\">Color</th>\r\n"
						+ "							<th style=\"padding: 8px 8px;\r\n" + "    display: table-cell;\r\n"
						+ "    text-align: left;\r\n" + "    vertical-align: top;\">Quantity</th>\r\n"
						+ "							<th style=\"padding: 8px 8px;\r\n" + "    display: table-cell;\r\n"
						+ "    text-align: left;\r\n" + "    vertical-align: top;\">Total</th>\r\n"
						+ "						</tr>\r\n" + "					</thead>\r\n"
						+ "					<tbody>\r\n";
				for (Cart c : carts) {
					String v = new DecimalFormat("###,###,###").format(c.getSumPrice());
					htmlMsg += "<tr style=\"border-bottom: 1px solid #ddd;\">\r\n" + "<td style=\"padding: 8px 8px;\r\n"
							+ "    display: table-cell;\r\n" + "    text-align: left;\r\n"
							+ "    vertical-align: top;\"> " + c.getProductName() + "</td>\r\n"
							+ "							<td style=\"padding: 8px 8px;\r\n"
							+ "    display: table-cell;\r\n" + "    text-align: left;\r\n"
							+ "    vertical-align: top;\">" + c.getSizeName() + "</td>\r\n"
							+ "							<td style=\"padding: 8px 8px;\r\n"
							+ "    display: table-cell;\r\n" + "    text-align: left;\r\n"
							+ "    vertical-align: top;\">" + c.getColorName() + "</td>\r\n"
							+ "							<td style=\"padding: 8px 8px;\r\n"
							+ "    display: table-cell;\r\n" + "    text-align: left;\r\n"
							+ "    vertical-align: top;\">" + c.getQuantity() + "</td>\r\n"
							+ "							<td style=\"padding: 8px 8px;\r\n"
							+ "    display: table-cell;\r\n" + "    text-align: left;\r\n"
							+ "    vertical-align: top;\">" + v + "</td>\r\n" + "						</tr>\r\n"
							+ "		\r\n";
				}
				String v = new DecimalFormat("###,###,###").format(temp);
				htmlMsg += "</tbody>\r\n" + "</table>\r\n" + "</div>\r\n"
						+ "<div  style=\"padding-bottom: 1% ;color: black;font-family:Helvetica,Arial,sans-serif;font-size:16px;font-weight:700;\">\r\n"
						+ "				\r\n" + v + " VNĐ \r\n" + "</div>\r\n"
						+ "			<div  style=\"padding-bottom: 1% ;color: black;font-family:Helvetica,Arial,sans-serif;font-size:16px;font-weight:700;\">\r\n"
						+ "				\r\n" + date + "\r\n" + "			</div>\r\n"
						+ "			<div  style=\" color: #ea948d;font-family:Helvetica,Arial,sans-serif;font-size:16px;font-weight:700;\">\r\n"
						+ "				\r\n" + "				Thank you for choosing Bemine Shop!\r\n"
						+ "			</div>\r\n" + "\r\n" + "</div>\r\n" + "\r\n" + "		</div>\r\n" + "\r\n"
						+ "	</div>\r\n" + "</body>";
				htmlMsg += "</html>";

				message.setContent(htmlMsg, "text/html");

				helper.setTo(request.getParameter("email").toLowerCase());

				helper.setSubject("Confirm Bill");

				sender.send(message);
			}
			else {
				return "Error";
			}
		} catch (Exception e) {
			return "Error";
		}
		List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
		listCart.clear();
		listCart = null;
		model.addAttribute("user", javaUtilsService.getAccountAuthentication());
		model.addAttribute("msg", "You order was successfuly completed.");
		return "ThankYou";
	}
}
