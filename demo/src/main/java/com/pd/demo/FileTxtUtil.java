package com.pd.demo;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author YCWB0382
 * @date 2023-07-28 14:58
 */

public class FileTxtUtil {

 public static void main(String[] args) throws Exception {
  final File file = new File("/Users/pd/Downloads/new.txt");
  String regex = "[\\s\\S]*第[\\s\\S]*卷[\\s\\S]*第[\\s\\S]*章[\\s\\S]*";
  String replaceRegex = "[\\s\\S]*第[\\s\\S]*卷[\\s\\S]*第";
  try (FileReader in = new FileReader(file);
      BufferedReader bufIn = new BufferedReader(in);
      CharArrayWriter tempStream = new CharArrayWriter()) {
    String line;
    while ((line = bufIn.readLine()) != null) {

     if (line.contains("分节阅读")) {
      continue;
     }

     tempStream.write(line);
     tempStream.write(System.lineSeparator());
    }
    tempStream.writeTo(new FileWriter(new File("/Users/pd/Downloads/new2.txt")));
  }
 }
}
