package com.bowen.austrianlotto.lotto;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for Austrian Lotto.
 */
public class AppTest extends TestCase {

	private AustrianLotto austrianLotto;

	@Before
	public void setUp() {
		austrianLotto = new AustrianLotto();
	}

	@Test
	public void testLottoResultExampleOne() {
		String drawing = "3 11 18 23 37 45";
		String[] picks = { "4 7 14 30 33 35", "1 11 12 25 37 38", "11 18 19 20 21 22" };
		int[] expectedResult = { 1, 0, 2, 0, 0, 0, 0 };
		int[] actualResult = austrianLotto.evaluate(drawing, picks);
		assertArrayEquals(actualResult, expectedResult);
	}

	@Test
	public void testLottoAllMatch() {
		String drawing = "3 11 18 23 37 45";
		String[] picks = { "3 11 18 23 37 45" };
		int[] expectedResult = { 0, 0, 0, 0, 0, 0, 1 };
		int[] actualResult = austrianLotto.evaluate(drawing, picks);
		assertArrayEquals(actualResult, expectedResult);
	}

	@Test
	public void testLottoThreeMatch() {
		String drawing = "42 26 33 2 13 14";
		String[] picks = { "13 4 36 42 26 12" };
		int[] expectedResult = { 0, 0, 0, 1, 0, 0, 0 };
		int[] actualResult = austrianLotto.evaluate(drawing, picks);
		assertArrayEquals(actualResult, expectedResult);
	}

	@Test
	public void testLottoWithNoMatch() {
		String drawing = "3 11 18 23 37 45";
		String[] picks = { "1 2 4 5 6 7", "8 9 10 12 13 14", "15 16 17 19 20 21" };
		int[] expectedResult = { 3, 0, 0, 0, 0, 0, 0 };
		int[] actualResult = austrianLotto.evaluate(drawing, picks);
		assertArrayEquals(actualResult, expectedResult);
	}

	@Test
	public void testLottoOneMatch() {
		String drawing = "3 11 18 23 37 45";
		String[] picks = { "2 10 17 23 38 44" };
		int[] expectedResult = { 0, 1, 0, 0, 0, 0, 0 };
		int[] actualResult = austrianLotto.evaluate(drawing, picks);
		assertArrayEquals(actualResult, expectedResult);
	}

}
