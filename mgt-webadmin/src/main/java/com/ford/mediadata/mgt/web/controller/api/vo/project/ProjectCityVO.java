package com.ford.mediadata.mgt.web.controller.api.vo.project;

import java.util.Date;
import java.util.List;

import com.ford.mediadata.mgt.web.controller.api.vo.basic.CityVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCityVO {

	private String projectCode;

	private Date beginTime;

	private Date endTime;

	private List<CityVO> cityList;

}
