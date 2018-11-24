package com.ford.mediadata.mgt.web.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.service.dealer.DealerService;
import com.ford.mediadata.mgt.web.controller.api.vo.ApiResponse;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.DealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO.CityDealerVO;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api")
public class DealerApiController {

	@Resource
	private DealerService dealerService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dealer/tree", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List> getDealerTree() {
		try {
			List<ProvinceDealerVO> provinceDealerVOList = Lists.newArrayList();
			List<DealerEntity> dealerEntityList = dealerService.findAll();
			for (DealerEntity dealerEntity : dealerEntityList) {
				// 查找省份，若不存在则新建
				ProvinceDealerVO province = null;
				for (ProvinceDealerVO provinceDealerVO : provinceDealerVOList) {
					if (provinceDealerVO.getProvinceName().equals(dealerEntity.getProvince())) {
						province = provinceDealerVO;
						break;
					}
				}
				if (province == null) {
					province = new ProvinceDealerVO();
					province.setProvinceName(dealerEntity.getProvince());
					List<CityDealerVO> cityDealerList = Lists.newArrayList();
					province.setCityDealerList(cityDealerList);
					provinceDealerVOList.add(province);
				}
				// 查找城市，若不存在则新建
				CityDealerVO city = null;
				for (CityDealerVO cityDealerVO : province.getCityDealerList()) {
					if (cityDealerVO.getCityName().equals(dealerEntity.getCity())) {
						city = cityDealerVO;
						break;
					}
				}
				if (city == null) {
					city = new CityDealerVO();
					city.setCityName(dealerEntity.getCity());
					List<DealerVO> dealerList = Lists.newArrayList();
					city.setDealerList(dealerList);
					province.getCityDealerList().add(city);
				}
				// 加入经销商
				DealerVO dealer = new DealerVO();
				dealer.setCode(dealerEntity.getCode());
				dealer.setName(dealerEntity.getName());
				city.getDealerList().add(dealer);
			}
			return ApiResponse.newSuccessResponse(List.class, provinceDealerVOList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ApiResponse.newErrorResponse(List.class, e.getMessage());
		}
	}

}
