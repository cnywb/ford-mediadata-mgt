package com.ford.mediadata.mgt.entity.mapping;

import lombok.Getter;
import lombok.Setter;

/**
 * Map表，仅用来存储K/V数据
 * 
 * @author chunliang.you
 *
 */
@Getter
@Setter
public class CommonMapEntityCondition extends CommonMapEntity {

	/**
	 * KEY LIKE
	 */
	private String mapKeyLike;

}
