package com.bryantchang.autodepsys.common;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * Created by bryantchang on 2016/12/24.
 */
public class FileProgressMonitor implements SftpProgressMonitor {

    private boolean isEnd = false;
    private long transfered;
    private long fileSize;
    private String uploadId;


    public FileProgressMonitor(String uploadId) {
        this.uploadId = uploadId;
    }

    private synchronized void add(long count) {
        transfered = transfered + count;
    }

    private synchronized long getTransfered() {
        return transfered;
    }

    public synchronized void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    private synchronized void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    private synchronized boolean isEnd() {
        return isEnd;
    }


    @Override
    public void init(int i, String s, String s1, long l) {

    }

    @Override
    public boolean count(long count) {
        if (isEnd()) return false;
        add(count);
        return true;
    }

    @Override
    public void end() {
        setEnd(true);
    }
}
