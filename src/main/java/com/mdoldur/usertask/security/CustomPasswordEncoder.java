package com.mdoldur.usertask.security;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Primary
@Component
public class CustomPasswordEncoder extends BCryptPasswordEncoder {
	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null) {
			throw new IllegalArgumentException("rawPassword cannot be null");
		}
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
		encodedPassword = encodedPassword.trim();
		if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
			return false;
		}
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}
	
}
