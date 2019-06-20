package kr.co.torpedo.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.torpedo.data.Path;
import kr.co.torpedo.file.FileIoManager;
import kr.co.torpedo.file.FileManager;
import kr.co.torpedo.file.PathManager;
import kr.co.torpedo.lotto.LottoNumberManager;
import kr.co.torpedo.propertymodule.PropertyLoader;
import kr.co.torpedo.propertymodule.PropertyReader;

public class ProgramExector {
	public static final Logger invalidFileLogger = LoggerFactory.getLogger("log.invalid");
	private LottoNumberManager manager;
	private FileManager fileManager;
	private PathManager pathManager;
	private FileIoManager fileIoManager;
	private int fileNum, lottoset, folderFileNum, threadNum, index;
	private String dir;
	private PropertyLoader loader;
	private PropertyReader reader;

	public ProgramExector() {
		manager = new LottoNumberManager();
		fileManager = new FileManager();
		pathManager = new PathManager();
		loader = new PropertyLoader();
		reader = new PropertyReader();
		fileIoManager = new FileIoManager();
	}

	public void writeFile() {
		setPropertyPath();
		setPropertyInfo();
		pathManager.makePathByDate();
		index = 1;

		for (int i = 1; i <= fileNum; i++) {
			if ((i % folderFileNum) == 0 && fileManager.getDirfile().listFiles() != null
					&& fileManager.getDirfile().listFiles().length >= folderFileNum) {
				index++;
			}
			fileManager.setDir(dir + pathManager.getPath() + index + "//");
			fileManager.makeDirFile();
			if (!fileManager.checkAndMakeDir()) {
				break;
			}
			while (true) {
				if (fileManager.getDirfile().listFiles() != null
						&& fileManager.getDirfile().listFiles().length >= folderFileNum) {
					index++;
					fileManager.setDir(dir + pathManager.getPath() + index + "//");
					fileManager.makeDirFile();
					if (!fileManager.checkAndMakeDir()) {
						break;
					}
				} else {
					break;
				}
			}
			fileManager.makeResultFile();
			if (!fileManager.checkAndMakeFile()) {
				break;
			}
			fileIoManager.setFileManager(fileManager);
			for (int j = 0; j < lottoset; j++) {
				manager.makeLottoNumber();
				fileIoManager.writeTextToFile(fileIoManager.ConvertIntListToString(manager.getNumberList()));
			}
		}
	}
	
	private void setPropertyPath() {
		loader.loadProp(Path.PROPERTY.getName());
		reader.setProperties(loader.getProperties());
	}

	private void setPropertyInfo() {
		dir = reader.getDir();
		fileNum = reader.getFileNum();
		lottoset = reader.getLottoSet();
		folderFileNum = reader.getFolderFileNum();
		threadNum = reader.getThreadNum();
	}
}
