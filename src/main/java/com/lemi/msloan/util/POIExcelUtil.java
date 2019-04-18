package com.lemi.msloan.util;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class POIExcelUtil {
    /**
     * 将数据组装成Excel文件的IO流
     */
    public static void writerDataInExcelIo(List<List<String>> strsList, OutputStream os) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        for (int i = 0; i < strsList.size(); i++) {
            List<String> list = strsList.get(i);//得到一行数据
            HSSFRow row = sheet.createRow((short) i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setCellValue(list.get(j));
            }
        }
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据组装成Excel文件的IO流
     */
    public static void writerDataInExcelIo(List<List<String>> strsList, OutputStream os,String title,Integer titleCol) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("客户数据");
        HSSFRow titleRow = null;
        HSSFCell titleCell = null;
        sheet.setDefaultRowHeightInPoints(30);
        HSSFCellStyle titleStyle = wb.createCellStyle();        //标题样式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font ztFont = wb.createFont();
        ztFont.setItalic(false);                     // 设置字体为斜体字
        ztFont.setFontHeightInPoints((short) 16);    // 将字体大小设置为18px
        ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上
        ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //加粗
        titleStyle.setFont(ztFont);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        titleStyle.setFillForegroundColor((short) 13);//背景颜色
        titleRow = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        titleCell = titleRow.createCell(0);
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleCol));
        // 设置单元格内容
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);



        HSSFCellStyle cellStyle = wb.createCellStyle();        //标题样式
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        for (int i = 0; i < strsList.size(); i++) {
            List<String> list = strsList.get(i);//得到一行数据
            HSSFRow row = sheet.createRow((short) i+1);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setCellValue(list.get(j));
                cell.setCellStyle(cellStyle);
            }
        }
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
