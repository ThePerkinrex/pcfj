package org.theperkinrex.parse;

import org.theperkinrex.result.Result;
import org.theperkinrex.result.error.EOFError;
import org.theperkinrex.result.error.PredError;
import org.theperkinrex.result.error.TokenError;

import java.util.function.Predicate;

public class SimpleParsers {
	public static Parser<String> token(String tok) {
		return inp -> {
			if (inp.startsWith(tok)) {
				return Result.ok(inp.substring(tok.length()), tok);
			} else {
				return Result.error(new TokenError(tok));
			}
		};
	}

	public static Parser<Character> characterIs(Predicate<Character> pred) {
		return inp -> {
			if (inp.length() == 0) return Result.error(new EOFError());
			Character c = inp.charAt(0);
			if (pred.test(c)) {
				return Result.ok(inp.substring(1), c);
			} else {
				return Result.error(new PredError(c));
			}
		};
	}

	public static <T extends U, U> Parser<U> map(Parser<T> p) {
		return inp -> {
			Result<T> r = p.parse(inp);
			if (r.isError()) return Result.error(r.error());
			return Result.ok(r.ok()
					                 .rest(), r.ok()
					                 .result());
		};
	}

	public static <T, U> Parser<U> map(Parser<T> p, MapFn<T, U> map) {
		return inp -> {
			Result<T> r = p.parse(inp);
			if (r.isError()) return Result.error(r.error());
			return Result.ok(r.ok()
					                 .rest(), map.map(r.ok()
							                                  .result()));
		};
	}
}
