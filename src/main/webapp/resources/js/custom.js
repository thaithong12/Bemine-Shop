$(document).ready(function () {
	
	/*start page login js */
	

/*	
	
	$("body").on("click",".ab",function(){
		var cr  = document.getElementsByClassName("ab");
		cr.removeClass("active");
		$(this).addClass("active");
		alert("thong");
	});*/
	
/*	end page login js ===============================================*/
	
	/*start page home*/	
	GetSizeCart();
	function GetSizeCart() {
		$.ajax({
			url : "/TQShop/api/GetSizeCart"
				
		}).done(function(value) {
			$("#cart-here").text("(" + value + ")");
		})
	};
	UpdateFilterCart();
	function UpdateFilterCart() {
		$.ajax({
			url : "/TQShop/api/UpdateFilterCart"
		}).done(function(value) {
			$("#filter-cart").html(value);
			/*alert(value);*/
		})
	}
	TotalCart();
	function TotalCart() {
		$.ajax({
			url : "/TQShop/api/TotalCart"
		}).done(function(value) {
			$("#total-cart").html(value);
		})
	}
	$("body").on("click",".remove",function(){
		/*alert("thong");*/
		var cart_id = $(this).attr("data-cart");
		$.ajax({
			url : "/TQShop/api/DeleteCart2",
			method : "GET",
			data : {
				cart_id : cart_id
			},
			success : function(value) {
				GetSizeCart();
				UpdateFilterCart();
				TotalCart();
			}
		});
	});
	
	/*start imgae background*/
	
	var images=new Array('/TQShop/resources/images/br/br1.jpg','/TQShop/resources/images/br/br2.jpg');
	var nextimage=0;
	doSlideshow();

	function doSlideshow(){
	    if(nextimage>=images.length){nextimage=0;}
	    $('.global-header')
	    .css('background-image','url("'+images[nextimage++]+'")')
	    .css('background-size', 'cover')
	    .fadeIn(4000,function(){
	        setTimeout(doSlideshow,4000);
	    });
	}
		/*end image background =================*/
	
	/*end page home js ====================================================*/
	
	
	/*start page details*/
	var count = 1;
	$(".minus").click(function() {
		
		if(count > 1){
		count = count -1;
		$("#quantity").val(count);
		var color_id = $('#select-color').val();
		var size_id = $('#select-size').val();
		var product_id  = $('#select-color').attr("data-product");
		var qty = $(this).closest(".wided").find(".quantity-selector").val();
		/*alert(color_id + " " +size_id + " "+product_id+" "+ qty);*/
		CheckQuantityInStock(product_id,color_id,size_id);
		setTimeout(() => {
			/*alert(quantityCheck)*/
				var temp = quantityCheck;
				if(qty <= temp){
					setStatus(false);				
				}
		}, 1000);
		}
	});
	$(".plus").click(function() {
		count = count +1;
		
		var qty = $(this).closest(".wided").find(".quantity-selector").val();
		var color_id = $(this).closest(".wided").find('.select-color').val();
		var size_id = $('.select-size').val();
		var product_id  = $(this).closest(".wided").find('.select-color').attr("data-product");
		
		/*alert(qty + " " + color_id + " " + size_id + " " +product_id);*/
		CheckQuantityInStock(product_id,color_id,size_id);
		setTimeout(() => {
			/*alert(quantityCheck)*/
			$('.quantity-selector').attr({
				"max" :quantityCheck
			});
				var temp = quantityCheck;
				if(qty >= temp){
					alert("Not enought");
					setStatus(true);
					count = count -1;
				}
				if(qty < temp){
					$(".quantity-selector").val(count);
				}
		}, 1000);
	});
	function setStatus(value) {
		if(value){
			$("#status-update").text("Not enought");
		}else{
			$("#status-update").text("In Stock");
		}
		
	}
	/*var quantityCheck = 0;*/
	function CheckQuantityInStock(product_id,color_id,size_id){
		quantityCheck = 0;
		$.ajax({
			url : "/TQShop/api/GetQuantityInStock",
			method : "GET",
			data : {
				product_id : product_id,
				color_id : color_id,
				size_id : size_id
			},
			success: function(data) {
				quantityCheck = data;
	        }, 
		});
	};
	
	var temp_current = $(this).find(".select-color");
	CheckColor(temp_current);
	$(".select-color").change(function(){
		var temp = $(this);
		CheckColor(temp);
	});

	function CheckColor(current) {
		var color_id = current.val();
		var product_id  = current.attr("data-product");
		/*alert(color_id + " " +product_id)*/
		$.ajax({
		url : "/TQShop/api/ChangeSelect",
			method : "GET",
			data : {
				product_id : product_id,
				color_id : color_id
			},
		}).done(function(value) {
			$('#select-size').html(value);
		})
	};
	/*end page detail===========================================================*/
	
	
	/*start page check out*/
	GetEachColor();
	function GetEachColor() {
		$("tr").each(function() {
			var product_id  = $(this).find(".a").attr("data-product");
			var color_id = $(this).find(".a").val();
			var size_id = $(this).find(".select-size").val();
			var temp = $(this);
			/*alert(product_id + " " + color_id + " " +size_id );*/
			$.ajax({
				url : "/TQShop/api/ChangeSelect2",
					method : "GET",
					data : {
						product_id : product_id,
						color_id : color_id,
						size_id : size_id
					},
					success: function(data) {
						temp.closest("tr").find(".qty2").find(".select-size").html(data);
			        }, 
				})
		})
	};
	
	TotalCartCheckOut();
	
	function TotalCartCheckOut(isEventChange) {
		var totalAll = 0 ;
		$(".price-c").each(function() {
			var price = parseFloat($(this).attr("data-price"));
			var qty =  $(this).closest("tr").find(".quantity-selector").val();
			
			var total = price* qty;
			var format = total.toLocaleString('vi', {style : 'currency', currency : 'VND'});
			totalAll =totalAll + total;
			if(!isEventChange){
				$(this).html(price.toLocaleString('vi', {style : 'currency', currency : 'VND'}));
				$(this).closest("tr").find(".total-c").html(format);
			}
			
			
			
			
		})
		var format2 = totalAll.toLocaleString('vi', {style : 'currency', currency : 'VND'});
		$("#sum-cart").html("Total Price :"+format2);
	};
	
	
	
	$('.quantity-selector').on('change' ,function(){
		var qty = $(this).closest("tr").find(".quantity-selector").val();
		var color_id = $(this).closest("tr").find('.a').val();
		var size_id = $(this).closest("tr").find('.select-size').val();
		var product_id  = $(this).closest("tr").find('.a').attr("data-product");
		var price = parseFloat($(this).closest("tr").find(".price-c").attr("data-price"));
		/*alert(price + " " + qty + " ");*/
		if(size_id == null){
			alert("Please chosse size");
			return;
		}
		CheckQuantityInStock(product_id,color_id,size_id);
		setTimeout(() => {
			$(this).closest("tr").find(".quantity-selector").attr({
				"max" :quantityCheck
			});
				var temp = quantityCheck;
				if(qty >= temp){
					alert("Not enought");
				}
				if(qty <= temp){
					var temp = qty * price;
					/*alert(temp);*/
					$(this).closest("tr").find(".total-c").html(temp.toLocaleString('vi', {style : 'currency', currency : 'VND'}));
					TotalCartCheckOut(true);
				}
		}, 100);
	});
	$(".a").on('focusin change' ,function(){
		var color_id = $(this).val();
		var product_id  = $(this).attr("data-product");
		var tem = $(this);
		$.ajax({
			url : "/TQShop/api/ChangeSelect",
				method : "GET",
				data : {
					product_id : product_id,
					color_id : color_id
				},
			}).done(function(value) {
				tem.closest("tr").find('.select-size').html(value);
			})
	});
	
	
	
	
	/*end page check out ==========================================================*/
	/* add product start */
	$(".form-data").submit(function(event) {
		event.preventDefault();
		 var formdata= $(this).serializeArray();
		 var check = $('.add-cart').attr("data-check");
		/* alert(check);*/
		 json = {};
		 $.each(formdata, function(i, field){
			 json[field.name]   = field.value;   		 
		 });
		 console.log(json);
		/* alert(json['colorId'] + " " +json['productId'] + " "+ json['sizeId'] + " " +json['quantity'])*/
		 $.ajax({
			url : "/TQShop/api/AddProduct",
			method : "POST",
			data :{ 
				dataJson : JSON.stringify(json)},
			success : function(value, status) {
				/*alert("OK"+status);*/
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(errorThrown);
            }
		}).done(function() {
			alert("Add Product Success !!");
			GetSizeCart();
			UpdateFilterCart();
			TotalCart();
			if(check == '0' || check == 0){
				var currentLocation = window.location.href;
				var nextLocation = currentLocation.replace("ViewDetail/"+json['productId'], "ViewCart");
				window.location = nextLocation;
			}
		})
	});
	/*end add product =========================================================*/
	/*	filter Search start*/
	  $("#search-input").on("input", function(){
		  var txtName = $(this).val();
		  $.ajax({
			url : "/TQShop/api/FindByName",
			method : "GET",
			data :{ 
				txtName : txtName
				},
			success : function(value) {
				/*alert(value);*/
				document.getElementById("fs-result").style.display = "block";
				$("#filter-search-here").html(value);
			}, error: function(XMLHttpRequest, textStatus, errorThrown){
				alert(errorThrown);
            }
		  });
		
	    });
	  
	  $(".wrapper").click(function(){
		  document.getElementById("fs-result").style.display = "none";
		});
	  
	  $(".btn-update-checkout").on("click", function(){
		  var cart_id = $(this).attr("data-cart");
		  var product_id =$(this).closest("tr").find(".product-name-checkout").attr("data-product");
		  var color_id  =$(this).closest("tr").find(".a").val(); 
		  var size_id = $(this).closest("tr").find(".select-size").val(); 
		  var quantity = $(this).closest("tr").find(".quantity-selector").val(); 
		  var temp = $(this);
		 /* alert(cart_id + " "+product_id+ " " + color_id +" " +size_id +" " + quantity );*/
		  $.ajax({
			url : "/TQShop/api/UpdateCart", 
			method : "GET",
			data : {
				cart_id : cart_id,
				color_id : color_id,
				size_id : size_id,
				quantity : quantity,
				product_id :product_id
			},
			success : function(value) {
				/*temp.closest("tr").Remove();*/
				UpdateFilterCart();
				alert("Update Sucess !!");
				TotalCart();
				if(value == false || value == "false"){
					location.reload();
				}
			}
		  })
	    });
	  $(".btn-delete-checkout").on("click", function(){
		  var cart_id = $(this).closest("tr").find(".btn-update-checkout").attr("data-cart");
		  var temp = $(this);
		  /*alert(cart_id);*/
		  $.ajax({
			  url : "/TQShop/api/DeleteCart2", 
			method : "GET",
			data : {
				cart_id : cart_id
			},
			success : function() {
				temp.closest("tr").remove();
				TotalCartCheckOut(true);
				UpdateFilterCart();
				TotalCart();
				GetSizeCart();
			}
		  })
	  });
/*	 filter search end ============================================================*/ 
})