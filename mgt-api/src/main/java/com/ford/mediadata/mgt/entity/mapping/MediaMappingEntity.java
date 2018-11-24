package com.ford.mediadata.mgt.entity.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ford.mediadata.mgt.entity.VersionEntity;
import com.ford.mediadata.mgt.entity.media.MediaTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 渠道Mapping表
 * 
 * @author chunliang.you
 *
 */
@Getter
@Setter
@Entity
@Table(name = "MD_MEDIA_MAPPING")
public class MediaMappingEntity extends VersionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MD_MEDIA_MAPPING")
	@SequenceGenerator(name = "SEQ_MD_MEDIA_MAPPING", allocationSize = 1, sequenceName = "SEQ_MD_MEDIA_MAPPING")
	private Long id;

	/**
	 * 媒体渠道标识
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "MEDIA_TYPE")
	private MediaTypeEnum mediaType;

	/**
	 * MAPPING类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "MAPPING_TYPE")
	private MappingTypeEnum mappingType;

	/**
	 * 渠道值
	 */
	@Column(name = "MEDIA_VALUE")
	private String mediaValue;

	/**
	 * 系统值
	 */
	@Column(name = "SYS_VALUE")
	private String sysValue;

	/**
	 * 说明
	 */
	@Column(name = "DESCRIPTION")
	private String description;

}
