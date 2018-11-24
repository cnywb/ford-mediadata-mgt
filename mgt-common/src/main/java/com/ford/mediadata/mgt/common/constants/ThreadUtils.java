package com.ford.mediadata.mgt.common.constants;

public class ThreadUtils {

	public static Thread of(Runnable task) {
		return new Thread(task);
	}

}
