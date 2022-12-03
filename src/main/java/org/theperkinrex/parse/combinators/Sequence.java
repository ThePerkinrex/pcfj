package org.theperkinrex.parse.combinators;

import org.theperkinrex.parse.Parser;
import org.theperkinrex.parse.SimpleParsers;
import org.theperkinrex.result.Result;

import java.util.LinkedList;
import java.util.List;

public class Sequence {
	private static <O> Parser<LinkedList<O>> many0Linked(Parser<O> p) {
		return inp -> {
			Result<O> res;
			LinkedList<O> r = new LinkedList<>();
			while ((res = p.parse(inp)).isOk()) {
				inp = res.ok().rest();
				r.add(res.ok().result());
			}
			return Result.ok(inp, r);
		};
	}

	public static <O> Parser<List<O>> many0(Parser<O> p) {
		return SimpleParsers.map(many0Linked(p));
	}
	private static <O> Parser<List<O>> many1CustomSecondParser(Parser<O> p, Parser<O> custom) {
		Parser<LinkedList<O>> pMany0 = many0Linked(custom);
		return inp -> {
			Result<O> res = p.parse(inp);
			if(res.isError()) return Result.error(res.error());
			inp = res.ok().rest();
			Result<LinkedList<O>> listResult = pMany0.parse(inp);
			LinkedList<O> r = listResult.ok().result();
			r.addFirst(res.ok().result());
			return Result.ok(inp, r);
		};
	}


	public static <O> Parser<List<O>> many1(Parser<O> p) {
		return many1CustomSecondParser(p, p);
	}

	public static <O> Parser<List<O>> separatedList1(Parser<O> element, Parser<?> sep) {
		return many1CustomSecondParser(element, SimpleParsers.map(Pair.pair(sep, element), Pair::right));
	}

	public static <O> Parser<List<O>> separatedList0(Parser<O> element, Parser<?> sep) {
		Parser<List<O>> p1 = separatedList1(element, sep);
		return inp -> {
			Result<List<O>> r = p1.parse(inp);
			if (r.isError()) return Result.ok(inp, new LinkedList<>());
			return r;
		};
	}
}
