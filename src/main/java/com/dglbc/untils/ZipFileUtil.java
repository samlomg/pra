package com.dglbc.untils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Enumeration;

/**
 * Created by gdlbc on 2017/1/1.
 */
public class ZipFileUtil {

    /**
     * 把文件压缩成zip格式
     *
     * @param files       需要压缩的文件
     * @param zipFilePath 压缩后的zip文件路径 ,如"D:/test/aa.zip";
     */
    public static void compressFiles2Zip(File[] files, String zipFilePath) {
        if (files != null && files.length > 0) {
            if (isEndsWithZip(zipFilePath)) {
                ZipArchiveOutputStream zaos = null;
                try {
                    File zipFile = new File(zipFilePath);
                    zaos = new ZipArchiveOutputStream(zipFile);
//Use Zip64 extensions for all entries where they are required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);

//将每个文件用ZipArchiveEntry封装
//再用ZipArchiveOutputStream写到压缩文件中
                    for (File file : files) {
                        if (file != null) {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                            zaos.putArchiveEntry(zipArchiveEntry);
                            InputStream is = null;
                            try {
                                is = new FileInputStream(file);
                                byte[] buffer = new byte[1024 * 5];
                                int len = -1;
                                while ((len = is.read(buffer)) != -1) {
//把缓冲区的字节写入到ZipArchiveEntry
                                    zaos.write(buffer, 0, len);
                                }
//Writes all necessary data for this entry.
                                zaos.closeArchiveEntry();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            } finally {
                                if (is != null)
                                    is.close();
                            }

                        }
                    }
                    zaos.finish();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (zaos != null) {
                            zaos.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }

    }

    /**
     * 把zip文件解压到指定的文件夹
     *
     * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir 解压后的文件存放路径, 如"D:/test/"
     */
    public static void decompressZip(String zipFilePath, String saveFileDir) {
        if (isEndsWithZip(zipFilePath)) {
            File file = new File(zipFilePath);
            if (file.exists()) {
                InputStream is = null;
//can read Zip archives
                ZipArchiveInputStream zais = null;
                try {
                    is = new FileInputStream(file);
                    zais = new ZipArchiveInputStream(is);
                    ArchiveEntry archiveEntry = null;
//把zip包中的每个文件读取出来
//然后把文件写到指定的文件夹
                    while ((archiveEntry = zais.getNextEntry()) != null) {
//获取文件名
                        String entryFileName = archiveEntry.getName();
//构造解压出来的文件存放路径
                        String entryFilePath = saveFileDir + entryFileName;
                        byte[] content = new byte[(int) archiveEntry.getSize()];
                        zais.read(content);
                        OutputStream os = null;
                        try {
//把解压出来的文件写到指定路径
                            File entryFile = new File(entryFilePath);
                            os = new FileOutputStream(entryFile);
                            os.write(content);
                        } catch (IOException e) {
                            throw new IOException(e);
                        } finally {
                            if (os != null) {
                                os.flush();
                                os.close();
                            }
                        }

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (zais != null) {
                            zais.close();
                        }
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 判断文件名是否以.zip为后缀
     *
     * @param fileName 需要判断的文件名
     * @return 是zip文件返回true, 否则返回false
     */
    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }


    public static void doZip(String sPath,String tPath,String encoding,int BUF) throws IOException, ArchiveException {
        ZipFile file =new ZipFile(sPath,encoding);
        Enumeration<ZipArchiveEntry> en =file.getEntries();
        ZipArchiveEntry ze;
        while(en.hasMoreElements()){
            ze =en.nextElement();
            File f =new File(tPath,ze.getName());//创建完整路径
            if(ze.isDirectory()){
                f.mkdirs();
                continue;
            }else
                f.getParentFile().mkdirs();

            InputStream is =file.getInputStream(ze);
            OutputStream os =new FileOutputStream(f);
            IOUtils.copy(is, os, BUF);
            is.close();
            os.close();
        }
        file.close();
    }

    //这是更一般的解压处理
    public static void doTar(String sPath,String tPath,int BUF) throws IOException{
        TarArchiveInputStream tis =new TarArchiveInputStream(new FileInputStream(sPath));
        TarArchiveEntry te =null;
        while((te=tis.getNextTarEntry())!=null){
            File target =new File(tPath,te.getName());
            if(te.isDirectory()){
                target.mkdirs();
                continue;
            }else
                target.getParentFile().mkdirs();

            FileOutputStream fos =new FileOutputStream(target); //将当前entry写入文件
            byte[] buf =new byte[BUF];
            int len;
            while((len=tis.read(buf))!=-1){
                fos.write(buf, 0, len);
            }
            fos.close();
        }
        tis.close();
    }

}
