// IService.aidl
package com.leo.aidl;

// Declare any non-default types here with import statements

interface IService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getData(String func, String params);
}
