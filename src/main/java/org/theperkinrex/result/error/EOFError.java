package org.theperkinrex.result.error;

import org.theperkinrex.result.SimpleError;

public class EOFError extends SimpleError {
	public EOFError() {
		super("Unexpected EOF");
	}
}
