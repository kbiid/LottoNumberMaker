package kr.co.torpedo.manager;

import kr.co.torpedo.model.PropertiesData;
import kr.co.torpedo.propertymodule.PropertyLoader;
import kr.co.torpedo.propertymodule.PropertyReader;

public class PropertyManager {
	private PropertiesData data;
	private PropertyLoader loader;
	private PropertyReader reader;

	public PropertyManager() {
		loader = new PropertyLoader();
		reader = new PropertyReader();
		data = new PropertiesData();
	}

	public PropertiesData getData() {
		return data;
	}

	public void setData(PropertiesData data) {
		this.data = data;
	}

	public PropertyLoader getLoader() {
		return loader;
	}

	public void setLoader(PropertyLoader loader) {
		this.loader = loader;
	}

	public PropertyReader getReader() {
		return reader;
	}

	public void setReader(PropertyReader reader) {
		this.reader = reader;
	}
}
