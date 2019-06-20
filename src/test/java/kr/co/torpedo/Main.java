package kr.co.torpedo;

import kr.co.torpedo.exec.ProgramExector;

public class Main {

	public static void main(String[] args) {
		ProgramExector.invalidFileLogger.info("Program Start");
		ProgramExector executor = new ProgramExector();
		executor.writeFile();
		ProgramExector.invalidFileLogger.info("Program End");
	}

}
