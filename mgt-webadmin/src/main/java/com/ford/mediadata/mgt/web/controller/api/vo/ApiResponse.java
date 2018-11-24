package com.ford.mediadata.mgt.web.controller.api.vo;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

	private String code;

	private String message;

	private T body;

	private ApiResponse() {
	}

	public static <T> ApiResponse<T> newSuccessResponse(Class<T> clazz, T body) {
		return newResponse(clazz, ApiCode.SUCCESS, ApiCode.SUCCESS.name(), body);
	}

	public static <T> ApiResponse<T> newErrorResponse(Class<T> clazz, String message) {
		return newResponse(clazz, ApiCode.ERROR, message, null);
	}

	private static <T> ApiResponse<T> newResponse(Class<T> clazz, ApiCode code, String message, T body) {
		Assert.notNull(clazz);
		Assert.notNull(code);
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setCode(code.getCode());
		apiResponse.setMessage(message);
		apiResponse.setBody(body);
		return apiResponse;
	}

}
