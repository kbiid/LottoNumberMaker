package kr.co.torpedo.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.data.Path;
import kr.co.torpedo.lotto.LottoNumberManager;
import kr.co.torpedo.manager.FileRelatedManager;
import kr.co.torpedo.manager.PropertyManager;

public class ProgramExector {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager manager;
	private PropertyManager propertyManager;
	private FileRelatedManager fileRelatedManager;

	public ProgramExector() {
		manager = new LottoNumberManager();
		propertyManager = new PropertyManager();
		fileRelatedManager = new FileRelatedManager();
	}

	public void writeFile() {
		setPropertyPath();
		setPropertyInfo();
		fileRelatedManager.getPathManager().makePathByDate();
		int index = 1;

		for (int i = 1; i <= propertyManager.getData().getFileNum(); i++) {
			if ((i % propertyManager.getData().getFolderFileNum()) == 0
					&& fileRelatedManager.getFileManager().getDirfile().listFiles() != null
					&& fileRelatedManager.getFileManager().getDirfile().listFiles().length >= propertyManager.getData()
							.getFolderFileNum()) {
				index++;
			}
			fileRelatedManager.getFileManager().setDir(
					propertyManager.getData().getDir() + fileRelatedManager.getPathManager().getPath() + index + "//");
			fileRelatedManager.getFileManager().makeDirFile();
			if (!fileRelatedManager.getFileManager().checkAndMakeDir()) {
				break;
			}
			while (true) {
				if (fileRelatedManager.getFileManager().getDirfile().listFiles() != null
						&& fileRelatedManager.getFileManager().getDirfile().listFiles().length >= propertyManager
								.getData().getFolderFileNum()) {
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
			fileRelatedManager.getFileManager().makeResultFile();
			if (!fileRelatedManager.getFileManager().checkAndMakeFile()) {
				break;
			}
			fileRelatedManager.getFileIoManager().setFileManager(fileRelatedManager.getFileManager());
			for (int j = 0; j < propertyManager.getData().getLottoset(); j++) {
				manager.makeLottoNumber();
				fileRelatedManager.getFileIoManager().writeTextToFile(
						fileRelatedManager.getFileIoManager().ConvertIntListToString(manager.getNumberList()));
			}
		}
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
