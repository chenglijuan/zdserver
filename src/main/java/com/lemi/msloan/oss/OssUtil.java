package com.lemi.msloan.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class OssUtil {

    public static OssFileUtil uploadToOss(String filepath, String fileName, String type) {
        try {
            String newName = Uuid.getUUID();
            String fileSrc = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String fileKey = "";
            fileKey = OssConstants.OSS_CONFIG.OSS_FILE_KEY + newName + fileSrc;
            //System.out.println("----putObject1");
            OssUtil.putObject1(fileKey, filepath);
            OssFileUtil result = new OssFileUtil();
            result.setUrl(OssConstants.OSS_CONFIG.OSS_NORIYOSHI_FILE + newName + fileSrc);
            result.setName(fileName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //分片上传  本地测试  5M 文件 5秒左右
    public static boolean putObject1(String key, String filePath) throws FileNotFoundException {
        // key指的是 保存在oss上后的路径+文件名
        // filePath 指的是上传的文件路径
        //OSSClient client;
        boolean flag = false;
        try {
            String endpoint = OssConstants.OSS_CONFIG.EDNPOINT;
            String accessKeyId = OssConstants.OSS_CONFIG.ACCESS_KEY_ID;
            String accessKeySecret = OssConstants.OSS_CONFIG.ACCESS_KEY_SECRET;
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 设置断点续传请求
            UploadFileRequest uploadFileRequest = new UploadFileRequest(OssConstants.OSS_CONFIG.BUCKET_NAME, key);
            // 指定上传的本地文件
            uploadFileRequest.setUploadFile(filePath);
            // 指定上传并发线程数
            uploadFileRequest.setTaskNum(3);
            // 指定上传的分片大小
            uploadFileRequest.setPartSize(1 * 1024 * 1024);
            // 开启断点续传
            uploadFileRequest.setEnableCheckpoint(true);
            // 断点续传上传
            ossClient.uploadFile(uploadFileRequest);
            // 关闭client
            ossClient.shutdown();
        } catch (OSSException oe) {
           /* System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());*/
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }

    }


    /*public static void main(String[] args) {
        try {
            File file = new File("E:\\generatorSqlmapCustom.zip");
            InputStream is = new FileInputStream(file);
           // PutObjectResult result = uploadToOssByStream(is, "generatorSqlmapCustom.zip");
            //System.out.println("-------"+url);
            //System.out.println("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
