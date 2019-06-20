package kr.co.torpedo.lotto;

public class RandomNumberMaker {
	private int num;

	public RandomNumberMaker(int num) {
		this.num = num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRandomNumber() {
		int number = (int) (Math.random() * num) + 1;
		return number;
	}
}
