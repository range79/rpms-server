package com.range.rpms.packages.mapper;

import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.UnsupportedFileException;

public class MapperUtil {
    public static byte[] getFileBytes(UploadPackageRequest request) {
        try {

            return request.getFile().getBytes();

        } catch (Exception e) {

            throw new UnsupportedFileException("can't read file:"+ e);

        }
    }
}
