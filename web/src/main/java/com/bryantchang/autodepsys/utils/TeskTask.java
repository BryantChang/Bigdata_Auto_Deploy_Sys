package com.bryantchang.autodepsys.utils;

public class TeskTask extends DaemonTask {

	@Override
	protected void execute() {
		for (int i = 0; i < 10; i++) {
			if(this.toStopFlag==false)
			{
				int progress = (i + 1) * 100 / 10;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.setProgress(progress);
			}
		}
	}

}
