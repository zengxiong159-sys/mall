package com.qdbank.mall.cms;

import java.io.*;

public class FileTest {
    public static void main(String[] args) {
        String inUrl = "D:\\1.txt";
        File infile = new File(inUrl);
        String outUrl = "D:\\2.txt";
        File outfile = new File(outUrl);
        try {
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            FileInputStream in = new FileInputStream(infile);
            FileOutputStream out = new FileOutputStream(outfile);
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out));
            String temp;
            while ((temp = read.readLine()) != null) {
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
