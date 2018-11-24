package com.ford.mediadata.mgt.entity.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ford.mediadata.mgt.entity.VersionEntity;

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
@Entity
@Table(name = "MD_COMMON_MAP")
public class CommonMapEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_COMMON_MAP")
	@SequenceGenerator(name = "SEQ_MD_COMMON_MAP", allocationSize = 1, sequenceName = "SEQ_MD_COMMON_MAP")
	private Long id;

	/**
	 * KEY
	 */
	@Column(name = "MAP_KEY")
	private String mapKey;

	/**
	 * VALUE
	 */
	@Column(name = "MAP_VALUE")
	private String mapValue;

}
