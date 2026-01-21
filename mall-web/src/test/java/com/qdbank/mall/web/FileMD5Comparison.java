package com.qdbank.mall.web;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class FileMD5Comparison {
    public static void main(String[] args) {
        String file1Path = "D:\\Users\\qd20000128\\Desktop\\1.jpg";
        String file2Path = "D:\\Users\\qd20000128\\Desktop\\2.jpg";
        try {
            String md5File1 = calculateMD5(file1Path);
            String md5File2 = calculateMD5(file2Path);
            if (md5File1.equals(md5File2)) {
                System.out.println("Files are equal.");
            } else {
                System.out.println("Files are not equal.");
            }
        } catch (Exception   e) {
            e.printStackTrace();
        }
    }

        // 计算文件的MD5哈希值
        private static String calculateMD5(String filePath) throws Exception{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            try (FileInputStream fis = new FileInputStream(filePath)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    md5.update(buffer, 0, bytesRead);
                }
            }
            byte[] digest = md5.digest();
            StringBuilder md5HexString = new StringBuilder();
            for (byte b : digest) {
                md5HexString.append(String.format("%02x", b & 0xff));
            }
            return md5HexString.toString();
            }
}
