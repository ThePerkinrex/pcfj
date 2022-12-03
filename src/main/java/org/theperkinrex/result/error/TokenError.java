package org.theperkinrex.result.error;

import org.theperkinrex.result.SimpleError;

import java.util.Objects;

public class TokenError extends SimpleError {
	private String tok;

	public TokenError(String tok) {
		super("Unexpected input, expecting token " + tok);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		TokenError that = (TokenError) o;

		return Objects.equals(tok, that.tok);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (tok != null ? tok.hashCode() : 0);
		return result;
	}
}
