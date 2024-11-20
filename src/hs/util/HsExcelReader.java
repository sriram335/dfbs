package hs.util;

import java.io.FileInputStream;

import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public abstract class HsExcelReader {
    private FileInputStream fin;
    private HSSFWorkbook wb;


    public abstract void processSheet(HSSFSheet sheet) throws Exception;

    public HsExcelReader(String path) throws Exception {
        fin = new FileInputStream(path);
    }

    public void processExcelFile() throws Exception {
        
        
        POIFSFileSystem fs = new POIFSFileSystem(fin);
       
        wb = new HSSFWorkbook(fs);
       
        processWorkbook(wb);
      
        fin.close();
      

    }

    public void processExcelFile(int sheetIndex) throws Exception {

        POIFSFileSystem fs = new POIFSFileSystem(fin);

        HSSFWorkbook wb = new HSSFWorkbook(fs);

        System.out.println("Processing Sheet ---> " +
                           wb.getSheetName(sheetIndex));
        processWorkbook(wb, sheetIndex);
        fin.close();
    }

    public void processExcelFileSheets() throws Exception {

        POIFSFileSystem fs = new POIFSFileSystem(fin);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        int sheetCount = wb.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            System.out.println("Processing Sheet #" + i + " ---> " +
                               wb.getSheetName(i));
            processWorkbook(wb, i);
        }
        fin.close();

    }

    public void processWorkbook(HSSFWorkbook wb) throws Exception {

        HSSFSheet sheet = wb.getSheetAt(0);
        processSheet(sheet);
    }

    public void processWorkbook(HSSFWorkbook wb,
                                int sheetIndex) throws Exception {


        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
        processSheet(sheet);

    }


    public static String getStringCellValue(HSSFRow row, int i) {
        if (row == null)
            return "";
        HSSFCell cell = row.getCell((short)i);
        try {
            return cell.getStringCellValue().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static double getNumericCellValue(HSSFRow row, int i) {
        if (row == null)
            return 0;

        HSSFCell cell = row.getCell((short)i);
        try {

            return cell.getNumericCellValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getStringCellValue(HSSFSheet sheet, int r, int c) {
        if (sheet == null)
            return "";
        try {
            HSSFRow row = sheet.getRow(r);
            return getStringCellValue(row, c).trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static double getNumericCellValue(HSSFSheet sheet, int r, int c) {
        if (sheet == null)
            return 0;
        try {
            HSSFRow row = sheet.getRow(r);
            return getNumericCellValue(row, c);
        } catch (Exception e) {
            return 0;
        }
    }

    public static java.util.Date getDateCellValue(HSSFRow row, int i) {
        if (row == null)
            return null;

        HSSFCell cell = row.getCell((short)i);
        try {
            return cell.getDateCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static java.util.Date getDateCellValue(HSSFSheet sheet, int r,
                                                  int c) {
        if (sheet == null)
            return null;
        try {
            HSSFRow row = sheet.getRow(r);
            return getDateCellValue(row, c);
        } catch (Exception e) {
            return null;
        }
    }

    public String getAmountDisplay(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public FileInputStream getFin() {
        return fin;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }


}
