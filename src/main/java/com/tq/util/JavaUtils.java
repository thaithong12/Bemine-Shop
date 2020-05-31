package com.tq.util;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tq.entities.AccountEntity;

@Service
public class JavaUtils {
	public boolean CheckAuthenticationInCurrent() {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
				!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public AccountEntity getAccountAuthentication() {
		if (CheckAuthenticationInCurrent()) {
			AccountEntity user = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user;
		}
		else return null;
	}
	
	public String ConvertFormatVND(double vnd) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(localeVN);
		String str = format.format(vnd);
		return str;
	}
}
