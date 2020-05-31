$(document).ready(function () {
	$('.add-favourite').on("click",function(){
		var account_id = $(this).attr('data-account');
		var product_id = $(this).closest("div").find(".add-cart").attr("data-id");
		var cur  = $(this).attr("data-page");
		/*alert(account_id + " " + product_id);*/
		if (typeof account_id !== typeof undefined && account_id !== false && account_id !="") {
			$.ajax({
				url : "/TQShop/api/AddFavourite",
				method : "POST",
				data : {
					account_id : account_id,
					product_id, product_id,
				},
				success : function(value) {
					if(value == true || value =="true"){
						alert("Add Success !");
					}else{
						alert("Product already exists !");
					}
				}
			})
		}else{
			var currentLocation = window.location.href;
			if(currentLocation.includes("home") ){
				var nextLocation = currentLocation.replace('home','login');
				window.location = nextLocation;
				return;
			}if(currentLocation.includes("ViewAll")){
				var nextLocation = currentLocation.replace("ViewAll/"+cur, "login");				
				window.location = nextLocation;
				return;
			}			
			else{
				var nextLocation = currentLocation.concat("login");
				window.location = nextLocation;
			}
		};
		
	});
	
	$('.add-comment').click(function() {
		var account_id = $(this).attr("data-account");
		var contend = $('#txt-comment').val();
		var product_id = $(this).closest("p").attr("data-product");
		var temp = $(this);
		$.ajax({
			url : "/TQShop/api/AddComment",
			method : "POST",
			data : {
				account_id : account_id,
				product_id, product_id,
				contend : contend,
			},
			success : function(value) {
				if(value != null){
					$('#temp').append(value);
					$('#txt-comment').val('');
				}
				
			}
		});
		
	});
	
	$('.paging-item').on('click', function() {
		$(".paging-item").removeClass("active");
		$(this).addClass('active');
	})
});