package com.ford.mediadata.mgt.web.controller.project;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.ford.mediadata.mgt.entity.dealer.DealerEntity;
import com.ford.mediadata.mgt.entity.project.ProjectUserDealerEntity;
import com.ford.mediadata.mgt.service.dealer.DealerService;
import com.ford.mediadata.mgt.service.project.ProjectUserDealerService;
import com.ford.mediadata.mgt.web.controller.project.vo.EditProjectUserDealerRequest;
import com.ford.mediadata.mgt.web.controller.project.vo.EditProjectUserDealerRequest.DealerVO;
import com.ford.mediadata.mgt.web.controller.vo.TreeVO;

import io.dabing.core.web.annotation.ApiService;

/**
 * 项目活动用户关联经销商
 * 
 * @author chunliang.you
 *
 */
@Controller
public class ProjectUserDealerController {

	@Resource
	private DealerService dealerService;

	@Resource
	private ProjectUserDealerService projectUserDealerService;

	@ApiService(transCode = "getProjectUserDealerTree")
	public TreeVO<DealerEntity> getTree(ProjectUserDealerEntity condition) {
		Assert.notNull(condition.getProjectUserId(), "用户ID为空");
		List<DealerEntity> allDealerList = dealerService.findAll();
		List<ProjectUserDealerEntity> selectedDealerList = projectUserDealerService.list(condition);
		return buildTree(allDealerList, selectedDealerList);
	}

	private TreeVO<DealerEntity> buildTree(List<DealerEntity> allDealerList,
			List<ProjectUserDealerEntity> selectedDealerList) {
		TreeVO<DealerEntity> countryNode = new TreeVO<DealerEntity>();
		{
			// 顶层节点
			countryNode.setText("中国");
			countryNode.setTreeId("CO_中国");
			countryNode.setOpened(Boolean.TRUE);
		}
		{
			// 遍历allDealerList，放入节点
			for (DealerEntity dealer : allDealerList) {
				// 判断是否存在省份节点，不存在则新建
				TreeVO<DealerEntity> provinceNode = getTree(countryNode.getChildren(), "PR_" + dealer.getProvince());
				if (provinceNode == null) {
					provinceNode = new TreeVO<DealerEntity>();
					provinceNode.setText(dealer.getProvince());
					provinceNode.setTreeId("PR_" + dealer.getProvince());
					countryNode.getChildren().add(provinceNode);
				}
				// 判断是否存在城市节点，不存在则新建
				TreeVO<DealerEntity> cityNode = getTree(provinceNode.getChildren(), "CI_" + dealer.getCity());
				if (cityNode == null) {
					cityNode = new TreeVO<DealerEntity>();
					cityNode.setText(dealer.getCity());
					cityNode.setTreeId("CI_" + dealer.getCity());
					provinceNode.getChildren().add(cityNode);
				}
				// 加入经销商节点
				TreeVO<DealerEntity> dealerNode = new TreeVO<DealerEntity>();
				dealerNode.setText(dealer.getName());
				dealerNode.setTreeId(dealer.getCode());
				dealerNode.setAttr(dealer);
				cityNode.getChildren().add(dealerNode);
				// 判断是否选中
				for (ProjectUserDealerEntity selectedDealer : selectedDealerList) {
					if (dealer.getCode().equals(selectedDealer.getCode())) {
						dealerNode.setSelected(Boolean.TRUE);
						cityNode.setSelected(Boolean.TRUE);
						provinceNode.setSelected(Boolean.TRUE);
						countryNode.setSelected(Boolean.TRUE);
						break;
					}
				}
			}
		}
		return countryNode;
	}

	private TreeVO<DealerEntity> getTree(List<TreeVO<DealerEntity>> treeList, String treeId) {
		for (TreeVO<DealerEntity> tree : treeList) {
			if (tree.getTreeId().equals(treeId)) {
				return tree;
			}
		}
		return null;
	}

	@ApiService(transCode = "editProjectUserDealer")
	public void edit(EditProjectUserDealerRequest req) {
		Assert.notNull(req.getProjectUserId(), "用户ID为空");
		ProjectUserDealerEntity condition = new ProjectUserDealerEntity();
		condition.setProjectUserId(req.getProjectUserId());
		List<ProjectUserDealerEntity> toDeletEntities = projectUserDealerService.list(condition);
		for (ProjectUserDealerEntity toDeletEntitie : toDeletEntities) {
			projectUserDealerService.delete(toDeletEntitie.getId());
		}
		if (CollectionUtils.isNotEmpty(req.getSelectedDealerList())) {
			for (DealerVO dealer : req.getSelectedDealerList()) {
				if (dealer != null) {
					ProjectUserDealerEntity toSaveEntity = new ProjectUserDealerEntity();
					toSaveEntity.setProjectUserId(req.getProjectUserId());
					toSaveEntity.setProvince(dealer.getProvince());
					toSaveEntity.setCity(dealer.getCity());
					toSaveEntity.setCode(dealer.getCode());
					toSaveEntity.setName(dealer.getName());
					projectUserDealerService.save(toSaveEntity);
				}
			}
		}
	}

}
