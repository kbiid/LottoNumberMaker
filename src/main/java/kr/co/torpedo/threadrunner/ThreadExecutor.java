package kr.co.torpedo.threadrunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.co.torpedo.writer.ContentWriter;

public class ThreadExecutor {
	private ContentWriter executor;
	private RunnableThread rt;
	private ExecutorService exService;

	public ThreadExecutor() {
		executor = new ContentWriter();
	}

	public void start() {
		int threadNum = executor.getConfigReader().getThreadNum();
		int propertyFileNum = executor.getConfigReader().getFileNum();
		int fileNum;
		exService = Executors.newFixedThreadPool(threadNum);
		rt = new RunnableThread();

		for (int i = 1; i <= threadNum; i++) {
			fileNum = getFileNum(threadNum, propertyFileNum, i);
			rt = new RunnableThread();
			rt.setExecutor(executor);
			rt.setFileNum(fileNum);
			exService.execute(rt);
		}
		exService.shutdown();
	}

	private int getFileNum(int threadNum, int propertyFileNum, int i) {
		int fileNum;
		if (i == threadNum) {
			fileNum = ((propertyFileNum / threadNum) + (propertyFileNum % threadNum));
		} else {
			fileNum = ((propertyFileNum / threadNum));
		}
		return fileNum;
	}
}
