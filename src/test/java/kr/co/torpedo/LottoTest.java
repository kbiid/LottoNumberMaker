package kr.co.torpedo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kr.co.torpedo.file.FileManager;
import kr.co.torpedo.file.PathManager;
import kr.co.torpedo.lotto.LottoNumberManager;
import kr.co.torpedo.propertymodule.PropertyLoader;
import kr.co.torpedo.propertymodule.PropertyReader;

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
		PathManager pathManager = new PathManager();
		pathManager.makePathByDate();
		fileManager.setDir("D:\\test\\" + pathManager.getPath());
		fileManager.makeDirFile();

		Assertions.assertEquals(true, fileManager.checkAndMakeDir());
	}

	@Test
	void test_MakeFile() {
		FileManager fileManager = new FileManager();
		PathManager pathManager = new PathManager();
		pathManager.makePathByDate();
		fileManager.setDir("D:\\test\\" + pathManager.getPath());
		fileManager.makeResultFile();

		Assertions.assertEquals(true, fileManager.checkAndMakeFile());
	}
	
	@Test
	void test_PropertyLoader() {
		PropertyLoader loader = new PropertyLoader();
		String path = "D:/eclipse_workspace/LottoMaker/src/main/resources/application.properties";
		
		Assertions.assertEquals(true, loader.loadProp(path));
	}
	
	@Test
	void test_PropertyReader() {
		PropertyLoader loader = new PropertyLoader();
		String path = "D:/eclipse_workspace/LottoMaker/src/main/resources/application.properties";
		loader.loadProp(path);
		PropertyReader reader = new PropertyReader();
		reader.setProperties(loader.getProperties());
		
		Assertions.assertEquals(loader.getProperties(), reader.getProperties());
		Assertions.assertEquals("D:\\test\\", reader.getDir());
	}
}
