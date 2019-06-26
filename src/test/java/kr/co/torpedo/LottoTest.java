package kr.co.torpedo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kr.co.torpedo.config.ConfigReader;
import kr.co.torpedo.lottonumber.LottoNumberManager;
import kr.co.torpedo.writer.FileTextWriter;

class LottoTest {

	@Test
	void test_Lotto() {
		LottoNumberManager manager = new LottoNumberManager();
		manager.makeLottoNumber();
		manager.printList();
		Assertions.assertEquals(6, manager.getNumberList().size());
	}

	@Test
	void test_NumberOverlap() {
		LottoNumberManager manager = new LottoNumberManager();
		manager.makeLottoNumber();
		Assertions.assertEquals(false, manager.checkOverlap());
	}

	@Test
	void test_FileMaker() {
		FileTextWriter fileManager = new FileTextWriter();
		Assertions.assertNotNull(fileManager);
	}

	@Test
	void test_MakeDir() {
		FileTextWriter fileManager = new FileTextWriter();
		fileManager.makePathByDate();
		fileManager.setFileDir("D:\\test\\" + fileManager.getPathByDate());
		fileManager.makeDirFile();

		Assertions.assertEquals(true, fileManager.checkAndMakeDir());
	}

	@Test
	void test_MakeFile() {
		FileTextWriter fileManager = new FileTextWriter();
		fileManager.makePathByDate();
		fileManager.setFileDir("D:\\test\\" + fileManager.getPathByDate());
		fileManager.makeResultFile();

		Assertions.assertEquals(true, fileManager.checkAndMakeFile());
	}

	@Test
	void test_PropertyLoader() {
		ConfigReader loader = new ConfigReader();
		Assertions.assertNotNull(loader.getProperties());
	}

	@Test
	void test_PropertyReader() {
		ConfigReader loader = new ConfigReader();
		Assertions.assertEquals("D:\\test\\", loader.getDir());
	}
}
