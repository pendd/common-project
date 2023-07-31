package com.pd.demo;

import java.io.File;
import java.util.Objects;

/**
 * @author YCWB0382
 * @date 2023-07-28 13:49
 */

public class FileUtil {

 public static void main(String[] args) {
  final File file = new File("/Volumes/彭东的硬盘/vedio/拉钩-Java工程师高薪训练营【完结】");

  String regex = "(\\[更多课程+.*\\])|(_+.*2268731)";

  recursionRemoveContent(file, regex);
 }

 private static void recursionRemoveContent(File file, String regex) {

  for (File subFile : Objects.requireNonNull(file.listFiles())) {
   System.out.println(subFile.getName());
   removeContent(regex, subFile);
   if (subFile.isDirectory()) {
    recursionRemoveContent(subFile, regex);
   }
  }
 }

 public static void removeContent(String regex, File file) {
  final String fileName = file.getName().replaceAll(regex, "");
  file.renameTo(new File(file.getParent(), fileName));
 }


}
