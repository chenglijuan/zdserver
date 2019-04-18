package com.lemi.msloan.util;

import java.io.File;

/**
 * @Author: chenglijuan
 * @Data: 2018/7/3  上午11:43
 * @Decription:
 * @Modified:
 */
public class FileUtil {


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    //图片格式
    private static final String IMG_JPG = "jpg";
    private static final String IMG_PNG = "png";
    private static final String IMG_BMP = "bmp";
    private static final String IMG_JPEG = "jpeg";
    private static final String IMG_GIF = "gif";

    //文件格式doc、docx、xls、xlsx、ppt、pptx、pdf、jpg、png、bmp、rar、zip、
    private static final String FILE_DOC = "doc";
    private static final String FILE_DOCX = "docx";
    private static final String FILE_PPT = "ppt";
    private static final String FILE_PPTX = "pptx";
    private static final String FILE_PDF = "pdf";
    private static final String FILE_RAR = "rar";
    private static final String FILE_ZIP = "zip";

    //判断文件是否是excel
    public static boolean checkExcelVaild(File file) throws Exception {
        boolean flag = false;
        if (!file.exists()) {
            flag = false;
        }
        if (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)) {
            flag = true;
        }
        return flag;
    }


    public static boolean checKImgVaild(File file) throws Exception {
        boolean flag = false;
        if (!file.exists()) {
            flag = false;
        }
        String[] fileFormat = {IMG_JPG,IMG_PNG,IMG_BMP,IMG_JPEG,IMG_GIF};
        String name = file.getName();
        for (String ss:fileFormat) {
            if (name.endsWith(ss)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public static boolean checKFileVaild(File file) throws Exception {
        boolean flag = false;
        if (!file.exists()) {
            flag = false;
        }
        String[] fileFormat = {FILE_DOC,FILE_DOCX,FILE_PPT,FILE_PPTX,FILE_PDF,FILE_RAR,FILE_ZIP};
        String name = file.getName();
        for (String ss:fileFormat) {
            if (name.endsWith(ss)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
