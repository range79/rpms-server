package com.range.rpms.mapper;

import com.range.rpms.dto.pkg.UploadPackageRequest;
import com.range.rpms.exception.pkg.UnsupportedFileException;

public class MapperUtil {
    public static byte[] getFileBytes(UploadPackageRequest request) {
        try {

            return request.getFile().getBytes();

        } catch (Exception e) {

            throw new UnsupportedFileException("can't read file:"+ e);

        }
    }
}
