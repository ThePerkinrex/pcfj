package org.theperkinrex.parse.combinators;

import org.jetbrains.annotations.NotNull;
import org.theperkinrex.parse.Parser;
import org.theperkinrex.result.Result;
import org.theperkinrex.result.error.PairError;

import java.util.Objects;

public record Pair<A, B>(A left, B right) {
	@Override
	public String toString() {
		return "(" + left +
				", " + right +
				')';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pair<?, ?> pair = (Pair<?, ?>) o;

		if (!Objects.equals(left, pair.left)) return false;
		return Objects.equals(right, pair.right);
	}

	@Override
	public int hashCode() {
		int result = left != null ? left.hashCode() : 0;
		result = 31 * result + (right != null ? right.hashCode() : 0);
		return result;
	}

	public static <A, B> Parser<Pair<A, B>> pair(Parser<A> a, Parser<B> b) {
		return new Parser<Pair<A, B>>() {
			@Override
			public @NotNull Result<Pair<A, B>> parse(String inp) {
				Result<A> resA = a.parse(inp);
				if (resA.isError()) return Result.error(new PairError.First(inp, resA.error()));
				Result<B> resB = b.parse(resA.ok().rest());
				if (resB.isError()) return Result.error(new PairError.Second(resA.ok().rest(), resB.error()));
				return Result.ok(resB.ok().rest(), new Pair<>(resA.ok().result(), resB.ok().result()));
			}
		};
	}
}
