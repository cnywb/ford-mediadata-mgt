package com.ford.mediadata.mgt.service.order;

import java.util.List;

import io.dabing.core.service.Service;

public interface BatchSaveService<T> extends Service {

	void batchSave(List<T> entityList);

}
