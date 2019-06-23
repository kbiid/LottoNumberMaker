package kr.co.torpedo.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import kr.co.torpedo.executor.ProgramExecutor;

public class FileManager {
	private File dirfile;
	private File resultfile;
	private String fileName;
	private String dir;
	private String path;

	public String getPath() {
		return path;
	}

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
			ProgramExecutor.invalidFileLogger.error("dirfile is NullException!");
			return false;
		}
		if (!(dirfile.exists())) { // 폴더가 없는 경우
			if (!dirfile.mkdirs()) {
				ProgramExecutor.invalidFileLogger.error("folder make fail");
				return false;
			}
		}
		return true;
	}

	public boolean checkAndMakeFile() {
		if (resultfile == null) { // makefile 변수가 null 일때
			ProgramExecutor.invalidFileLogger.error("makeFile is NullException!");
			return false;
		}
		if (!resultfile.exists()) { // 파일이 없는 경우
			try {
				if (!resultfile.createNewFile()) {
					ProgramExecutor.invalidFileLogger.error("FileManager make File NullException!");
					return false;
				}
			} catch (IOException e) {
				ProgramExecutor.invalidFileLogger.error("Serializer Exception : " + e);
			}
		}
		return true;
	}

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	public void writeTextToFile(String text) {
		if (!checkAndMakeFile()) {
			ProgramExecutor.invalidFileLogger.error("file is null");
			throw new NullPointerException("file is null");
		}
		try (FileWriter writer = new FileWriter(getResultfile(), true);
				BufferedWriter bWriter = new BufferedWriter(writer);) {
			bWriter.write(text);
			bWriter.newLine();
			bWriter.flush();
		} catch (IOException e) {
			ProgramExecutor.invalidFileLogger.error("FileIoManager IOException !!");
			throw new NullPointerException("FileIoManager IOException !!" + e);
		}
	}

	public String ConvertIntListToString(ArrayList<Integer> list) {
		String str = "";
		for (Integer integer : list) {
			str += Integer.toString(integer) + " ";
		}
		return str;
	}

	public void makePathByDate() {
		path = "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String str = format.format(new Date());
		path += str + "/";

		format = new SimpleDateFormat("MM");
		str = format.format(new Date());
		path += str + "/";

		format = new SimpleDateFormat("dd");
		str = format.format(new Date());
		path += str + "/";
	}
}
