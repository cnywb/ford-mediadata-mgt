package com.ford.mediadata.mgt.dto.order;

import lombok.Getter;

public class OrderNotifyException extends RuntimeException {

	private static final long serialVersionUID = 1057207177043105951L;

	@Getter
	final private OrderNotifyError orderNotifyError;

	public OrderNotifyException(OrderNotifyError orderNotifyError) {
		super(orderNotifyError.getCode() + ":" + orderNotifyError.getMessage());
		this.orderNotifyError = orderNotifyError;
	}

}
