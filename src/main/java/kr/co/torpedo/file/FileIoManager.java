package kr.co.torpedo.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIoManager {
	private FileManager fileManager;

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void writeTextToFile(String text) {
		if (!fileManager.checkAndMakeFile()) {
			throw new NullPointerException("file is null");
		}
		try (FileWriter writer = new FileWriter(fileManager.getResultfile(), true);
				BufferedWriter bWriter = new BufferedWriter(writer);) {
			bWriter.write(text);
			bWriter.newLine();
			bWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String ConvertIntListToString(ArrayList<Integer> list) {
		String str = "";
		for (Integer integer : list) {
			str += Integer.toString(integer) + " ";
		}
		return str;
	}
}
