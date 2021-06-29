package me.sample.uhaha.web.stock.module.excel;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelModuleTest {

	@Autowired
	private ExcelGenerator eg;

	@Test
	void test() throws EncryptedDocumentException, InvalidFormatException, IOException {
		eg.genWorkbook();
	}

}
