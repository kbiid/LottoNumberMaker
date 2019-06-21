package kr.co.torpedo.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.config.ConfigReader;
import kr.co.torpedo.lotto.LottoNumberManager;
import kr.co.torpedo.manager.FileRelatedManager;

public class ProgramExecutor {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager manager;
	private ConfigReader configReader;
	private FileRelatedManager fileRelatedManager;

	public ProgramExecutor() {
		manager = new LottoNumberManager();
		configReader = new ConfigReader();
		fileRelatedManager = new FileRelatedManager();
	}

	public ConfigReader getConfigReader() {
		return configReader;
	}

	public void setConfigReader(ConfigReader configReader) {
		this.configReader = configReader;
	}

	public LottoNumberManager getManager() {
		return manager;
	}

	public FileRelatedManager getFileRelatedManager() {
		return fileRelatedManager;
	}

	public void setManager(LottoNumberManager manager) {
		this.manager = manager;
	}

	public void setFileRelatedManager(FileRelatedManager fileRelatedManager) {
		this.fileRelatedManager = fileRelatedManager;
	}

	public synchronized void writeFile(int fileNum) {
		fileRelatedManager.getPathManager().makePathByDate();
		int index = 1;

		index = checkIndexBeforeStart(index);

		for (int i = 1; i <= fileNum; i++) {
			if (!checkDir(index)) {
				break;
			}
			if (!checkAndMakeResult()) {
				break;
			}
			index = checkIndexForLoop(index);
		}
	}

	public synchronized boolean checkDir(int index) {
		String str = String.format("%04d", index);
		fileRelatedManager.getFileManager()
				.setDir(configReader.getDir() + fileRelatedManager.getPathManager().getPath() + str + "/");
		fileRelatedManager.getFileManager().makeDirFile();
		return fileRelatedManager.getFileManager().checkAndMakeDir();
	}

	public boolean checkAndMakeResult() {
		fileRelatedManager.getFileManager().makeResultFile();
		if (!fileRelatedManager.getFileManager().checkAndMakeFile()) {
			return false;
		}
		fileRelatedManager.getFileIoManager().setFileManager(fileRelatedManager.getFileManager());
		for (int j = 0; j < configReader.getLottoSet(); j++) {
			manager.makeLottoNumber();
			fileRelatedManager.getFileIoManager().writeTextToFile(
					fileRelatedManager.getFileIoManager().ConvertIntListToString(manager.getNumberList()));
		}
		return true;
	}

	public int checkIndexForLoop(int index) {
		if (fileRelatedManager.getFileManager().getDirfile().listFiles() != null
				&& fileRelatedManager.getFileManager().getDirfile().listFiles().length == configReader
						.getFolderFileNum()) {
			index++;
		}
		return index;
	}

	public int checkIndexBeforeStart(int index) {
		if (!checkDir(index)) {
			try {
				throw new Exception("checkIndexBeforeStart checkDir fail");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		while (true) {
			if (fileRelatedManager.getFileManager().getDirfile().listFiles() != null
					&& fileRelatedManager.getFileManager().getDirfile().listFiles().length == configReader
							.getFolderFileNum()) {
				index++;
				if (!checkDir(index)) {
					break;
				}
			} else {
				break;
			}
		}
		return index;
	}
}
