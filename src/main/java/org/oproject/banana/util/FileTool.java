/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package org.oproject.banana.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件内容读取器
 * @author aohai.li
 * @version $Id: FileTool.java, v 0.1 2013-11-27 下午1:10:45 aohai.li Exp $
 */
public class FileTool {

    /** 系统换行符 */
    private static final String SYS_LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileToolException("未找到文件：" + filePath);
        }
        if (!file.isFile()) {
            throw new FileToolException("对象不是一个文件：" + filePath);
        }
        StringBuilder contentBuilder = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "utf-8");
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(SYS_LINE_SEPARATOR);
            }
        } catch (IOException ioe) {
            throw new FileToolException("捕获IOE，" + filePath, ioe);
        } finally {
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                 // 忽略
                }
            }
            if(null != isr){
                try {
                    isr.close();
                } catch (IOException e) {
                 // 忽略
                }
            }
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    // 忽略
                }
            }
        }
        return contentBuilder.toString();
    }

    /**
     * 文件工具异常
     * @author aohai.li
     * @version $Id: FileTool.java, v 0.1 2013-11-27 下午1:13:54 aohai.li Exp $
     */
    public static class FileToolException extends RuntimeException {

        /**  */
        private static final long serialVersionUID = 1L;

        public FileToolException() {
            super();
        }

        public FileToolException(String message, Throwable cause) {
            super(message, cause);
        }

        public FileToolException(String message) {
            super(message);
        }

        public FileToolException(Throwable cause) {
            super(cause);
        }
    }
}
