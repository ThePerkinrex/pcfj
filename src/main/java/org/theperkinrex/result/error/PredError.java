package org.theperkinrex.result.error;

import org.theperkinrex.result.SimpleError;

public class PredError extends SimpleError {
	private final char found;

	public PredError(char found) {
		super("Unexpected '" + found + '\'');
		this.found = found;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PredError predError = (PredError) o;

		return found == predError.found;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (int) found;
		return result;
	}
}
