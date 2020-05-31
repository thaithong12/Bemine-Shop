$(document).ready(function(){
	$('#btn-showpass').click(function(){
		 var x = document.getElementById("password");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
    });
	
	$("#sign-in").click(function(){
		$(this).addClass("active");
		$("#sign-up").removeClass("active");
		$(".form-sign-in").show();
		$(".form-sign-up").css("display","none");
		$('#alert-message').empty();
	});
	
	$("#sign-up").click(function(){
		$(this).addClass("active");
		$("#sign-in").removeClass("active");
		$(".form-sign-in").hide();
		$(".form-sign-up").show();
		$('#alert-message-2').empty();
	});
	$("#si").click(function(event) {
		event.preventdefault();
	})
	$("#su").click(function(event) {
		event.preventdefault();
	})
	function CheckValidEmail(email) {
		var check = false;
		$.ajax({
			url : "/TQShop/api/CheckEmail",
			method : "POST",
			data :{
				email : email
			},
			success : function(value, status) {
				check = value;
			}
		})
	}
	function validateEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(String(email).toLowerCase());
	}
	$("#btn-form-sign-up").click(function(event) {
		event.preventDefault();
		 var formdata= $('#form-sign-up').serializeArray();
		 json = {};
		 $.each(formdata, function(i, field){
			 json[field.name]   = field.value;   		 
		 });
		 console.log(json);
		/* alert(json['colorId'] + " " +json['productId'] + " "+ json['sizeId'] + " " +json['quantity'])*/
		 if(json['customerName'] != "" &&json['address'] != "" &&json['phoneNumber'] != "" &&json['email'] != "" &&
				 json['password'] != "" &&json['rePassword'] != "" ){
			 json['email'] = json['email'].toLowerCase();
			 var regex = /[,#-\/\!\@\$]/gi; // ... add all the characters you need
			 if (regex.test(json['address'])) {
				 $('#alert-message-2').html('Address Not Invalid !!!');
			   return ;
			 }
			 var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
			 if(vnf_regex.test(json['phoneNumber']) == false){
				 $('#alert-message-2').html('Phone Number Not Invalid !!!');				 
				 return;
			 }
			 if(!validateEmail(json['email'])){
				 $('#alert-message-2').html('Email Not Invalid !!!');
				 return;
			 }
			 if(! (json['password'] == json['rePassword']) ){
				 $('#alert-message-2').html('Pass and Re-Pass must be samp !!!');
				 return;
			 }
			 if(check_pass(json['password']) == "false" ||check_pass(json['rePassword']) == "false"){
				 $('#alert-message-2').html('Passwords must contain uppercase characters,special characters(#,/,@,..),characters from 0-9, length greater than 8.');
				 return;
			 }
			 else{
				 json['avatar'] = file_name;
				 $('#alert-message-2').html('Processing ... !!!');
				 $.ajax({
						url : "/TQShop/api/Registration",
						method : "POST",
						data :{ 
							dataJson : JSON.stringify(json)},
						success : function(value, status) {
							/*alert("OK"+status);*/
							$('#alert-message-2').html(value);
						}, error: function(XMLHttpRequest, textStatus, errorThrown){
							alert(errorThrown + " " +textStatus );
			            },
					})
			 }
			
	}else{
		 $('#alert-message-2').html('Please enter the empty field !!!');
		 return;
	}
	});
	
	function check_pass(myPass) { 
        var res; 
        if (myPass.match(/[a-z]/g) && myPass.match( 
                /[A-Z]/g) && myPass.match( 
                /[0-9]/g) && myPass.match( 
                /[^a-zA-Z\d]/g) && myPass.length >= 8) 
            res = "true"; 
        else 
            res = "false"; 
        
        return res;
    };
    var files = [];
    var file_name = "";
    $('#avatar').change(function(event) {
    	file_name = $('input[type=file]').val().split('\\').pop();
    	/*alert(file_name);*/
    	files = event.target.files;
    	forms = new FormData();
		forms.append("file",files[0]);
		
		$.ajax({
			url:"/TQShop/api/UploadFile",
		    type:"POST",
		    data:forms,
		    contentType:false,
		    processData:false,
		    enctype: "multipart/form-data",
			success: function(value){
				
			}
		})
    	
	})
})