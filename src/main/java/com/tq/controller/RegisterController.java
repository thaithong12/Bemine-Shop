package com.tq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tq.entities.AccountEntity;
import com.tq.entities.ConfirmationToken;
import com.tq.enums.StatusAccount;
import com.tq.service.AccountService;
import com.tq.service.ConfirmationTokenService;

@Controller
public class RegisterController {

	@Autowired
	JavaMailSender sender;

	@Autowired
	AccountService accountService;

	@Autowired
	ConfirmationTokenService confirmationTokenService;

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmUserAccount(Model model, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenService.getToken(confirmationToken);

		if (token != null) {
			AccountEntity accountEntity = accountService.findByEmail(token.getAccountEntity().getEmail());
			accountEntity.setStatus(StatusAccount.ACTIVE);
			accountService.saveAccount(accountEntity);
			
		} else {

			return "Error";
		}
		model.addAttribute("msg", "Thank for choosing BemineShop ! Have a nice day !");
		return "ThankYou";
	}
	// getters and setters
}
