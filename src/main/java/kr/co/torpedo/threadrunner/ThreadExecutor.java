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

	public void startThread() {
		int threadNum = executor.getConfigReader().getThreadNum();
		int propertyFileNum = executor.getConfigReader().getFileNum();
		exService = Executors.newFixedThreadPool(threadNum);
		rt = new RunnableThread();
		int fileNum;

		for (int i = 1; i <= threadNum; i++) {
			if (i == threadNum) {
				fileNum = ((propertyFileNum / threadNum) + (propertyFileNum % threadNum));
			} else {
				fileNum = ((propertyFileNum / threadNum));
			}
			rt = new RunnableThread();
			rt.setExecutor(executor);
			rt.setFileNum(fileNum);
			exService.execute(rt);
		}
		exService.shutdown();
	}
}
