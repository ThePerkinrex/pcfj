package org.theperkinrex.parse;

import org.jetbrains.annotations.NotNull;
import org.theperkinrex.result.Result;
import org.theperkinrex.result.SimpleError;
import org.theperkinrex.result.error.TokenError;

public class SimpleParsers {
	public static Parser<String> token(String tok) {
		return new Parser<String>() {
			@Override
			public @NotNull Result<String> parse(String inp) {
				if (inp.startsWith(tok)) {
					return Result.ok(inp.substring(tok.length()), tok);
				} else {
					return Result.error(new TokenError(tok));
				}
			}
		};
	}
}
