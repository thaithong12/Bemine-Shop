package com.tq.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tq.entities.AccountEntity;
import com.tq.entities.Cart;
import com.tq.entities.Color;
import com.tq.entities.Favourite;
import com.tq.entities.Orders;
import com.tq.entities.ProductDetails;
import com.tq.entities.ProductEntity;
import com.tq.entities.Review;
import com.tq.entities.Size;
import com.tq.enums.StatusOrder;
import com.tq.service.AccountService;
import com.tq.service.ColorService;
import com.tq.service.FavouriteService;
import com.tq.service.OrderService;
import com.tq.service.ProductDetailService;
import com.tq.service.ProductService;
import com.tq.service.ReviewService;
import com.tq.service.SizeService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "api/")
@SessionAttributes("cart")
public class AjaxAPI {
	@Autowired
	ProductService productService;

	@Autowired
	ProductDetailService productDetailService;

	@PostMapping("AddProduct")
	public ResponseEntity<Object> AddCart(@RequestParam String dataJson, Model model, HttpSession session) {
		Cart cart = new Gson().fromJson(dataJson, Cart.class);
		ProductEntity productEntity = productService.findOne(cart.getProductId());
		ProductDetails dt = productDetailService.GetQuantityInStock(cart.getProductId(), cart.getColorId(),
				cart.getSizeId());
		Cart cart2 = new Cart(cart.getProductId(), dt.getProductDetailsId(), productEntity.getNewPrice(),
				productEntity.getProductName(), dt.getSize().getSizeName(), dt.getColor().getColorName(),
				cart.getQuantity(), productEntity.getOneImage(), cart.getColorId(), cart.getSizeId());
		if (session.getAttribute("cart") == null) {
			List<Cart> carts = new ArrayList<Cart>();
			cart2.setCartId(1);
			carts.add(cart2);
			model.addAttribute("cart", carts);
			System.out.println("Khoi tao gio hang " + carts.size());
		} else {
			List<Cart> lcarts = (List<Cart>) session.getAttribute("cart");
			int check = CheckProductInCart(session, cart2);
			if (check <0) {
				cart2.setCartId(lcarts.size()+1);
				lcarts.add(cart2);
				System.out.println("San pham duoc them lan dau " + lcarts.size());
			} else {
						int qty = lcarts.get(check).getQuantity() +1;
						lcarts.get(check).setQuantity(qty);
						System.out.println("San pham duc them lan tiep theo " + lcarts.size());
			}

		}
		return new ResponseEntity(HttpStatus.OK);
	}

	public int CheckProductInCart(HttpSession session, Cart cart) {
		if (session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			for(int i = 0 ; i < carts.size() ; i ++) {
				if(carts.get(i).getProductId() == cart.getProductId() && cart.getColorId() == carts.get(i).getColorId()
						&& carts.get(i).getSizeId() == cart.getSizeId()) {
					return i ;
				}
			}
		}
		return -1;
	}

