package com.ford.mediadata.mgt.web.controller.inport.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileUploadResponse {

	private Boolean success;

	private Integer dataSize;

	private String message;

}
