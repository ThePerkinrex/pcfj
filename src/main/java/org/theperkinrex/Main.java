package org.theperkinrex;

import org.theperkinrex.parse.Parser;
import org.theperkinrex.parse.SimpleParsers;
import org.theperkinrex.result.Result;

public class Main {
	public static void main(String[] args) {
		String input = "HOLAHOLAADIOS";
		Parser<String> holaParser = SimpleParsers.token("HOLA");
		Result<String> res1 = holaParser.parse(input);
		System.out.println("1: " + res1);
		Result<String> res2 = holaParser.parse(res1.ok().rest());
		System.out.println("2: " + res2);
		Result<String> res3 = holaParser.parse(res2.ok().rest());
		System.out.println("3: " + res3);

		System.out.println("Hello world!");
	}
}