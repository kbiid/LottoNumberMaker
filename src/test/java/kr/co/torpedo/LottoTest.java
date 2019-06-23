package kr.co.torpedo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kr.co.torpedo.config.ConfigReader;
import kr.co.torpedo.file.FileManager;
import kr.co.torpedo.lotto.LottoNumberManager;

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
		FileManager fileManager = new FileManager();
		Assertions.assertNotNull(fileManager);
	}

	@Test
	void test_MakeDir() {
		FileManager fileManager = new FileManager();
		fileManager.makePathByDate();
		fileManager.setDir("D:\\test\\" + fileManager.getPath());
		fileManager.makeDirFile();

		Assertions.assertEquals(true, fileManager.checkAndMakeDir());
	}

	@Test
	void test_MakeFile() {
		FileManager fileManager = new FileManager();
		fileManager.makePathByDate();
		fileManager.setDir("D:\\test\\" + fileManager.getPath());
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
