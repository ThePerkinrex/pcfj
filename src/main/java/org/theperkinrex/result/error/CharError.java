package org.theperkinrex.result.error;

import org.theperkinrex.result.SimpleError;

public class CharError extends SimpleError {
	private final char expected;
	private final char found;

	public CharError(char expected, char found) {
		super("Expected '"+expected+"' but found '" + found + '\'');
		this.expected = expected;
		this.found = found;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		CharError charError = (CharError) o;

		if (expected != charError.expected) return false;
		return found == charError.found;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (int) expected;
		result = 31 * result + (int) found;
		return result;
	}
}
