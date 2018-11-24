package com.ford.mediadata.mgt.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface OrderNotifyError {

	String getCode();

	String getMessage();

	@Getter
	@AllArgsConstructor
	public enum DefaultNotifyError implements OrderNotifyError {

		// 000
		E000("000", "OK"),
		// 001
		E001("001", "日期参数不为空"),
		// 002
		E002("002", "签名参数不为空"),
		// 003
		E003("003", "签名错误"),
		// 004
		E004("004", "数据状态不正确"),
		// 998
		E998("998", "请求为空"),
		// 999
		E999("999", "未知错误"),
		// EOF
		;

		private String code;

		private String message;

	}

}
