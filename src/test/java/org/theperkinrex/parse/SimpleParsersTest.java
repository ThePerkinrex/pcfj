package org.theperkinrex.parse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.theperkinrex.result.Result;
import org.theperkinrex.result.SimpleError;

import static org.junit.jupiter.api.Assertions.*;

class SimpleParsersTest {

	@Test
	void token() {
		String input = "HOLAHOLAADIOS";
		Parser<String> holaParser = SimpleParsers.token("HOLA");
		Result<String> res1 = holaParser.parse(input);
//        System.out.println("1: " + res1);
		assertEquals(Result.ok("HOLAADIOS", "HOLA"), res1);
		Result<String> res2 = holaParser.parse(res1.ok().rest());
//        System.out.println("2: " + res2);
		assertEquals(Result.ok("ADIOS", "HOLA"), res2);
		Result<String> res3 = holaParser.parse(res2.ok().rest());
//        System.out.println("3: " + res3);

		assertAll(() -> assertTrue(res3.isError()), () -> assertNotNull(res3.error()), () -> assertTrue(res3.error().recoverable()));

	}
}