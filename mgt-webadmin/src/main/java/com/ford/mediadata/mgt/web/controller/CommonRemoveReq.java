package com.ford.mediadata.mgt.web.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用批量删除请求对象
 * 
 * @author chunliang.you
 *
 */
@Getter
@Setter
public class CommonRemoveReq {

	private List<Data> reqs;

	@Getter
	@Setter
	public static class Data {

		private Long id;

	}
}
