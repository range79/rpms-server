package com.range.rpms.packages.mapper;

import com.range.rpms.packages.enums.PackageVisibility;
import com.range.rpms.packages.exception.VisibilityNotDefinedException;

public class PackageVisibilityMapper {

    public static PackageVisibility getPackageVisibility(int code) {

        switch (code) {
            case 1:
                return PackageVisibility.PUBLIC;
            case 2:
                return PackageVisibility.PRIVATE;
            case 3:
                return PackageVisibility.ONLY_FRIEND;
            default:
                throw new VisibilityNotDefinedException("Code not defined");






        }






    }




}