	/*
	 * public void SetTimeOutSession(HttpSession session) { if
	 * (session.getAttribute("cart") != null) { HttpSession httpSession =
	 * (HttpSession) session.getAttribute("cart");
	 * httpSession.setMaxInactiveInterval(5 * 60); } }
	 */
	@GetMapping("GetSizeCart")
	@ResponseBody
	public int GetSizeCart(HttpSession httpSession) {
		int count = 0;
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
			for (Cart cart : carts) {
				count += cart.getQuantity();
			}

		}
		return count;
	}

	@GetMapping(path = "UpdateFilterCart", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String UpdateFilterCart(HttpSession session) {
		String html = "";
		if (session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			for (Cart c : carts) {
				String str = ConvertFormatVND(c.getSumPrice());
				html += "<li>\n";
				html += "<div class=\"cart-item\">\n";
				html += "<div class=\"image\"><img src='/TQShop/resources/images/products/all/" + c.getImage() + "'>";
				html += "</div>\n";
				html += "<div class=\"item-description\">\n";
				html += "<p class=\"name\">" + c.getProductName() + "</p>\n";
				html += "<p>Size: <span class=\"light-red size-pcart\">"
						+ c.getSizeName() + "</span>" + "<br>Quantity: <span class=\"light-red\">" + c.getQuantity()
						+ "</span>" + "<br>Color: <span class=\"light-red color-pcart\">" + c.getColorName() + "</span>" + "</p>\n";
				html += "</div>\n";
				html += "<div class=\"right\">\n";
				html += "<p class=\"price\">" + str + "</p>\n";
				html += "<a class=\"remove\" data-cart= '"+c.getCartId()+"'><img src='/TQShop/resources/images/remove.png'></a>\n";
				html += "</div>\n";
				html += "</div>\n";
				html += "</li>";
			}
		}

		return html;
	}
	public String ConvertFormatVND(double vnd) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(localeVN);
		String str = format.format(vnd);
		return str;
	}

	@GetMapping(path = "TotalCart", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String TotalCart(HttpSession session) {
		double temp = 0;
		if (session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			for (Cart cart : carts) {
				temp += cart.getPrice() * cart.getQuantity();
			}
		}
		return ConvertFormatVND(temp);
	}

	@GetMapping(path = "ChangeSelect", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String HandelEventChangeSelect(@RequestParam("product_id") int productId,
			@RequestParam("color_id") int colorId) {
		List<ProductDetails> details = productDetailService.getProductDetailByProductIdAndColorId(productId, colorId);
		String html = "";
		
		if (!CollectionUtils.isEmpty(details)) {

			for (ProductDetails detail : details) {
				
						html += "<option value=\"" + detail.getSize().getSizeId() + "\">\r\n" + detail.getSize().getSizeName()
								+ "\r\n" + "</option>";
			}
		}
		return html;
	}
	
	
	@GetMapping(path = "ChangeSelect2", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String HandelEventChangeSelect2(@RequestParam("product_id") int productId,
			@RequestParam("color_id") int colorId ,@RequestParam("size_id") int sizeId , HttpSession httpSession) {
		List<ProductDetails> details = productDetailService.getProductDetailByProductIdAndColorId(productId, colorId);
		String html = "";
		
		if (!CollectionUtils.isEmpty(details)) {

			for (ProductDetails detail : details) {
				if(detail.getSize().getSizeId() != sizeId) {
					html += "<option value=\"" + detail.getSize().getSizeId() + "\">\r\n" + detail.getSize().getSizeName()
							+ "\r\n" + "</option>";
				}else {
					
						html += "<option value=\"" + detail.getSize().getSizeId() + "\" selected=\"selected\">\r\n" + detail.getSize().getSizeName()
								+ "\r\n" + "</option>";			
				}
			
			}
		}
		return html;
	}

	@GetMapping("GetQuantityInStock")
	@ResponseBody
	public int GetQuantityInStock(@RequestParam("product_id") int productId, @RequestParam("color_id") int colorId,
			@RequestParam("size_id") int sizeId) {
		int qty = 0;
		ProductDetails productDetail = productDetailService.GetQuantityInStock(productId, colorId, sizeId);
		if (productDetail.getProductDetailsId() > 0) {
			qty = productDetail.getQuantity();
		}
		return qty;
	}

	
	@GetMapping(path = "FindByName", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String FindByName(@RequestParam("txtName") String txtName) {
		List<ProductEntity> productEntities = productService.findByName(txtName);
		String html = "";
		if(!CollectionUtils.isEmpty(productEntities)) {
			for(ProductEntity entity : productEntities) {
				html += "<li>\r\n" + 
						"<a href='/TQShop/ViewDetail/"+entity.getProductId()+"'>\r\n" + 
						"<p><img src='/TQShop/resources/images/products/all/"+entity.getOneImage()+"' alt=\"product\" /></p>\r\n" + 
						"<div>\r\n" + 
						"<h3>"+entity.getProductName() +"</h3>\r\n" + 
						"<p class=\"fshop-search-prodprice\">"+entity.getPrice()+"</p>\r\n" + 
						"</div>\r\n" + 
						"</a>\r\n" + 
						"</li>";
			}
		}
		return html;
	}
	
	@Autowired
	ColorService colorService;
	@Autowired
	SizeService sizeService;
	
	@GetMapping("UpdateCart")
	@ResponseBody
	public String UpdateCart(@RequestParam("cart_id") int cartId ,@RequestParam("color_id") int colorId,
			@RequestParam("size_id") int sizeId,@RequestParam("quantity") int quantity,
			@RequestParam("product_id") int productId  ,HttpSession httpSession) {
		if (httpSession.getAttribute("cart")!= null) {
			List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
			int check = CheckLocationCartByCartId(cartId, httpSession);
			if(check >= 0) {
					
				Color c = colorService.getColorById(colorId);
				Size s = sizeService.getSizeBySizeId(sizeId);
				carts.get(check).setColorId(colorId);
				carts.get(check).setSizeId(sizeId);
				carts.get(check).setQuantity(quantity);
				carts.get(check).setColorName(c.getColorName());
				carts.get(check).setSizeName(s.getSizeName());
					for(int i = 0 ; i < carts.size() ; i++) {
						if(i != check) {
							if(carts.get(i).getProductId() == productId && carts.get(i).getSizeId() == sizeId
									&& carts.get(i).getColorId() == colorId	) {
									int qty2 = carts.get(i).getQuantity() + quantity;
									carts.get(i).setQuantity(qty2);
									carts.remove(check);
									return false + "";
								}
						}
						
					}
				}
			}
		return true +"";
		}
	@GetMapping("DeleteCart2")
	@ResponseBody
	public void DeleteCart(@RequestParam("cart_id") int cartId,HttpSession httpSession ) {
		if(httpSession.getAttribute("cart")!=null) {
			List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
			int check = CheckLocationCartByCartId(cartId, httpSession);
			if(check>= 0) {
				carts.remove(check);
			}
		}
	}
	
	public int CheckLocationCartByCartId(int id,HttpSession httpSession) {
		if(httpSession.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) httpSession.getAttribute("cart");
			for(int i = 0 ; i < carts.size() ; i ++) {
				if (id == carts.get(i).getCartId()) {
					return i;
				}
			}
		}
		return -1;
	}
	@Autowired
	OrderService orderService;
	
	@Autowired
	JavaMailSender sender;
	
	@PostMapping("CancelOrder")
	@ResponseBody
	public String CancelOrder(@RequestParam("order_id") int orderId) throws MessagingException {
		Orders order = orderService.findById(orderId);
		if(order != null ) {
			if(order.getOrderId() >0 && order.getStatus().equals(StatusOrder.PROCESSING)) {
				order.setStatus(StatusOrder.CANCLE);
				orderService.saveOrder(order);
				Date date = new Date();

				MimeMessage message = sender.createMimeMessage();

				boolean multipart = true;

				MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
				String htmlMsg = "<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"	<meta charset=\"UTF-8\">\r\n" + 
						"	<title>Vaildator mail</title>\r\n" + 
						"</head>\r\n" + 
						"<body >\r\n" + 
						"	<div style=\"margin:0px;padding:0px; overflow: hidden;\">\r\n" + 
						"	<div style=' background: url(\"https://aliceandquinn.files.wordpress.com/2019/04/1286.jpg?w=940\") no-repeat center center fixed; \r\n" + 
						"  -webkit-background-size: cover;\r\n" + 
						"  -moz-background-size: cover;\r\n" + 
						"  -o-background-size: cover;\r\n" + 
						"  background-size: cover;height: 100%; display: flex;justify-content: center;align-items: center;'>\r\n" + 
						"		<div>\r\n" + 
						"			<div style=\"width: 100%;\">\r\n" + 
						"				\r\n" + 
						"				<img src=\"https://theme.hstatic.net/1000135148/1000474095/14/logo.png?v=79\" alt=\"\" style=\"margin-left: 44%;margin-top: 5%;margin-bottom: 3%;\">\r\n" + 
						"			</div>\r\n" + 
						"			<div class=\"container\" style=\"margin-left: 8%; width: 820px;height: 100%;background-color: white;opacity: 0.8;text-align: center;margin-bottom: 6%;\">\r\n" + 
						"				<div style=\"padding-top:  5%;\">\r\n" + 
						"					<img style=\"width: 90px;height: 90px;\" src='https://scontent.fdad5-1.fna.fbcdn.net/v/t1.0-9/76644593_1404282816401378_7817559808432668672_n.jpg?_nc_cat=100&_nc_oc=AQkWNuV9qoeWY3Z1ZIqpNNZOhryNkNLw9e-SuMVMiY23buQEjtErH3Q1tBXkGb6RMzw&_nc_ht=scontent.fdad5-1.fna&oh=c71320a99fc6bc781edf9739d895b9c4&oe=5E52130B' alt=\"\" style=\"\">\r\n" + 
						"\r\n" + 
						"				</div>\r\n" + 
						"				<div style=\"margin-bottom:17px;color:#2d363d;font-family:Helvetica,Arial,sans-serif;font-size:28px;font-weight:700;text-align:center;word-wrap:break-word;margin-top: 1%;\">				\r\n" + 
						"						"+order.getCustomer().getAccountEntity().getCustomerName() +"\r\n" + 
						"				</div>\r\n" + 
						"				<div style=\"margin-bottom:10px; padding-bottom: 2% ;color:#40c778;font-family:Helvetica,Arial,sans-serif;font-size:18px;font-weight:400;text-align:center\">\r\n" + 
						"					Your order has been canceled !\r\n" + 
						"\r\n" + 
						"				</div>\r\n" + 
						"				<div style=\"margin-bottom:10px; padding-bottom: 2% ;color:black;font-family:Helvetica,Arial,sans-serif;font-size:18px;font-weight:400;text-align:center\">\r\n" + 
						"					"+date +"\r\n" + 
						"\r\n" + 
						"				</div>\r\n" + 
						"			\r\n" + 
						"\r\n" + 
						"			</div>\r\n" + 
						"\r\n" + 
						"		</div>\r\n" + 
						"\r\n" + 
						"	</div>\r\n" + 
						"	</div>\r\n" + 
						"</body>\r\n" + 
						"</html>";
				htmlMsg += "</html>";
				
				message.setContent(htmlMsg, "text/html");

				helper.setTo(order.getCustomer().getAccountEntity().getEmail());

				helper.setSubject("Cancle Order");

				sender.send(message);
				System.out.println("Da gui mail");
				return true + "";
			}
		
		}
		return false+"";
		
	}
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	FavouriteService favouriteService;
	
	@PostMapping("AddFavourite")
	@ResponseBody
	public String AddFavourite(@RequestParam("account_id") int accountId, @RequestParam("product_id") int productId) {
		/* System.out.println(accountId + " " + productId); */
		AccountEntity accountEntity = accountService.findAccountById(accountId);
		if(accountEntity != null) {
			List<Favourite> favourites = favouriteService.getListFavouritesByAccount(accountEntity);
			int check  = checkProductExistInListFavourite(productId , favourites);
			if(check <0) {
				Favourite fa = new Favourite();
				fa.setAccount(accountEntity);
				fa.setProductEntities(productService.findOne(productId));
				
				Favourite newFavourite = favouriteService.saveFavourite(fa);
				if(newFavourite.getFavouriteId() >0 ) {
					return true +"";
				}
			}
		}
		
		return false +"";
	}
	
	public int checkProductExistInListFavourite(int productId, List<Favourite> favourites) {
		if(!CollectionUtils.isEmpty(favourites)) {
			for(int i = 0 ; i < favourites.size() ; i ++) {
				if(favourites.get(i).getProductEntities().getProductId() == productId) {
					return i;
				}
			}
		}
		return -1;
	}
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("AddComment")
	@ResponseBody
	public String AddComment(@RequestParam("account_id") int accountId , @RequestParam("product_id") int productId,
			@RequestParam("contend") String contend) {
		
		AccountEntity accountEntity = accountService.findAccountById(accountId);
		ProductEntity productEntity = productService.findOne(productId);
		DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = fm.format(date);
		String html = "";
		String [] a = {"bad" , "bitch" , "haiz"};
		if(contend == "" || contend.length() == 0 || StringUtils.isEmpty(contend)) {
			return null;
		}
		String avatar ="https://ssl.gstatic.com/accounts/ui/avatar_2x.png";
		if(!StringUtils.isEmpty(accountEntity.getAvatar())) {
			avatar ="/TQShop/resources/images/avatar/"+accountEntity.getAvatar();
		}
		
		for(String temp : a) {
			if(contend.contains(temp)) {
				return null;
			}
		}
			html += "<div class=\"review\">\r\n" + 
					"                  	<img style=\"width: 80px;float: left;height: 84px;margin-right: 4%;\" class=\"img-responsive user-photo\" src=\""+ avatar+"\">\r\n" + 
					"                      <p class=\"rating\">\r\n" + 
					"                        <i class=\"fa fa-star light-red\">\r\n" + 
					"                        </i>\r\n" + 
					"                        <i class=\"fa fa-star light-red\">\r\n" + 
					"                        </i>\r\n" + 
					"                        <i class=\"fa fa-star light-red\">\r\n" + 
					"                        </i>\r\n" + 
					"                        <i class=\"fa fa-star-half-o gray\">\r\n" + 
					"                        </i>\r\n" + 
					"                        <i class=\"fa fa-star-o gray\">\r\n" + 
					"                        </i>\r\n" + 
					"                      </p>\r\n" + 
					"                      \r\n" + 
					"                      <h5 class=\"reviewer\">\r\n" + 
												accountEntity.getCustomerName()	+ 
					"                      </h5>\r\n" + 
					"                      <p class=\"review-date\">\r\n" + 
												now+ 
					"                      </p>\r\n" + 
					"                      <p>\r\n" + 
										contend +
					"                      </p>\r\n" + 
					"                    </div>";
		Review review = new Review(contend, date, accountEntity, productEntity);
		reviewService.addReview(review);	
		return html;
	}
}
