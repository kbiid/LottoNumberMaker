package kr.co.torpedo.lotto;

import java.util.ArrayList;

public class LottoNumberManager {
	private ArrayList<Integer> numberList;
	private RandomNumberMaker numberMaker;

	public LottoNumberManager() {
		numberList = new ArrayList<>();
	}

	public ArrayList<Integer> getNumberList() {
		return numberList;
	}

	public void makeLottoNumber() {
		numberMaker = new RandomNumberMaker(45);
		numberList.clear();
		while (numberList.size() < 6) {
			int number = numberMaker.getRandomNumber();
			if (!numberList.contains(number)) {
				numberList.add(number);
			}
		}
	}

	public boolean checkOverlap() {
		for (int i = 0; i < numberList.size(); i++) {
			for (int j = 0; j < numberList.size(); j++) {
				if (i != j && numberList.get(j) == numberList.get(i)) {
					return true;
				}
			}
		}
		return false;
	}

	public void printList() {
		for (Integer integer : numberList) {
			System.out.println(integer);
		}
	}
}
