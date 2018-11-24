package com.ford.mediadata.mgt.web.controller.api;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO.CityDealerVO;
import com.ford.mediadata.mgt.web.controller.api.vo.dealer.ProvinceDealerVO;
import com.ford.mediadata.mgt.web.controller.project.vo.EditProjectCityRequest;
import com.google.common.collect.Lists;

import io.dabing.common.util.ResourceUtils;
import lombok.Getter;
import lombok.Setter;

public class ProvinceCityTreeJSONGenerator {

	@Test
	public void tree() {
		String json = ResourceUtils.readString("api/getDealerTree.json");
		List<ProvinceDealerVO> provinceDealerVOList = JSON.parseArray(json, ProvinceDealerVO.class);
		List<Tree> root = Lists.newArrayList();
		// 顶层节点
		Tree country = new Tree();
		country.setText("中国");
		country.setTreeId("C_中国");
		root.add(country);
		// 遍历省份
		for (ProvinceDealerVO provinceDealerVO : provinceDealerVOList) {
			Tree province = new Tree();
			province.setText(provinceDealerVO.getProvinceName());
			province.setTreeId("P_" + provinceDealerVO.getProvinceName());
			EditProjectCityRequest.ProvinceCity provinceAttr = new EditProjectCityRequest.ProvinceCity();
			provinceAttr.setProvince(provinceDealerVO.getProvinceName());
			province.setAttr(provinceAttr);
			country.getChildren().add(province);
			// 遍历城市
			for (CityDealerVO cityDealerVO : provinceDealerVO.getCityDealerList()) {
				Tree city = new Tree();
				city.setText(cityDealerVO.getCityName());
				city.setTreeId(cityDealerVO.getCityName());
				EditProjectCityRequest.ProvinceCity cityAttr = new EditProjectCityRequest.ProvinceCity();
				cityAttr.setProvince(provinceDealerVO.getProvinceName());
				cityAttr.setCity(cityDealerVO.getCityName());
				city.setAttr(cityAttr);
				province.getChildren().add(city);
			}
		}
		System.out.println(JSON.toJSONString(root, false));
	}

	@Test
	public void simpleTree() {
		String json = ResourceUtils.readString("api/getDealerTree.json");
		List<ProvinceDealerVO> provinceDealerVOList = JSON.parseArray(json, ProvinceDealerVO.class);
		List<SimpleTree> provinceList = Lists.newArrayList();
		// 遍历省份
		for (ProvinceDealerVO provinceDealerVO : provinceDealerVOList) {
			SimpleTree province = new SimpleTree();
			province.setName(provinceDealerVO.getProvinceName());
			province.setValue(provinceDealerVO.getProvinceName());
			provinceList.add(province);
			// 遍历城市
			for (CityDealerVO cityDealerVO : provinceDealerVO.getCityDealerList()) {
				SimpleTree city = new SimpleTree();
				city.setName(cityDealerVO.getCityName());
				city.setValue(cityDealerVO.getCityName());
				province.getChildren().add(city);
			}
		}
		System.out.println(JSON.toJSONString(provinceList, false));
	}

	@Getter
	@Setter
	public static class Tree {
		private String text;
		private String treeId;
		private boolean opened = true;
		private boolean canSelect = true;
		private boolean selected = false;
		private boolean disabled = false;
		private EditProjectCityRequest.ProvinceCity attr;
		private List<Tree> children = Lists.newArrayList();
	}

	@Getter
	@Setter
	public static class SimpleTree {
		private String name;
		private String value;
		private List<SimpleTree> children = Lists.newArrayList();
	}

}
