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
 * �ļ����ݶ�ȡ��
 * @author aohai.li
 * @version $Id: FileTool.java, v 0.1 2013-11-27 ����1:10:45 aohai.li Exp $
 */
public class FileTool {

    /** ϵͳ���з� */
    private static final String SYS_LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * ��ȡ�ļ�����
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileToolException("δ�ҵ��ļ���" + filePath);
        }
        if (!file.isFile()) {
            throw new FileToolException("������һ���ļ���" + filePath);
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
            throw new FileToolException("����IOE��" + filePath, ioe);
        } finally {
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                 // ����
                }
            }
            if(null != isr){
                try {
                    isr.close();
                } catch (IOException e) {
                 // ����
                }
            }
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    // ����
                }
            }
        }
        return contentBuilder.toString();
    }

    /**
     * �ļ������쳣
     * @author aohai.li
     * @version $Id: FileTool.java, v 0.1 2013-11-27 ����1:13:54 aohai.li Exp $
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
