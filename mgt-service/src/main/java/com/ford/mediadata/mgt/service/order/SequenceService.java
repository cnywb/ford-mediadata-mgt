package com.ford.mediadata.mgt.service.order;

import io.dabing.core.service.Service;

/**
 * 序列服务
 * 
 * @author chunliang.you
 *
 */
public interface SequenceService extends Service {

	Long nextval(String sequenceName);

}