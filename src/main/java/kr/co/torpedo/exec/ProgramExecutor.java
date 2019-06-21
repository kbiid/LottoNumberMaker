package kr.co.torpedo.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.data.Path;
import kr.co.torpedo.lotto.LottoNumberManager;
import kr.co.torpedo.manager.FileRelatedManager;
import kr.co.torpedo.manager.PropertyManager;

public class ProgramExecutor {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager manager;
	private PropertyManager propertyManager;
	private FileRelatedManager fileRelatedManager;
	private int fileNum;

	public ProgramExecutor() {
		manager = new LottoNumberManager();
		propertyManager = new PropertyManager();
		fileRelatedManager = new FileRelatedManager();
		setPropertyPath();
		setPropertyInfo();
		fileNum = 0;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void writeFile() {
		fileRelatedManager.getPathManager().makePathByDate();
		int index = 1;

		if (!checkDir(index)) {
			return;
		}
		index = checkIndexBeforeStart(index);

		if (fileNum == 0) {
			fileNum = propertyManager.getData().getFileNum();
		}
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

	public boolean checkDir(int index) {
		fileRelatedManager.getFileManager().setDir(
				propertyManager.getData().getDir() + fileRelatedManager.getPathManager().getPath() + index + "//");
		fileRelatedManager.getFileManager().makeDirFile();
		return fileRelatedManager.getFileManager().checkAndMakeDir();
	}

	public boolean checkAndMakeResult() {
		fileRelatedManager.getFileManager().makeResultFile();
		if (!fileRelatedManager.getFileManager().checkAndMakeFile()) {
			return false;
		}
		fileRelatedManager.getFileIoManager().setFileManager(fileRelatedManager.getFileManager());
		for (int j = 0; j < propertyManager.getData().getLottoset(); j++) {
			manager.makeLottoNumber();
			fileRelatedManager.getFileIoManager().writeTextToFile(
					fileRelatedManager.getFileIoManager().ConvertIntListToString(manager.getNumberList()));
		}
		return true;
	}

	public int checkIndexForLoop(int index) {
		if (fileRelatedManager.getFileManager().getDirfile().listFiles() != null
				&& fileRelatedManager.getFileManager().getDirfile().listFiles().length == propertyManager.getData()
						.getFolderFileNum()) {
			index++;
		}
		return index;
	}

	public int checkIndexBeforeStart(int index) {
		while (true) {
			if (fileRelatedManager.getFileManager().getDirfile().listFiles() != null
					&& fileRelatedManager.getFileManager().getDirfile().listFiles().length == propertyManager.getData()
							.getFolderFileNum()) {
				index++;
				fileRelatedManager.getFileManager().setDir(propertyManager.getData().getDir()
						+ fileRelatedManager.getPathManager().getPath() + index + "//");
				fileRelatedManager.getFileManager().makeDirFile();
				if (!fileRelatedManager.getFileManager().checkAndMakeDir()) {
					break;
				}
			} else {
				break;
			}
		}
		return index;
	}

	private void setPropertyPath() {
		propertyManager.getLoader().loadProp(Path.PROPERTY.getName());
		propertyManager.getReader().setProperties(propertyManager.getLoader().getProperties());
	}

	private void setPropertyInfo() {
		propertyManager.getData().setDir(propertyManager.getReader().getDir());
		propertyManager.getData().setFileNum(propertyManager.getReader().getFileNum());
		propertyManager.getData().setLottoset(propertyManager.getReader().getLottoSet());
		propertyManager.getData().setFolderFileNum(propertyManager.getReader().getFolderFileNum());
		propertyManager.getData().setThreadNum(propertyManager.getReader().getThreadNum());
	}
}
