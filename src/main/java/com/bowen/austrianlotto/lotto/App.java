package com.bowen.austrianlotto.lotto;


public class App {
	
	public static void main(String[] args) {
		AustrianLotto lotto = new AustrianLotto();
		lotto.evaluate(lotto.randomDraw(), lotto.randomPick());
	}
	
}
