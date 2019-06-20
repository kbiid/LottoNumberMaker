package kr.co.torpedo.propertymodule;

import java.util.Properties;

import kr.co.torpedo.exec.ProgramExector;

public class PropertyReader {
	private Properties properties;

	public String getDir() {
		if (properties == null || !properties.containsKey("dir")) {
			ProgramExector.invalidFileLogger.error("properties가 null이거나 file.dir키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 file.dir키가 없습니다.");
		}
		return properties.get("dir").toString();
	}

	public int getFileNum() {
		if (properties == null || !properties.containsKey("file.num")) {
			ProgramExector.invalidFileLogger.error("properties가 null이거나 file.num키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 file.num키가 없습니다.");
		}
		return Integer.parseInt(properties.get("file.num").toString());
	}

	public int getLottoSet() {
		if (properties == null || !properties.containsKey("file.lottoset")) {
			ProgramExector.invalidFileLogger.error("properties가 null이거나 file.lottoset키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 file.lottoset키가 없습니다.");
		}
		return Integer.parseInt(properties.get("file.lottoset").toString());
	}

	public int getFolderFileNum() {
		if (properties == null || !properties.containsKey("folder.filenum")) {
			ProgramExector.invalidFileLogger.error("properties가 null이거나 folder.filenum키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 folder.filenum키가 없습니다.");
		}
		return Integer.parseInt(properties.get("folder.filenum").toString());
	}

	public int getThreadNum() {
		if (properties == null || !properties.containsKey("thread.num")) {
			ProgramExector.invalidFileLogger.error("properties가 null이거나 thread.num키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 thread.num키가 없습니다.");
		}
		return Integer.parseInt(properties.get("thread.num").toString());
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
