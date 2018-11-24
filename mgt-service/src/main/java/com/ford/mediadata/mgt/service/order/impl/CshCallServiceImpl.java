package com.ford.mediadata.mgt.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.order.CshCallEntity;
import com.ford.mediadata.mgt.entity.order.CshCallEntityCondition;
import com.ford.mediadata.mgt.repository.order.CshCallRepository;
import com.ford.mediadata.mgt.service.order.CshCallService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;

/**
 * 车商汇话单服务
 * 
 * @author chunliang.you
 *
 */
@Transactional
@Service
public class CshCallServiceImpl extends AbstractService implements CshCallService {

	@Resource
	private CshCallRepository cshCallRepository;

	@Override
	public Page<CshCallEntity> findByGridPage(GridPage page, CshCallEntityCondition condition) {
		return cshCallRepository.findByGridPage(page, condition);
	}

	@Override
	public List<CshCallEntity> list(CshCallEntityCondition condition) {
		return cshCallRepository.list(condition);
	}

	@Override
	public void save(CshCallEntity entity) {
		cshCallRepository.save(entity);
	}

	@Override
	public void update(CshCallEntity entity) {
		cshCallRepository.update(entity);
	}

	@Override
	public void delete(Long id) {
		cshCallRepository.delete(id);
	}

	@Override
	public void batchSave(List<CshCallEntity> entityList) {
		if (CollectionUtils.isEmpty(entityList)) {
			return;
		}
		for (CshCallEntity entity : entityList) {
//			CshCallEntity existEntity = findByOrderId(entity.getOrderId());
//			if (existEntity == null) {
				cshCallRepository.save(entity);
//			} else {
//				BeanUtils.copyProperties(entity, existEntity, "id", "createdById", "createdDate", "updatedById",
//						"updatedDate", "deleted", "deleteDate", "optCounter");
//				cshCallRepository.update(existEntity);
//			}
		}
	}

//	@Override
//	public CshCallEntity findByOrderId(String orderId) {
//		Assert.hasText(orderId);
//		CshCallEntityCondition condition = new CshCallEntityCondition();
//		condition.setOrderId(orderId);
//		List<CshCallEntity> list = cshCallRepository.list(condition);
//		if (CollectionUtils.isNotEmpty(list)) {
//			return list.get(0);
//		}
//		return null;
//	}

}