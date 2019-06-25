package kr.co.torpedo.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class FileTextWriter {
	private File dirfile;
	private File resultfile;
	private String dir;
	private String path;

	public String getPath() {
		return path;
	}

	public void makeDirFile() {
		dirfile = new File(dir);
	}

	public void makeResultFile() {
		resultfile = new File(dir + getUUID() + ".txt");
	}

	public File getDirfile() {
		return dirfile;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean checkAndMakeDir() {
		if (dirfile == null) { // dirfile 변수가 null 일때
			ContentWriter.invalidFileLogger.error("dirfile is NullException!");
			return false;
		}
		if (!(dirfile.exists())) { // 폴더가 없는 경우
			if (!dirfile.mkdirs()) {
				ContentWriter.invalidFileLogger.error("folder make fail");
				return false;
			}
		}
		return true;
	}

	public boolean checkAndMakeFile() {
		if (resultfile == null) { // makefile 변수가 null 일때
			ContentWriter.invalidFileLogger.error("makeFile is NullException!");
			return false;
		}
		if (!resultfile.exists()) { // 파일이 없는 경우
			try {
				if (!resultfile.createNewFile()) {
					ContentWriter.invalidFileLogger.error("FileManager make File NullException!");
					return false;
				}
			} catch (IOException e) {
				ContentWriter.invalidFileLogger.error("Serializer Exception : " + e);
			}
		}
		return true;
	}

	private String getUUID() {
		return UUID.randomUUID().toString();
	}

	public void writeTextToFile(String text) {
		if (!checkAndMakeFile()) {
			ContentWriter.invalidFileLogger.error("file is null");
			throw new NullPointerException("file is null");
		}
		try (FileWriter writer = new FileWriter(resultfile, true);
				BufferedWriter bWriter = new BufferedWriter(writer);) {
			bWriter.write(text);
			bWriter.newLine();
			bWriter.flush();
		} catch (IOException e) {
			ContentWriter.invalidFileLogger.error("FileIoManager IOException !!");
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
