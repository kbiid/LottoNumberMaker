package kr.co.torpedo.exec;

import kr.co.torpedo.thread.RunnableThread;

public class ThreadExecutor {
	private ProgramExecutor executor;
	private RunnableThread rt;
	private Thread t = null;

	public ThreadExecutor() {
		executor = new ProgramExecutor();
	}

	public void startThread() {
		int threadNum = executor.getPropertyManager().getData().getThreadNum();
		int propertyFileNum = executor.getPropertyManager().getData().getFileNum();
		rt = new RunnableThread();
		rt.setExecutor(executor);

		System.out.println(threadNum);
		
		for (int i = 1; i <= threadNum; i++) {
			if (i == threadNum) {
				executor.setFileNum((propertyFileNum / threadNum) + (propertyFileNum % threadNum));
				rt.setExecutor(executor);
			} else {
				executor.setFileNum((propertyFileNum / threadNum));
				rt.setExecutor(executor);
			}
			t = new Thread(rt);
			t.start();

			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
