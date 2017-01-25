package com.asap.exception;

import net.sourceforge.stripes.exception.StripesServletException;

public class AuthorizationException extends StripesServletException {
	public AuthorizationException() {
		super("Unauthorized");
	}

	public AuthorizationException(String string) {
		super(string);
	}

	public AuthorizationException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public AuthorizationException(Throwable throwable) {
		super(throwable);
	}
}