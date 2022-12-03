package org.theperkinrex.parse;

import org.jetbrains.annotations.NotNull;
import org.theperkinrex.result.Result;

public interface Parser<O> {
	@NotNull
	Result<O> parse(String inp);
}
