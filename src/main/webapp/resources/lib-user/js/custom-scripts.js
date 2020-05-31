$(document).ready(function () {
	$(".cancle-order").on("click", function() {
		var order_id = $(this).closest("tr").find(".id-order").attr("data-order");
		/*alert(order_id);*/
		var temp = $(this);
		$.ajax({
			
			url : "/TQShop/api/CancelOrder",
			method : "POST",
			data :{
				order_id : order_id,
			},
			success : function(value) {
				if(value == true || value == "true"){
					temp.closest("tr").find(".status").removeClass("label label-info label-mini");
					temp.closest("tr").find(".status").html("<span class='status label label-warning label-mini'>Cancel</span>");
				}
			}
		});
	});
	
	$('#change-info').submit(function( event ){
		event.preventDefault();
		 var formdata= $('#change-info').serializeArray();
		 json = {};
		 $.each(formdata, function(i, field){
			 json[field.name]   = field.value;   		 
		 });
		 console.log(json);
		 var regex = /[,#\/\!\@\$]/gi; // ... add all the characters you need
		 if (regex.test(json['address'])) {
			 $('#msg-account').html('Address Not Invalid !!!');
		   return ;
		 }
		 var vnf_regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
		 if(vnf_regex.test(json['phoneNumber']) == false){
			 $('#msg-account').html('Phone Number Not Invalid !!!');				 
			 return;
		 }else{
			 json['avatar'] = file_name;
			 $('#msg-account').html('Processing ... !!!')
			 $.ajax({
					
					url : "/TQShop/api/UpdateInfo",
					method : "POST",
					data :{ 
						dataJson : JSON.stringify(json)},
					success : function(value, status) {
						$('#msg-account').html(' Update Success !!!')
					}, error: function(XMLHttpRequest, textStatus, errorThrown){
						alert(errorThrown);
		            }
				}); 
		 }
		
	})
	$('#change-password').click(function(event) {
		event.preventDefault();
		$('#change-info').hide();
		$('#form-change-pass').show();
		 $('#msg-account').empty();
	});
	$('#back-info').click(function(event) {
		event.preventDefault();
		$('#form-change-pass').hide();
		$('#change-info').show();
		 $('#msg-account').empty();
	});
	$('#btn-submit-change-pass').click(function() {
		event.preventDefault();
		var formdata= $('#form-change-pass').serializeArray();
		 json = {};
		 $.each(formdata, function(i, field){
			 json[field.name]   = field.value;   		 
		 });
		 console.log(json);
		 if(json['oldPass'] !=""&&json['newPass'] != ""&&json['reNewPass'] != ""){
			 if(json['newPass'] != json['reNewPass'] ){
				 $('#msg-account').html('Passwords do not match.');	
				 return;
			 }else{
				 if(check_pass(json['newPass']) == 'false'
					  || check_pass(json['reNewPass']) == 'false'){
					 $('#msg-account').html('Passwords must contain uppercase characters,special characters(#,/,@,..),characters from 0-9, length greater 8 .');
					 return;
				 }else{
					 $('#msg-account').html('Processing ... !!');
					 $.ajax({
							url : "/TQShop/api/UpdatePassword",
							method : "POST",
							data :{ 
								dataJson : JSON.stringify(json)},
							success : function(value, status) {
								$('#msg-account').html('Update Password Success !!');
							}, error: function(XMLHttpRequest, textStatus, errorThrown){
								$('#msg-account').html('Current Password Is Incorrect !!');
				            }
					 });
				 }
				
			 }
		 }else{
			 $('#msg-account').html('Please Enter Filed Empty !!!');
		 }
	});
	  var files = [];
	  var file_name = "";
	  $('#avatar').change(function(event) {
	    	file_name = $('input[type=file]').val().split('\\').pop();
/*	    	var data_account =  $('#data-account').val();
	    	alert(file_name + " " + data_account);*/
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

    } 
	 $('#change-ava').click(function(event) {
		event.preventDefault();
		$(this).remove();
		$("#focus-ava").css("display", "block");
		$('#avatar').removeAttr('disabled');
	});
	 
});