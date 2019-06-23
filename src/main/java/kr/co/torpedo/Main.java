package kr.co.torpedo;

import kr.co.torpedo.threadrunner.ThreadExecutor;

public class Main {
	public static void main(String[] args) {
		System.out.println("Program Start");
		long start, end;
		start = System.currentTimeMillis();
		ThreadExecutor tExecutor = new ThreadExecutor();
		tExecutor.startThread();
		end = System.currentTimeMillis();
		System.out.println(" 소요 시간 : " + (end - start) / 1000.0);
	}
}
