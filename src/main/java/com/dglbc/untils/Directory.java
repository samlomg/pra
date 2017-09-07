package com.dglbc.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdlbc on 2016/10/29.
 */
public class Directory {

    protected Log logger = LogFactory.getLog(getClass());
    public List<String> listFileOfDirectory(String customPath, String suffixString){
        List<String> relist=new ArrayList<>();
        Path dir= Paths.get(customPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,suffixString)) {
            for (Path entry:stream){
                relist.add(entry.getFileName().toString());
            }
        } catch (IOException e) {
            logger.info("读取文件夹失败："+e.getMessage());
        }
        return relist;
    }


}
