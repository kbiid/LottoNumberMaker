package kr.co.torpedo.propertymodule;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	private Properties properties;

	public PropertyLoader() {
		properties = new Properties();
	}

	public Properties getProperties() {
		return properties;
	}

	public boolean loadProp(String path) {
		try (FileInputStream fis = new FileInputStream(path)) {
			properties.load(fis);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
