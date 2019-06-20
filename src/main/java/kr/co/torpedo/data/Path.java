package kr.co.torpedo.data;

public enum Path {
	PROPERTY("D:/eclipse_workspace/LottoMaker/src/main/resources/application.properties");

	final private String name;

	private Path(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
