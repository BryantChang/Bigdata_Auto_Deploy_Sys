package com.bryantchang.autodepsys.utils;

import java.util.Date;

import javax.lang.model.element.ExecutableElement;

public abstract class DaemonTask implements Runnable {

	private String taskID;
	private String taskName;
	private String taskDesc;
	private boolean isStartedFlag = false;
	private boolean isTerminated = false;
	private int progress;
	private Date startTime;
	private Date terminatedTime;
	protected boolean toStopFlag = false;
	private String execResult;
	
	protected abstract void execute();
	
	protected void setProgress(int progress) {
		this.progress = progress;
	}
	
	@Override
	public void run() {
		this.isStartedFlag = true;
		this.execute();
		this.isTerminated = true;
		this.terminatedTime = new Date();
	}
	
	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public boolean isStarted() {
		return isStartedFlag;
	}

	public boolean getIsTerminated() {
		return isTerminated;
	}
	public void setToStopFlag(boolean toStopFlag) {
		this.toStopFlag = toStopFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getTerminatedTime() {
		return terminatedTime;
	}

	public int getProgress() {
		return progress;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getExecResult() {
		return execResult;
	}

	public void setExecResult(String execResult) {
		this.execResult = execResult;
	}
	
	

}
















