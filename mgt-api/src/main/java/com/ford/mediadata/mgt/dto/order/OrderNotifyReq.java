package com.ford.mediadata.mgt.dto.order;

import lombok.Data;

@Data
public class OrderNotifyReq {

	/**
	 * 时间：YYYYMMDDHHmmssSSS
	 */
	private String date;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 数据状态标识：0-新增，1-更新
	 */
	private Integer dataType;

}
