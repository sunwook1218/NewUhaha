package me.sample.uhaha.web.stock.module.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import me.sample.uhaha.web.stock.module.crawl.CrawlFundamentalData;
import me.sample.uhaha.web.stock.module.vo.FundamentalData;
import me.sample.uhaha.web.stock.module.vo.Sector;

@Component
public class ExcelGenerator {

	@Autowired
	private CrawlFundamentalData cFunda;

	@Value("${apache.poi.saveDir}")
	private String saveDir;

	public void genWorkbook(HttpServletResponse response) throws EncryptedDocumentException, InvalidFormatException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTimeString = sdf.format(Calendar.getInstance().getTime());
		String wbFileName = saveDir + "wb_" + dateTimeString + ".xlsx";
		System.out.println(wbFileName);

		try (OutputStream fileOut = new FileOutputStream(wbFileName)) {

			/* Workbook */
			Workbook wb = new XSSFWorkbook();
//			Workbook wb = WorkbookFactory.create(new File("MyExcel.xlsx"));
			
			/* Sheet */
			List<Sector> sectorList = cFunda.getSectorList();
			
			for(Sector s : sectorList) {
				String safeSheetName = WorkbookUtil.createSafeSheetName(s.getSectorName());
				Sheet sheet = createSheet(wb, safeSheetName);
				
				s = cFunda.setFundamentalDataList(s);
				
				int r = 1;
				for(FundamentalData f : s.getFundamentalDataList()) {
					
					/* Cell */
					Row row = sheet.createRow(r++);
					int c = 0;
					row.createCell(c++).setCellValue(f.getCode());
					row.createCell(c++).setCellValue(f.getName());
					row.createCell(c++).setCellValue(f.getSales());
					row.createCell(c++).setCellValue(f.getOperatingProfit());
					row.createCell(c++).setCellValue(f.getNetProfit());
					row.createCell(c++).setCellValue(f.getOperatingMargin());
					row.createCell(c++).setCellValue(f.getNetProfitMargin());
					row.createCell(c++).setCellValue(f.getRoe());
					row.createCell(c++).setCellValue(f.getPer());
					row.createCell(c++).setCellValue(f.getPbr());
				}
			}			
			
			/* export file */
//			wb.write(response.getOutputStream()); //TODO outputStream 수정필요
			wb.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Sheet createSheet(Workbook wb, String sheetName) {
		return wb.createSheet(sheetName);
	}

}
