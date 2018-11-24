package com.ford.mediadata.mgt.web.controller.vo;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeVO<T> {

	private Boolean canSelect = Boolean.TRUE;

	private Boolean disabled = Boolean.FALSE;

	private Boolean opened = Boolean.FALSE;

	private Boolean selected = Boolean.FALSE;

	private String text;

	private String treeId;

	private T attr;

	private List<TreeVO<T>> children = Lists.newArrayList();

}
