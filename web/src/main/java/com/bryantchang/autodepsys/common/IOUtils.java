package com.bryantchang.autodepsys.common;

/**
 * Created by bryantchang on 2016/12/24.
 * I/O工具类
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

    private static final Logger log = LoggerFactory.getLogger(IOUtils.class);

    public static void closeChannel(Channel channel) {

        if (null != channel) {
            try {
                channel.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            channel = null;
        }
    }

    public static void closeReader(Reader reader) {

        if (null != reader) {
            try {
                reader.close();
                reader = null;
            } catch (IOException e) {
                log.error("close reader faile!", e);
            }
        }
    }

    public static void closeStream(InputStream is, OutputStream os) {

        if (null != is) {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                log.error("close InputStream fail!", e);
            }
        }

        if (null != os) {
            try {
                os.close();
                os = null;
            } catch (IOException e) {
                log.error("close OutputStream fail!", e);
            }
        }
    }

    public static void closeWriter(Writer writer) {

        if (null != writer) {
            try {
                writer.close();
            } catch (IOException e) {
                log.error("Close Writer fail:" + e.getMessage(), e);
            }
            writer = null;
        }
    }

    /**
     * @Title: writeStrToFile
     * @deprecated: 将字符串写入制定的文件中
     * @param os
     *            输出流,包含有用户选择的文件路径
     * @param str
     *            字符串
     * @param fileName
     *            文件名称
     * @return int 0:失败, 1:成功
     * @throws Exception
     * @author
     * @date 2014-11-16
     */
    public static int writeStrToFile(OutputStream os, String str,
                                     String fileName) throws Exception {

        log.info("Write String to file,the str is:\r\n" + str);
        log.info("Write String to file,the fileName is:" + fileName);

        int ret = 0;

        OutputStreamWriter writer = null;

        try {

            writer = new OutputStreamWriter(os);

            writer.write(str);

            ret = 1;

        } catch (FileNotFoundException e) {
            log.error("Write String to File fail:" + e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error("Write String to File fail:" + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Write String to File fail:" + e.getMessage(), e);
            throw e;
        } finally {

            try {
                if (null != writer) {
                    // 清空缓冲区,否则下一次输出时会重复输出
                    writer.flush();

                    writer.close();
                }
            } catch (IOException e) {
                log.error("Close OutputStreamWriter fail:" + e.getMessage(), e);
            }

            closeStream(null, os);
        }
        return ret;
    }
}
