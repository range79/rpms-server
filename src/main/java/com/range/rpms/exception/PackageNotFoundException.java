package com.range.rpms.exception;

public class PackageNotFoundException extends RuntimeException {
  public PackageNotFoundException(String message) {
    super(message);
  }
}
