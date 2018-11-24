package com.ford.mediadata.mgt.web.controller.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ford.mediadata.mgt.service.cache.CacheService;
import com.ford.mediadata.mgt.web.controller.api.vo.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api")
public class CacheApiController {

	@Resource
	private CacheService cacheService;

	@RequestMapping(value = "/cache", method = RequestMethod.DELETE)
	@ResponseBody
	public ApiResponse<Object> getProjectCity() {
		try {
			cacheService.cleanDealerCache();
			cacheService.cleanProjectCache();
			return ApiResponse.newSuccessResponse(Object.class, null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ApiResponse.newErrorResponse(Object.class, e.getMessage());
		}
	}

}
