package org.theperkinrex.parse.combinators;

import org.junit.jupiter.api.Test;
import org.theperkinrex.parse.Parser;
import org.theperkinrex.parse.SimpleParsers;
import org.theperkinrex.result.Result;
import org.theperkinrex.result.error.PairError;
import org.theperkinrex.result.error.TokenError;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

	@Test
	void pair() {
		String inp = "ABCD";
		assertAll(() -> {
					Parser<Pair<String, String>> p = Pair.pair(SimpleParsers.token("A"), SimpleParsers.token("B"));
					assertEquals(Result.ok("CD", new Pair<>("A", "B")), p.parse(inp));
				},
				() -> {
					Parser<Pair<String, String>> p = Pair.pair(SimpleParsers.token("A"), SimpleParsers.token("C"));
					assertEquals(Result.error(new PairError.Second("BCD", new TokenError("C"))), p.parse(inp));
//					Result<Pair<String, String>> res3 = p.parse(inp);
//					assertAll(() -> assertTrue(res3.isError()), () -> assertNotNull(res3.error()), () -> assertTrue(res3.error().recoverable()));
				},
				() -> {
					Parser<Pair<String, String>> p = Pair.pair(SimpleParsers.token("B"), SimpleParsers.token("C"));
					assertEquals(Result.error(new PairError.First("ABCD", new TokenError("B"))), p.parse(inp));
				});
	}
}