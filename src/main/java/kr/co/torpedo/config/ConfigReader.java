package kr.co.torpedo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import kr.co.torpedo.exec.ProgramExecutor;

public class ConfigReader {
	private Properties properties;

	public ConfigReader() {
		properties = new Properties();
		loadProp();
	}

	public Properties getProperties() {
		return properties;
	}

	private void loadProp() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDir() {
		if (properties == null || !properties.containsKey("base.dir")) {
			ProgramExecutor.invalidFileLogger.error("properties가 null이거나 base.dir키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 base.dir키가 없습니다.");
		}
		return properties.get("base.dir").toString();
	}

	public int getFileNum() {
		if (properties == null || !properties.containsKey("file.count")) {
			ProgramExecutor.invalidFileLogger.error("properties가 null이거나 file.count키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 file.count키가 없습니다.");
		}
		return Integer.parseInt(properties.get("file.count").toString());
	}

	public int getLottoSet() {
		if (properties == null || !properties.containsKey("file.lottosetcount")) {
			ProgramExecutor.invalidFileLogger.error("properties가 null이거나 file.lottosetcount키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 file.lottosetcount키가 없습니다.");
		}
		return Integer.parseInt(properties.get("file.lottosetcount").toString());
	}

	public int getFolderFileNum() {
		if (properties == null || !properties.containsKey("folder.maxfilecount")) {
			ProgramExecutor.invalidFileLogger.error("properties가 null이거나 folder.maxfilecount키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 folder.maxfilecount키가 없습니다.");
		}
		return Integer.parseInt(properties.get("folder.maxfilecount").toString());
	}

	public int getThreadNum() {
		if (properties == null || !properties.containsKey("thread.count")) {
			ProgramExecutor.invalidFileLogger.error("properties가 null이거나 thread.count키가 없습니다.");
			throw new NullPointerException("properties가 null이거나 thread.count키가 없습니다.");
		}
		return Integer.parseInt(properties.get("thread.count").toString());
	}

}
