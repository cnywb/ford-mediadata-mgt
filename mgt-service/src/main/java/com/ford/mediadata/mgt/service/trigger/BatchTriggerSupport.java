package com.ford.mediadata.mgt.service.trigger;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ford.mediadata.mgt.service.mapping.CommonMapService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BatchTriggerSupport {

	@Resource
	private CommonMapService commonMapService;

	final private String BATCH_SERVER_ADDRESS_KEY = "BatchServerAddress";

	final private static Object lock = new Object();

	public Boolean isBatchServer() throws UnknownHostException {
		String batchServerAddress = commonMapService.getString(BATCH_SERVER_ADDRESS_KEY);
		if (batchServerAddress == null) {
			try {
				Double sleepTime = 60000 * Math.random();
				Thread.sleep(sleepTime.longValue());
			} catch (InterruptedException e) {
				// ignore
			}
			initMap();
			return isBatchServer();
		}
		String localAddres = getLocalAddress();
		log.info("批处理配置服务器地址[{}]，本机地址[{}]", batchServerAddress, localAddres);
		if (batchServerAddress.equals(localAddres)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	private void initMap() throws UnknownHostException {
		synchronized (lock) {
			String batchServerAddress = commonMapService.getString(BATCH_SERVER_ADDRESS_KEY);
			if (batchServerAddress != null) {
				return;
			}
			String localAddres = getLocalAddress();
			commonMapService.set(BATCH_SERVER_ADDRESS_KEY, localAddres);
		}
	}

	private String getLocalAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress().toString();
	}

}
