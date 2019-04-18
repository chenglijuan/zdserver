package com.lemi.msloan.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;


/**
 * Created by clj on 2018/10/29.
 */
public class PoiTest {

    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath == null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
      //  InputStream is = null;
        //String extString = filePath.getName();
        try {
            InputStream is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                 wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                 wb = new XSSFWorkbook(is);
            }else{
                wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类

            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                   // cellValue = String.valueOf(cell.getNumericCellValue());

                    DecimalFormat format = new DecimalFormat("#");
                    Number value = cell.getNumericCellValue();
                    cellValue = format.format(value);

                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}
