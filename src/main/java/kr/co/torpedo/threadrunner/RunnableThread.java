package kr.co.torpedo.threadrunner;

import kr.co.torpedo.writer.ContentWriter;

public class RunnableThread implements Runnable{
	private ContentWriter executor;
	private int fileNum;
	
	public void setExecutor(ContentWriter executor) {
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
