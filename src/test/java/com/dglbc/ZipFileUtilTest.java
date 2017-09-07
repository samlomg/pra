package com.dglbc;

import com.dglbc.untils.ZipFileUtil;
import org.apache.commons.compress.archivers.ArchiveException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by gdlbc on 2017/1/1.
 */
public class ZipFileUtilTest {

    @Test
    public void testCompressFiles2Zip() {
//存放待压缩文件的目录
        File srcFile = new File("D:/12");
//压缩后的zip文件路径
        String zipFilePath = "d:/12/12.zip";
        if(srcFile.exists()) {
            File[] files = srcFile.listFiles();
            ZipFileUtil.compressFiles2Zip(files, zipFilePath);
        }
    }

    @Test
    public void testDecompressZip() throws IOException, ArchiveException {
//压缩包所在路径
        String zipFilePath = "d:/12/12.zip";
//解压后的文件存放目录
        String saveFileDir = "d:/12/1/";
//调用解压方法
//        ZipFileUtil.decompressZip(zipFilePath, saveFileDir);
        ZipFileUtil.doZip(zipFilePath,saveFileDir,"uft-8",12);
    }

//    @Test
//    public void test234(){
//        SalesPlan salesPlan =null;
//        System.out.println(salesPlan.getSalesInvoiceId());
//    }
}
