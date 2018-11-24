package com.ford.mediadata.mgt.web.controller.api;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;
import com.ford.mediadata.mgt.web.controller.api.vo.basic.CityVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO.CityDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectCityVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectUserVO;
import com.ford.mediadata.mgt.web.controller.api.vo.project.ProjectUserVO.DealerVO;
import com.google.common.collect.Lists;

import io.dabing.common.util.ResourceUtils;
import lombok.Getter;
import lombok.Setter;

public class ApiDataGenerator {

	// 生成活动城市配置文件

	final private String PROJECT_CODE = "2017_DREAM_WORKS";

	@Test
	public void projectCity() {
		String json = ResourceUtils.readString("api/getDealerTree.json");
		List<ProvinceDealerVO> provinceDealerVOList = JSON.parseArray(json, ProvinceDealerVO.class);
		List<CityVO> cityList = Lists.newArrayList();
		for (ProvinceDealerVO provinceDealerVO : provinceDealerVOList) {
			for (CityDealerVO cityDealerVO : provinceDealerVO.getCityDealerList()) {
				CityVO cityVO = new CityVO();
				cityVO.setName(cityDealerVO.getCityName());
				cityList.add(cityVO);
			}
		}
		ProjectCityVO projectCityVO = new ProjectCityVO();
		projectCityVO.setBeginTime(DateTime.now().minusMonths(6).toDate());
		projectCityVO.setEndTime(DateTime.now().plusMonths(6).toDate());
		projectCityVO.setProjectCode(PROJECT_CODE);
		projectCityVO.setCityList(cityList);
		System.out.println(JSON.toJSONString(projectCityVO, true));
	}

	// 生成活动用户配置文件

	@Test
	public void projectUser() {
		List<ProjectUserVO> projectUserVOList = Lists.newArrayList();
		for (ProjectUserBaseInfo baseInfo : projectUserBaseInfo()) {
			for (int i = 1; i <= 20; i++) {
				String num = "";
				if (i < 100) {
					num += "0";
				}
				if (i < 10) {
					num += "0";
				}
				num += i;
				String usernameN = baseInfo.getUsernamePrefix() + "N" + num;
				String usernameW = baseInfo.getUsernamePrefix() + "W" + num;
				{
					ProjectUserVO projectUserVO = new ProjectUserVO();
					BeanUtils.copyProperties(baseInfo, projectUserVO);
					projectUserVO.setUsername(usernameN);
					List<DealerVO> dealers = Lists.newArrayList();
					DealerVO dealerVO = new DealerVO();
					BeanUtils.copyProperties(baseInfo.getDealers().get(0), dealerVO);
					dealers.add(dealerVO);
					projectUserVO.setDealers(dealers);
					projectUserVOList.add(projectUserVO);
				}
				{
					ProjectUserVO projectUserVO = new ProjectUserVO();
					BeanUtils.copyProperties(baseInfo, projectUserVO);
					projectUserVO.setUsername(usernameW);
					List<DealerVO> dealers = Lists.newArrayList();
					DealerVO dealerVO = new DealerVO();
					BeanUtils.copyProperties(baseInfo.getDealers().get(0), dealerVO);
					dealers.add(dealerVO);
					projectUserVO.setDealers(dealers);
					projectUserVOList.add(projectUserVO);
				}
			}
		}
		System.out.println(JSON.toJSONString(projectUserVOList, true));
	}

	private List<ProjectUserBaseInfo> projectUserBaseInfo() {
		List<ProjectUserBaseInfo> baseInfoList = Lists.newArrayList();
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("J");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("安阳");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("河南");
			dealer.setName("安阳顺达尔");
			dealer.setCode("A19780");
			dealer.setCity("安阳");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("K");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("常德");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("湖南");
			dealer.setName("常德日福");
			dealer.setCode("A13695");
			dealer.setCity("常德");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("F");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("郴州");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("湖南");
			dealer.setName("郴州中天");
			dealer.setCode("A14415");
			dealer.setCity("郴州");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("A");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("广州");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("广东");
			dealer.setName("广州增福");
			dealer.setCode("A33660");
			dealer.setCity("广州");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("M");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("河源");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("广东");
			dealer.setName("河源共成");
			dealer.setCode("A20240");
			dealer.setCity("河源");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("Y");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("常熟");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("江苏");
			dealer.setName("常熟德福");
			dealer.setCode("A07195");
			dealer.setCity("常熟");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("Z");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("诸暨");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("浙江");
			dealer.setName("诸暨中大");
			dealer.setCode("A08772");
			dealer.setCity("诸暨");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("O");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("南京");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("江苏");
			dealer.setName("溧水福联");
			dealer.setCode("A30296");
			dealer.setCity("南京");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("T");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("马鞍山");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("安徽");
			dealer.setName("马鞍山福联");
			dealer.setCode("A17156");
			dealer.setCity("马鞍山");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setUsernamePrefix("X");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventCity("金华");
			baseInfo.setSerial("1");
			List<DealerVO> dealers = Lists.newArrayList();
			DealerVO dealer = new DealerVO();
			dealer.setProvince("浙江");
			dealer.setName("金华福达");
			dealer.setCode("A05892");
			dealer.setCity("金华");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		return baseInfoList;
	}

	@Getter
	@Setter
	private class ProjectUserBaseInfo extends ProjectUserVO {
		private String usernamePrefix;
	}

}
