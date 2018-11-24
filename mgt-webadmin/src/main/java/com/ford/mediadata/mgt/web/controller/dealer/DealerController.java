package com.ford.mediadata.mgt.web.controller.dealer;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.service.dealer.DealerService;
import com.ford.mediadata.mgt.web.controller.CommonRemoveReq;
import com.ford.mediadata.mgt.web.controller.dealer.vo.PageDealerRequest;

import io.dabing.core.repository.domain.Page;
import io.dabing.core.web.annotation.ApiService;

/**
 * 经销商
 * 
 * @author chunliang.you
 *
 */
@Controller
public class DealerController {

	@Autowired
	private DealerService dealerService;

	@ApiService(transCode = "pageDealer")
	public Page<DealerEntity> page(PageDealerRequest req) {
		DealerEntity condition = new DealerEntity();
		BeanUtils.copyProperties(req, condition);
		return dealerService.findByGridPage(req.getPage(), condition);
	}

	@ApiService(transCode = "editDealer")
	public void edit(DealerEntity entity) {
		if (entity.getId() == null) {
			dealerService.save(entity);
		} else {
			dealerService.update(entity);
		}
	}

	@ApiService(transCode = "removeDealer")
	public void remove(CommonRemoveReq req) {
		if (req != null && CollectionUtils.isNotEmpty(req.getReqs())) {
			for (CommonRemoveReq.Data data : req.getReqs()) {
				dealerService.delete(data.getId());
			}
		}
	}

}
