package com.ford.mediadata.mgt.service.excel.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	public int col();

	public ColType colType() default ColType.STRING;

	public String dateFormat() default "";

	public static enum ColType {
		STRING, DATE
	}

}
