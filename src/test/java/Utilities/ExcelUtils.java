package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    /*
    .openExcelFile(String fileNme, String sheetName); -> return ExcelSheet;
    .getValue(int rowNum, int CelNum); -> return String;
    .setValue(int rowMum, int cellNum, String value); -> void;
    .getRowValue(int rowNum); -> return List<String>;
     */


    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static String path;

    /**
     * This method will read excel file with fileName and sheetName then will return Sheet
     *
     * @param fileName
     * @param sheetName
     * @return
     */

    public static Sheet openExcelFile(String fileName, String sheetName) {
        path = "src\\test\\resources\\Data\\" + fileName + ".xlsx";
        File file = new File(path);

        try {
            FileInputStream input = new FileInputStream(file);
            workbook = new XSSFWorkbook(input);
            sheet = workbook.getSheet(sheetName);


        } catch (FileNotFoundException e) {
            System.out.println(fileName + "excel file doesn't exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sheet;
    }

    /**
     * This method return the value in provided rowNum and cellNum in String type
     *
     * @param rowNum
     * @param cellNum
     * @return
     */
    public static String getValue(int rowNum, int cellNum) {
        return sheet.getRow(rowNum).getCell(cellNum).toString();
    }

    public static void setValue(int rowNum, int cellNum, String newValue) {
        row = sheet.getRow(rowNum);
        cell = row.getCell(cellNum);
        if (cell == null) {
            cell = row.createCell(cellNum);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(newValue);
        } else {
            cell.setCellValue(newValue);

        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("FIle doesn't exist.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This method will store all values to list from provided rowNum.
     *
     * @param rowNum
     * @return
     */
    public static List<String> getRowValues(int rowNum) {
        List<String> values = new ArrayList<>();
        row = sheet.getRow(rowNum);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            values.add(i, row.getCell(i).toString());
        }
return values;

    }

    public static void close(){
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



