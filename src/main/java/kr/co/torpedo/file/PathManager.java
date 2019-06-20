package kr.co.torpedo.file;

import java.util.Calendar;

public class PathManager {
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void makePathByDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		path = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(date) + "/";
	}
}
