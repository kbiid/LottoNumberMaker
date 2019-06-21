package kr.co.torpedo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import kr.co.torpedo.exec.ProgramExecutor;

public class ConfigReader {
	private Properties properties;

	public ConfigReader() {
		properties = new Properties();
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public boolean loadProp(String path) {
		try (FileInputStream fis = new FileInputStream(path)) {
			properties.load(fis);
		} catch (IOException e) {
			ProgramExecutor.invalidFileLogger.error("PropertyLoader IOException!!" + e);
			return false;
		}
		return true;
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
