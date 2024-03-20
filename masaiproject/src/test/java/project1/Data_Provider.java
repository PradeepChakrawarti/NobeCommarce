package project1;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Data_Provider {
	
    public Object[][] getdatafromexcel() throws EncryptedDocumentException, IOException {

       FileInputStream file = new FileInputStream("C:\\Users\\Prade\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\masaiproject\\src\\main\\resources\\Project_Data.xlsx");

        Workbook book = WorkbookFactory.create(file);

        Sheet sheet = book.getSheet("Sheet1");

        int RowCount = sheet.getLastRowNum() + 1;
        int CellCount = sheet.getRow(0).getLastCellNum();

        Object[][] object = new Object[RowCount][CellCount];

        for (int i = 0; i < RowCount; i++) {
            for (int j = 0; j < CellCount; j++) {
                object[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }
        return object;
    }
}
