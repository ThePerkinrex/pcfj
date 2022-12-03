package org.theperkinrex.result;

import java.util.Objects;

public class Result<O> {
	public record Ok<O>(String rest, O result) {
		@Override
		public String toString() {
			return "Ok(" +
					"'" + rest + '\'' +
					", " + result +
					')';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Ok<?> ok = (Ok<?>) o;

			if (!Objects.equals(rest, ok.rest)) return false;
			return Objects.equals(result, ok.result);
		}

		@Override
		public int hashCode() {
			int result1 = rest != null ? rest.hashCode() : 0;
			result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
			return result1;
		}
	}

	private final IError error;
	private final Ok<O> ok;

	private Result(IError error, Ok<O> ok) {
		this.error = error;
		this.ok = ok;
	}

	public static <O> Result<O> ok(String rest, O result) {
		return new Result<>(null, new Ok<>(rest, result));
	}

	public static <O> Result<O> error(IError error) {
		return new Result<>(error, null);
	}

	public boolean isError() {
		return this.error != null;
	}


	public boolean isOk() {
		return this.ok != null;
	}

	public IError error() {
		return error;
	}

	public Ok<O> ok() {
		return ok;
	}

	@Override
	public String toString() {
		if (isOk()) {
			return ok().toString();
		} else {
			return "Err(" + error() + ")";
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Result<?> result = (Result<?>) o;

		if (!Objects.equals(error, result.error)) return false;
		return Objects.equals(ok, result.ok);
	}

	@Override
	public int hashCode() {
		int result = error != null ? error.hashCode() : 0;
		result = 31 * result + (ok != null ? ok.hashCode() : 0);
		return result;
	}
}
