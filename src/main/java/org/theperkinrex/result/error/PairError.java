package org.theperkinrex.result.error;

import org.theperkinrex.result.IError;
import org.theperkinrex.result.SimpleError;

import java.util.Objects;

public abstract class PairError extends SimpleError {
	private String inp;
	private IError err;

	private boolean first;

	public PairError(String inp, IError error, boolean first) {
		super("Unexpected input for " + (first? "first" : "second") +" element in pair (\"" + inp + "\"), expected " + error);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PairError pairError = (PairError) o;

		if (first != pairError.first) return false;
		if (!Objects.equals(inp, pairError.inp)) return false;
		return Objects.equals(err, pairError.err);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (inp != null ? inp.hashCode() : 0);
		result = 31 * result + (err != null ? err.hashCode() : 0);
		result = 31 * result + (first ? 1 : 0);
		return result;
	}

	public IError error() {
		return this.err;
	}

	public String input() {
		return inp;
	}

	public static class First extends PairError {
		public First(String inp, IError error) {
			super(inp, error, true);
		}

		@Override
		public boolean equals(Object o) {
			return this == o || o instanceof First && super.equals(o);
		}
	}

	public static class Second extends PairError {
		public Second(String inp, IError error) {
			super(inp, error, false);
		}

		@Override
		public boolean equals(Object o) {
			return this == o || o instanceof Second && super.equals(o);
		}
	}
}
