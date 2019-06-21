package kr.co.torpedo.file;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PathManager {
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void makePathByDate() {
		path="";
		
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
