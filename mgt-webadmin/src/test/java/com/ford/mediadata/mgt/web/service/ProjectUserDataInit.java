package com.ford.mediadata.mgt.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserRoleEnum;
import com.ford.mediadata.mgt.service.project.ProjectUserDealerService;
import com.ford.mediadata.mgt.service.project.ProjectUserService;
import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectUserDataInit extends ApplicationContextTest {

	final private String PROEJCT_CODE = "2017_DREAM_WORKS";

	@Resource
	private ProjectUserService projectUserService;

	@Resource
	private ProjectUserDealerService projectUserDealerService;

	@Test
	public void initAdminProjectUser() {
		try {
			ProjectUserEntity entity = new ProjectUserEntity();
			entity.setProjectCode(PROEJCT_CODE);
			entity.setRole(ProjectUserRoleEnum.ADMIN);
			entity.setUsername("admin");
			entity.setPassword("000");
			projectUserService.save(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void initOperatorProjectUser() {
		try {
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
						ProjectUserEntity projectUserEntity = new ProjectUserEntity();
						BeanUtils.copyProperties(baseInfo, projectUserEntity);
						projectUserEntity.setUsername(usernameN);
						projectUserService.save(projectUserEntity);
						for (ProjectUserDealerEntity dealerBaseInfo : baseInfo.getDealers()) {
							ProjectUserDealerEntity dealerEntity = new ProjectUserDealerEntity();
							BeanUtils.copyProperties(dealerBaseInfo, dealerEntity);
							dealerEntity.setProjectUserId(projectUserEntity.getId());
							projectUserDealerService.save(dealerEntity);
						}
					}
					{
						ProjectUserEntity projectUserEntity = new ProjectUserEntity();
						BeanUtils.copyProperties(baseInfo, projectUserEntity);
						projectUserEntity.setUsername(usernameW);
						projectUserService.save(projectUserEntity);
						for (ProjectUserDealerEntity dealerBaseInfo : baseInfo.getDealers()) {
							ProjectUserDealerEntity dealerEntity = new ProjectUserDealerEntity();
							BeanUtils.copyProperties(dealerBaseInfo, dealerEntity);
							dealerEntity.setProjectUserId(projectUserEntity.getId());
							projectUserDealerService.save(dealerEntity);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private List<ProjectUserBaseInfo> projectUserBaseInfo() {
		List<ProjectUserBaseInfo> baseInfoList = Lists.newArrayList();
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("F");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("江西");
			baseInfo.setEventCity("赣州");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("江西");
			dealer.setCity("赣州");
			dealer.setName("赣州华美行");
			dealer.setCode("A21375");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("K");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("江西");
			baseInfo.setEventCity("宜春");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("江西");
			dealer.setCity("宜春");
			dealer.setName("宜春福鑫");
			dealer.setCode("A21378");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("J");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("河南");
			baseInfo.setEventCity("驻马店");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("河南");
			dealer.setCity("驻马店");
			dealer.setName("驻马店铭阳");
			dealer.setCode("A21538");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("M");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("广西");
			baseInfo.setEventCity("防城港");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("广西");
			dealer.setCity("防城港");
			dealer.setName("防城港恒骐");
			dealer.setCode("A35542");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("A");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("广东");
			baseInfo.setEventCity("中山");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("广东");
			dealer.setCity("中山");
			dealer.setName("中山黄圃中裕");
			dealer.setCode("A37042");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("B");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("福建");
			baseInfo.setEventCity("龙岩");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("福建");
			dealer.setCity("龙岩");
			dealer.setName("龙岩全福");
			dealer.setCode("A07472");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("O");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("山东");
			baseInfo.setEventCity("邹城");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("山东");
			dealer.setCity("邹城");
			dealer.setName("邹城金炫");
			dealer.setCode("A38808");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("P");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("山东");
			baseInfo.setEventCity("枣庄");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("山东");
			dealer.setCity("枣庄");
			dealer.setName("枣庄永乐");
			dealer.setCode("A33585");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("T");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("天津");
			baseInfo.setEventCity("天津");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("天津");
			dealer.setCity("天津");
			dealer.setName("天津远大");
			dealer.setCode("A28735");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("Y");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("河北");
			baseInfo.setEventCity("沧州");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("河北");
			dealer.setCity("沧州");
			dealer.setName("沧州德源");
			dealer.setCode("A09592");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("S");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("江苏");
			baseInfo.setEventCity("扬州");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("江苏");
			dealer.setCity("扬州");
			dealer.setName("城北福联");
			dealer.setCode("A33561");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("ZH");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("浙江");
			baseInfo.setEventCity("金华");
			baseInfo.setSerial("2");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("浙江");
			dealer.setCity("金华");
			dealer.setName("东阳联鑫");
			dealer.setCode("A29297");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("U");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("江苏");
			baseInfo.setEventCity("镇江");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("江苏");
			dealer.setCity("镇江");
			dealer.setName("镇江福联");
			dealer.setCode("A07173");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("Z");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("安徽");
			baseInfo.setEventCity("宣城");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("安徽");
			dealer.setCity("宣城");
			dealer.setName("宣城亚夏福嘉");
			dealer.setCode("A23457");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		{
			ProjectUserBaseInfo baseInfo = new ProjectUserBaseInfo();
			baseInfo.setProjectCode(PROEJCT_CODE);
			baseInfo.setUsernamePrefix("X");
			baseInfo.setPassword("123456");
			baseInfo.setRole(ProjectUserRoleEnum.OPERATOR);
			baseInfo.setEventProvince("浙江");
			baseInfo.setEventCity("义乌");
			baseInfo.setSerial("1");
			List<ProjectUserDealerEntity> dealers = Lists.newArrayList();
			ProjectUserDealerEntity dealer = new ProjectUserDealerEntity();
			dealer.setProvince("浙江");
			dealer.setCity("义乌");
			dealer.setName("义乌瑞鑫");
			dealer.setCode("A05793");
			dealers.add(dealer);
			baseInfo.setDealers(dealers);
			baseInfoList.add(baseInfo);
		}
		return baseInfoList;
	}

	@Getter
	@Setter
	private class ProjectUserBaseInfo extends ProjectUserEntity {
		private List<ProjectUserDealerEntity> dealers;
		private String usernamePrefix;
	}

}
