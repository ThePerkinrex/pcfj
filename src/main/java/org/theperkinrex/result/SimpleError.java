package org.theperkinrex.result;

import java.util.Objects;

public class SimpleError implements IError {
	private boolean recoverable;
	private String message;

	public SimpleError(boolean recoverable, String message) {
		this.recoverable = recoverable;
		this.message = message;
	}

	public SimpleError(String message) {
		this(true, message);
	}

	@Override
	public boolean recoverable() {
		return recoverable;
	}

	public void failure() {
		this.recoverable = false;
	}

	@Override
	public String toString() {
		if (recoverable) {
			return "Error: " + this.message;
		} else {
			return "Failure: " + this.message;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SimpleError that = (SimpleError) o;

		if (recoverable != that.recoverable) return false;
		return Objects.equals(message, that.message);
	}

	@Override
	public int hashCode() {
		int result = (recoverable ? 1 : 0);
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}
}
