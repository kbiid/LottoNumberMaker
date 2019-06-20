package kr.co.torpedo.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import kr.co.torpedo.exec.ProgramExector;

public class FileManager {
	private File dirfile;
	private File resultfile;
	private String fileName;
	private String dir;

	public void makeDirFile() {
		dirfile = new File(getDir());
	}

	public void makeResultFile() {
		resultfile = new File(getDir() + getUUID() + ".txt");
	}

	public void setResultFile(String str) {
		resultfile = new File(getDir() + str);
	}

	public File getDirfile() {
		return dirfile;
	}

	public void setDirfile(File dirfile) {
		this.dirfile = dirfile;
	}

	public File getResultfile() {
		return resultfile;
	}

	public void setResultfile(File makefile) {
		this.resultfile = makefile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean checkAndMakeDir() {
		if (dirfile == null) { // dirfile 변수가 null 일때
			ProgramExector.invalidFileLogger.error("dirfile is NullException!");
			return false;
		}
		if (!dirfile.exists()) { // 폴더가 없는 경우
			if (dirfile.mkdirs()) {
				ProgramExector.invalidFileLogger.info("folder make success");
			} else {
				ProgramExector.invalidFileLogger.error("folder make fail");
				return false;
			}
		} else {
			ProgramExector.invalidFileLogger.info("folder already exist");
		}
		return true;
	}

	public boolean checkAndMakeFile() {
		if (resultfile == null) { // makefile 변수가 null 일때
			ProgramExector.invalidFileLogger.error("makeFile is NullException!");
			return false;
		}
		if (!resultfile.exists()) { // 파일이 없는 경우
			try {
				if (resultfile.createNewFile()) {
					ProgramExector.invalidFileLogger.info("File make Success ");
				} else {
					ProgramExector.invalidFileLogger.error("FileManager make File NullException!");
					return false;
				}
			} catch (IOException e) {
				ProgramExector.invalidFileLogger.error("Serializer Exception : " + e);
			}
		} else {
			ProgramExector.invalidFileLogger.info("File exist");
		}
		return true;
	}

	public String getUUID() {
		return UUID.randomUUID().toString();
	}
}
