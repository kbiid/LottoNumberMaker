package kr.co.torpedo.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.config.ConfigReader;
import kr.co.torpedo.file.FileManager;
import kr.co.torpedo.lotto.LottoNumberManager;

public class ProgramExecutor {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager manager;
	private ConfigReader configReader;
	private FileManager fileManager;

	public ProgramExecutor() {
		manager = new LottoNumberManager();
		configReader = new ConfigReader();
		fileManager = new FileManager();
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

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setManager(LottoNumberManager manager) {
		this.manager = manager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public synchronized void writeFile(int fileNum) {
		fileManager.makePathByDate();
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
		fileManager.setDir(configReader.getDir() + fileManager.getPath() + str + "/");
		fileManager.makeDirFile();
		return fileManager.checkAndMakeDir();
	}

	public boolean checkAndMakeResult() {
		fileManager.makeResultFile();
		if (!fileManager.checkAndMakeFile()) {
			return false;
		}
		for (int j = 0; j < configReader.getLottoSet(); j++) {
			manager.makeLottoNumber();
			fileManager.writeTextToFile(fileManager.ConvertIntListToString(manager.getNumberList()));
		}
		return true;
	}

	public int checkIndexForLoop(int index) {
		if (fileManager.getDirfile().listFiles() != null
				&& fileManager.getDirfile().listFiles().length == configReader.getFolderFileNum()) {
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
			if (fileManager.getDirfile().listFiles() != null
					&& fileManager.getDirfile().listFiles().length == configReader.getFolderFileNum()) {
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
