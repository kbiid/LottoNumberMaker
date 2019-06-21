package kr.co.torpedo;

import kr.co.torpedo.exec.ProgramExecutor;
import kr.co.torpedo.exec.ThreadExecutor;

public class Main {

	public static void main(String[] args) {
		long start, end;
		ProgramExecutor.invalidFileLogger.info("Program Start");
		start = System.currentTimeMillis();
		ThreadExecutor tExecutor = new ThreadExecutor();
		tExecutor.startThread();
		end = System.currentTimeMillis();
		ProgramExecutor.invalidFileLogger.info("Program End");
		System.out.println(" 소요 시간 : " + (end - start) / 1000.0);
	}

}
