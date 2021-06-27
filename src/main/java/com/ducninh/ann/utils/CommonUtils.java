package com.ducninh.ann.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.Instant;
import java.util.Date;

public class CommonUtils {

    /**
     * @param rootDirectory
     * @param subFolder
     * @param file
     * @return
     * @throws IOException
     */
    public static String upload(String rootDirectory, String subFolder, MultipartFile file) throws IOException {
        if (file == null){
            return null;
        }

        String fileName =file.getOriginalFilename();
        String includeFileDirectory = rootDirectory + "/" + subFolder;
        File folderFile = new File(includeFileDirectory);
        if (!folderFile.exists()){
            folderFile.mkdirs();
        }
        InputStream i = file.getInputStream();

        String path = includeFileDirectory + "/" + fileName;
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
        BufferedInputStream bis = new BufferedInputStream(i);

        byte buf[] = new byte[102400];
        while ((bis.read(buf)) != -1){
            stream.write(buf);
        }
        stream.flush();
        stream.close();
        bis.close();
        return path.replace(rootDirectory, "private");
    }
}
