package org.theperkinrex.parse;

import org.junit.jupiter.api.Test;
import org.theperkinrex.result.Result;
import org.theperkinrex.result.error.EOFError;
import org.theperkinrex.result.error.PredError;

import static org.junit.jupiter.api.Assertions.*;

class SimpleParsersTest {

	@Test
	void token() {
		String input = "HOLAHOLAADIOS";
		Parser<String> holaParser = SimpleParsers.token("HOLA");
		Result<String> res1 = holaParser.parse(input);
//        System.out.println("1: " + res1);
		assertEquals(Result.ok("HOLAADIOS", "HOLA"), res1);
		Result<String> res2 = holaParser.parse(res1.ok()
				                                       .rest());
//        System.out.println("2: " + res2);
		assertEquals(Result.ok("ADIOS", "HOLA"), res2);
		Result<String> res3 = holaParser.parse(res2.ok()
				                                       .rest());
//        System.out.println("3: " + res3);

		assertAll(() -> assertTrue(res3.isError()), () -> assertNotNull(res3.error()), () -> assertTrue(res3.error()
				                                                                                                .recoverable()));

	}

	@Test
	void characterIs() {
		String input1 = "AB";
		String input2 = "B";
		String input3 = "";
		Parser<Character> p = SimpleParsers.characterIs((c) -> c == 'A');
		assertAll(() -> assertEquals(Result.ok("B", 'A'), p.parse(input1)),
		          () -> assertEquals(Result.error(new PredError('B')), p.parse(input2)),
		          () -> assertEquals(Result.error(new EOFError()), p.parse(input3)));
	}
}