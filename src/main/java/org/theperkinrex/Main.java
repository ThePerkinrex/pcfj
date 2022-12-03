package org.theperkinrex;

import org.theperkinrex.parse.Parser;
import org.theperkinrex.parse.SimpleParsers;
import org.theperkinrex.parse.combinators.Sequence;
import org.theperkinrex.result.Result;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		String input = "HOLAHOLAADIOS";
		{
			Parser<String> holaParser = SimpleParsers.token("HOLA");
			Result<String> res1 = holaParser.parse(input);
			System.out.println("1: " + res1);
			Result<String> res2 = holaParser.parse(res1.ok()
					                                       .rest());
			System.out.println("2: " + res2);
			Result<String> res3 = holaParser.parse(res2.ok()
					                                       .rest());
			System.out.println("3: " + res3);
		}

		System.out.println("MULTILINE:");
		{
			Result<List<String>> res1 = Sequence.many0(SimpleParsers.token("HOLA")).parse(input);
			System.out.println("1: " + res1);
			Result<List<String>> res2 = Sequence.many1(SimpleParsers.token("HOLA")).parse(res1.ok().rest());
			System.out.println("2: " + res2);
		}

		System.out.println("Hello world!");
	}
}