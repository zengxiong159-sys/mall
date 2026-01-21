package com.qdbank.mall.web;

import java.io.*;

public class FileTest {
    public static void main(String[] args) {
        String inurl = "D:\\1.txt";
        File infile = new File(inurl);
        String outurl = "D:\\2.txt";
        File outfile = new File(outurl);
        try {
            //如果写入的文件不存在创建新文件
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            //文件的输入流读取文件
            FileInputStream in = new FileInputStream(infile);
            FileOutputStream out = new FileOutputStream(outfile);
            //读文件
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            //写文件
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out));

            String temp = "";

            while ((temp = read.readLine()) != null) {
                //写入文件
                write.write(temp + "\r\n" + "commit;" + "\r\n" );
            }
            read.close();
            write.close();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
