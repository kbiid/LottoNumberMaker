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
		for (int i = 0; i < 6; i++) {
			numberList.add(numberMaker.getRandomNumber());
			i = checkOverlap(i);
		}
	}

	public int checkOverlap(int i) {
		for (int j = 0; j < i; j++) {
			if (numberList.get(i) == numberList.get(j)) {
				i--;
				break;
			}
		}
		return i;
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
