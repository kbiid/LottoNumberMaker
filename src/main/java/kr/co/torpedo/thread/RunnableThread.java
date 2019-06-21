package kr.co.torpedo.thread;

import kr.co.torpedo.exec.ProgramExecutor;

public class RunnableThread implements Runnable{
	private ProgramExecutor executor;
	
	public void setExecutor(ProgramExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void run() {
		executor.writeFile();
	}

}
