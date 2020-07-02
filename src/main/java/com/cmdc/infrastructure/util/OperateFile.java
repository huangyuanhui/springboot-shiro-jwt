package com.cmdc.infrastructure.util;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : wuwensheng
 * @date : 10:40 2020/6/9
 */
public class OperateFile {

    //获取文件中每一行的内容,转换为string的集合
    public static List<String> getFileContent(String fileLocation) throws IOException {
        return Files.readLines(new File(fileLocation), Charsets.UTF_8);
    }

    public static HashCode getFileHashCode(String fileLocation) throws IOException {
        return Files.hash(new File(fileLocation), Hashing.md5());
    }

    //向文件中写入内容,注意此方法会将源文件中内容替换
    public static File writeContentToFile(String writeValue,String fileLocation) throws IOException {
        File file=new File(fileLocation);
        Files.write(writeValue, file, Charsets.UTF_8);
        return getFile(fileLocation);
    }

    //向文件中追加内容
    public static File appendContentToFile(String writeValue,String fileLocation) throws IOException {
        File file=new File(fileLocation);
        Files.append(writeValue, file, Charsets.UTF_8);
        return getFile(fileLocation);
    }

    public static File getFile(String fileLocation){
        return new File(fileLocation);
    }

    public static void copyFile(String fileLocation,String copyTo) throws IOException {
        Files.copy(new File(fileLocation),new File(copyTo));//复制文件
    }

    public static void moveFile(String fileLocation,String moveTo) throws IOException {
        Files.move(new File(fileLocation), new File(moveTo)); //移动文件
    }

    public static boolean compareFile(String fileFirst,String fileSecond) throws IOException {
        return Files.equal(new File(fileFirst), new File(fileSecond));//比较文件
    }

}
