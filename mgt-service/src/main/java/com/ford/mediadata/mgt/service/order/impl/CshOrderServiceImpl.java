package com.ford.mediadata.mgt.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.order.CshOrderEntity;
import com.ford.mediadata.mgt.entity.order.CshOrderEntityCondition;
import com.ford.mediadata.mgt.repository.order.CshOrderRepository;
import com.ford.mediadata.mgt.service.order.CshOrderService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;

/**
 * 车商汇订单服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class CshOrderServiceImpl extends AbstractService implements CshOrderService {

	@Resource
	private CshOrderRepository cshOrderRepository;

	@Override
	public Page<CshOrderEntity> findByGridPage(GridPage page, CshOrderEntityCondition condition) {
		return cshOrderRepository.findByGridPage(page, condition);
	}

	@Override
	public List<CshOrderEntity> list(CshOrderEntityCondition condition) {
		return cshOrderRepository.list(condition);
	}

	@Override
	public void save(CshOrderEntity entity) {
		cshOrderRepository.save(entity);
	}

	@Override
	public void update(CshOrderEntity entity) {
		cshOrderRepository.update(entity);
	}

	@Override
	public void delete(Long id) {
		cshOrderRepository.delete(id);
	}

	@Override
	public void batchSave(List<CshOrderEntity> entityList) {
		if (CollectionUtils.isEmpty(entityList)) {
			return;
		}
		for (CshOrderEntity entity : entityList) {
//			CshOrderEntity existEntity = findByOrderId(entity.getOrderId());
//			if (existEntity == null) {
				cshOrderRepository.save(entity);
//			} else {
//				BeanUtils.copyProperties(entity, existEntity, "id", "createdById", "createdDate", "updatedById",
//						"updatedDate", "deleted", "deleteDate", "optCounter");
//				cshOrderRepository.update(existEntity);
//			}
		}
	}

//	@Override
//	public CshOrderEntity findByOrderId(String orderId) {
//		Assert.hasText(orderId);
//		CshOrderEntityCondition condition = new CshOrderEntityCondition();
//		condition.setOrderId(orderId);
//		List<CshOrderEntity> list = cshOrderRepository.list(condition);
//		if (CollectionUtils.isNotEmpty(list)) {
//			return list.get(0);
//		}
//		return null;
//	}

}