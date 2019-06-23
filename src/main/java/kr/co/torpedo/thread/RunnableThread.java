package kr.co.torpedo.thread;

import kr.co.torpedo.executor.ProgramExecutor;

public class RunnableThread implements Runnable{
	private ProgramExecutor executor;
	private int fileNum;
	
	public void setExecutor(ProgramExecutor executor) {
		this.executor = executor;
	}
	
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	@Override
	public void run() {
		executor.writeFile(fileNum);
	}
}
