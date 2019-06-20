package kr.co.torpedo.manager;

import kr.co.torpedo.file.FileIoManager;
import kr.co.torpedo.file.FileManager;
import kr.co.torpedo.file.PathManager;

public class FileRelatedManager {
	private PathManager pathManager;
	private FileManager fileManager;
	private FileIoManager fileIoManager;

	public FileRelatedManager() {
		fileManager = new FileManager();
		pathManager = new PathManager();
		fileIoManager = new FileIoManager();
	}

	public PathManager getPathManager() {
		return pathManager;
	}

	public void setPathManager(PathManager pathManager) {
		this.pathManager = pathManager;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public FileIoManager getFileIoManager() {
		return fileIoManager;
	}

	public void setFileIoManager(FileIoManager fileIoManager) {
		this.fileIoManager = fileIoManager;
	}
}
