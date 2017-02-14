package com.bryantchang.autodepsys.common;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/26.
 */
public class SftpUtils {
    private static final Logger log = LoggerFactory.getLogger(SftpUtils.class);

    private volatile static SftpUtils instance;

    private SftpUtils() {
    }

    /** 单例模式获取对象SftpUtils的实例 */
    public static SftpUtils getInstance() {
        if (null == instance) {
            synchronized (SftpUtils.class) {
                if (null == instance) {
                    instance = new SftpUtils();
                }
            }
        }
        return instance;
    }

    private volatile Session sshSession = null;

    public ChannelSftp getSftpChannel(String host, int port, String username,
                               String password) {
        ChannelSftp sftp = null;
        Channel channel = null;

        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            log.info("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            log.info("Session connected.");
            channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;

            log.info("Connected to " + host + " success.");
        } catch (JSchException e) {
            log.error("Connect to '" + host + "' fail:" + e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("Connect to '" + host + "' fail:" + e.getMessage(), e);
            return null;
        }
        return sftp;
    }

    public String upload(String ip, int port, String userName, String password,
                       String directory, String uploadFile) {
        FileInputStream is = null;
        ChannelSftp sftp = null;
        String msg = "succ";
        try {
            sftp = this.getSftpChannel(ip, port, userName, password);
            if(sftp == null) {
                msg = "connect error";
            }
            sftp.cd(directory);
            File file = new File(uploadFile);
            is = new FileInputStream(file);
            sftp.put(is, file.getName());
            log.info("sftp Upload file '" + uploadFile + "' success.");
        } catch (FileNotFoundException e) {
            msg = "file not found";
            log.error("file '" + uploadFile + "' not found!", e);
        } catch (Exception e) {
            msg = "error";
            log.error("upload file '" + uploadFile + "' fail:" + e.getMessage(), e);
        } finally {
            log.info("End of SftpUtils.upload(...)");
            IOUtils.closeStream(is, null);
            this.disconnectSftp(sftp);
            this.disconnectSession(sshSession);
        }
        return msg;
    }

    public void disconnectSftp(ChannelSftp sftp) {
        if (null != sftp && sftp.isConnected()) {
            log.info("Disconnect ChannelSftp.");
            sftp.disconnect();
            sftp.exit();
            sftp = null;
        }
    }

    public void disconnectSession(Session sshSession) {
        if (null != sshSession && sshSession.isConnected()) {
            log.info("Disconnect Session.");
            sshSession.disconnect();
            sshSession = null;
        }
    }

    public void disconnectChannel(Channel channel) {
        if (null != channel && channel.isConnected()) {
            log.info("Disconnect Channel.");
            channel.disconnect();
            channel = null;
        }
    }

}










