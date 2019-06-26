package kr.co.torpedo.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.config.ConfigReader;
import kr.co.torpedo.lottonumber.LottoNumberManager;

public class ContentWriter {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager lottoNumberManager;
	private ConfigReader configReader;
	private FileTextWriter fileManager;

	public ContentWriter() {
		lottoNumberManager = new LottoNumberManager();
		configReader = new ConfigReader();
		fileManager = new FileTextWriter();
	}

	public ConfigReader getConfigReader() {
		return configReader;
	}

	public synchronized void writeFile(int fileNum) {
		fileManager.makePathByDate();
		int index = 1;

		index = checkIndexBeforeStart(index);

		for (int i = 1; i <= fileNum; i++) {
			if (!checkFileDir(index)) {
				break;
			}
			if (!checkAndMakeResult()) {
				break;
			}
			index = checkIndexForLoop(index);
		}
	}

	private boolean checkFileDir(int index) {
		String str = String.format("%04d", index);
		fileManager.setFileDir(configReader.getDir() + fileManager.getPathByDate() + str + "/");
		fileManager.makeDirFile();
		return fileManager.checkAndMakeDir();
	}

	private boolean checkAndMakeResult() {
		fileManager.makeResultFile();
		if (!fileManager.checkAndMakeFile()) {
			return false;
		}
		for (int j = 0; j < configReader.getLottoSet(); j++) {
			lottoNumberManager.makeLottoNumber();
			fileManager.writeTextToFile(fileManager.ConvertIntListToString(lottoNumberManager.getNumberList()));
		}
		return true;
	}

	private int checkIndexForLoop(int index) {
		if (fileManager.getDirfile().listFiles() != null
				&& fileManager.getDirfile().listFiles().length == configReader.getFolderFileNum()) {
			index++;
		}
		return index;
	}

	private int checkIndexBeforeStart(int index) {
		if (!checkFileDir(index)) {
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
				if (!checkFileDir(index)) {
					break;
				}
			} else {
				break;
			}
		}
		return index;
	}
}
