package kr.co.torpedo.lottonumber;

import java.util.ArrayList;

public class LottoNumberManager {
	private ArrayList<Integer> numberList;

	public LottoNumberManager() {
		numberList = new ArrayList<>();
	}

	public ArrayList<Integer> getNumberList() {
		return numberList;
	}

	public void makeLottoNumber() {
		numberList.clear();
		while (numberList.size() < 6) {
			int number = getRandomNumber(45);
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

	private int getRandomNumber(int num) {
		int number = (int) (Math.random() * num) + 1;
		return number;
	}
}
