package com.ford.mediadata.mgt.dto.order;

import lombok.Data;

@Data
public class OrderNotifyResp {

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 错误代码
	 */
	private String code;

	public OrderNotifyResp() {
	}

	public OrderNotifyResp(OrderNotifyError error) {
		this.code = error.getCode();
		this.message = error.getMessage();
	}

}
