package com.range.rpms.common.util;

import com.range.rpms.packages.exception.PackageCantUploadException;

import java.util.Arrays;
import java.util.List;

public class FileExtensionUtil {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("tgz","deb","gz","zip","tar","bz2");
    public static String getFileExtension(String packageName){
        return packageName
                .substring(packageName
                        .lastIndexOf("."));
    }
    public static boolean isInValidExtension(String packageName){
           /*
           Get File original name and if its
           null, or it has no extension throw an exception
         */
        if (packageName == null
                || !packageName.contains(".")){

            throw new PackageCantUploadException("wrong format file");

        }

        // get file extension from file
        String fileExtension = packageName.substring(packageName.lastIndexOf(".") + 1).toLowerCase();
        //if file extension contains allowed extension return true else false
        return !ALLOWED_EXTENSIONS.contains(fileExtension);
    }


}
