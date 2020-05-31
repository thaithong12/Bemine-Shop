package com.tq.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.tq.entities.AccountEntity;
import com.tq.entities.AccountRoleEntity;
import com.tq.entities.ChangePassword;
import com.tq.entities.ConfirmationToken;
import com.tq.enums.AccountRole;
import com.tq.service.AccountRoleService;
import com.tq.service.AccountService;
import com.tq.service.ConfirmationTokenService;
import com.tq.util.JavaUtils;

@Controller
@RequestMapping(value = "api/")
public class AjaxAPI2 {

	@Autowired
	JavaMailSender sender;

	@Autowired
	AccountService accountService;

	@Autowired
	ConfirmationTokenService confirmationTokenService;

	@Autowired
	AccountRoleService accountRoleService;

	@PostMapping("Registration")
	@ResponseBody
	public String RegisterationAccount(@RequestParam String dataJson, Model model) {
		AccountEntity accountEntity = new Gson().fromJson(dataJson, AccountEntity.class);
		if (accountService.findByEmail(accountEntity.getEmail()) != null) {
			return " Email already exists !!";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));

			AccountRoleEntity accountRoleEntity = accountRoleService.findByRole(AccountRole.ROLE_USER);

			accountRoleEntity.setRole(AccountRole.ROLE_USER);

			List<AccountRoleEntity> accountRoleEntities = new ArrayList<AccountRoleEntity>();

			accountRoleEntities.add(accountRoleEntity);

			accountEntity.setAccountRoles(accountRoleEntities);

			accountService.saveAccount(accountEntity);

			ConfirmationToken confirmationToken = new ConfirmationToken(accountEntity);

			confirmationTokenService.saveToken(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(accountEntity.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/TQShop/confirm-account?token=" + confirmationToken.getConfirmationToken());

			sender.send(mailMessage);

		}

		return "Success !!! Valid Email has been sent for you !";
	}
	@PostMapping("UpdateInfo")
	@ResponseBody
	public boolean UpdateAccount(@RequestParam String dataJson) {
		AccountEntity accountEntity = new Gson().fromJson(dataJson, AccountEntity.class);
		if (accountEntity.getId() >0) {
			AccountEntity entity = accountService.findAccountById(accountEntity.getId());
			if(entity != null ) {
				entity.setPhoneNumber(accountEntity.getPhoneNumber());
				entity.setAddress(accountEntity.getAddress());
				entity.setCustomerName(accountEntity.getCustomerName());
				if(!StringUtils.isEmpty(accountEntity.getAvatar())) {
					entity.setAvatar(accountEntity.getAvatar());
				}		
				accountService.saveAccount(entity);
				return true;
			}
			
		}
		return false;
	}
	@PostMapping("UpdatePassword")
	public ResponseEntity<AccountEntity> UpdatePassword(@RequestParam("dataJson") String dataJson){
		ChangePassword changePassword = new Gson().fromJson(dataJson, ChangePassword.class);
		if(changePassword != null) {
			if(changePassword.getId() >0) {
				AccountEntity accountEntity = accountService.findAccountById(changePassword.getId());
				if(accountEntity != null) {
					if (BCrypt.checkpw(changePassword.getOldPass(), accountEntity.getPassword())) {
						BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
						accountEntity.setPassword(encoder.encode(changePassword.getNewPass()));
						accountService.saveAccount(accountEntity);
						return new ResponseEntity(HttpStatus.OK);
					}
				}
			}
		}
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	@Autowired
	ServletContext context;
	
	@Autowired
	JavaUtils javaUtils;
	
	@PostMapping("UploadFile")
	@ResponseBody
	public String UploadFile(MultipartHttpServletRequest request) {
		AccountEntity accountEntity =javaUtils.getAccountAuthentication();
		Iterator<String> listNames = request.getFileNames();
		String path_save_file = context.getRealPath("/resources/images/avatar/");
		MultipartFile mpf = request.getFile(listNames.next());			
		if(accountEntity != null) {					
			String curAva = accountEntity.getAvatar();
			if(!StringUtils.isEmpty(curAva)) {
				File file_save = new File(path_save_file + curAva);
				try {
					mpf.transferTo(file_save);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "";
			}
			
		}	
			File file_save = new File(path_save_file + mpf.getOriginalFilename());
			try {
				mpf.transferTo(file_save);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		return "true";
	}
}
