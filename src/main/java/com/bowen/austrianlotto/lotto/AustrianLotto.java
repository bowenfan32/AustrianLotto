package com.bowen.austrianlotto.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AustrianLotto {

	public static final int DRAW_SIZE = 6;
	public static final int MIN_LOTTO_NUM = 1;
	public static final int MAX_LOTTO_NUM = 45;
	public static final int MAX_LOTTO_PICKS = 50;

	private Random random = new Random();

	public String randomDraw() {
		String drawing = "";
		StringBuilder sb = new StringBuilder();

		// Random an array of 6 distinct numbers
		int[] n = random.ints(MIN_LOTTO_NUM, MAX_LOTTO_NUM).distinct().limit(DRAW_SIZE).toArray();

		// Loop through array and append array elements into a String
		for (int i = 0; i < n.length; i++) {
			drawing = sb.append(n[i] + " ").toString();
			drawing = drawing.trim(); // Ensure character limit do not go beyond 17
		}
		return drawing;
	}

	public String[] randomPick() {
		// Randomize the number of picks array
		String[] picks = new String[random.nextInt(MAX_LOTTO_PICKS) + 1];
		String num = "";

		for (int i = 0; i < picks.length; i++) {
			StringBuilder sb = new StringBuilder();
			// Random 6 distinct number into an array
			int[] n = random.ints(MIN_LOTTO_NUM, MAX_LOTTO_NUM).distinct().limit(DRAW_SIZE).toArray();
			
			// Loop through array and append array elements into a String
			for (int j = 0; j < n.length; j++) {
				num = sb.append(n[j] + " ").toString();
				num = num.trim(); // Ensure character limit do not go beyond 17
			}
			// Add each String sets into an the picks array
			picks[i] = num;
		}
		return picks;
	}

	public int[] evaluate(String drawing, String[] picks) {

		int[] result = new int[7];
		// Read the drawing numbers from the String and add them to a List
		Scanner drawScanner = new Scanner(drawing);
		List<Integer> drawList = new ArrayList<Integer>();
		while (drawScanner.hasNextInt()) {
			drawList.add(drawScanner.nextInt());
		}
		drawScanner.close();

		// Read the picks numbers from the String and add them to a List
		for (int i = 0; i < picks.length; i++) {
			Scanner pickScanner = new Scanner(picks[i]);
			List<Integer> pickList = new ArrayList<Integer>();
			while (pickScanner.hasNextInt()) {
				pickList.add(pickScanner.nextInt());
			}
			pickScanner.close();

			int numOfMatches = 0;
			// Sort the picks list in order perform a binary search
			// to achieve a time complexity of O(n*log(n))
			for (int j = 0; j < drawList.size(); j++) {
				Collections.sort(pickList);
				if (binarySearch(pickList, drawList.get(j))) {
					numOfMatches++;
				}
			}

			// Slower alternative of O(n^2)
//			for (int j = 0; j < drawList.size(); j++) {
//				for (int k = 0; k < pickList.size(); k++) {
//					if (drawList.get(j) == pickList.get(k)) {
//						numOfMatches++;
//					}
//				}
//			}

			// Increments the result found
			result[numOfMatches]++;

			// For visualization
			System.out.println("Drawing: " + drawList.toString());
			System.out.println("Picks: " + pickList.toString());
			System.out.println("Number of matches: " + numOfMatches + "\r");
		}

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}

		return result;
	}

	public boolean binarySearch(List<Integer> pickList, int key) {
		int low = 0;
		int high = pickList.size() - 1;
		while (high >= low) {
			int mid = low + (high - low) / 2;
			if (pickList.get(mid) > key)
				high = mid - 1;
			else if (pickList.get(mid) < key)
				low = mid + 1;
			else
				return true;
		}
		return false;
	}
}
