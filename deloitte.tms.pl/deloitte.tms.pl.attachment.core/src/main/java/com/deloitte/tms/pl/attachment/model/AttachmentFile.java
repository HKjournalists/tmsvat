package com.deloitte.tms.pl.attachment.model;

// Generated 2013-5-23 15:22:34 by Hibernate Tools 3.2.0.beta8

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * File generated by hbm2java
 */
@Entity
@Table(name=TablePreDef.BASEPRE+"FILE")
public class AttachmentFile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 428433799089338769L;
	// Property accessors
	@Id
	@TableGenerator(name = "FILE_GENERATOR", pkColumnValue = "FILE", table = "TBL_SEQUENCE", pkColumnName = "TYPE", valueColumnName = "VALUE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FILE_GENERATOR")
	@Column(name = "ID",  length = 16)
	private Long id;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "FILE_PATH")
	private String filePath;
	@ModelProperty(comment="相对路径")
	@Column(name = "relativePath")
	private String relativePath;
	@ModelProperty(comment="绝对路径")
	@Column(name = "ABSOLUTEPATH")
	private String absolutePath;
	@ModelProperty(comment="存储方式")
	@Column(name = "storeType")
	private String storeType;
	@Column(name = "FILE_SIZE")
	private Long fileSize;
	@Column(name = "FILE_TYPE")
	private String fileType;
	@Temporal(TemporalType.DATE)
	@Column(name = "FILE_TIME")
	private Date fileTime;
	@Column(name = "FILE_SAVE")
	private String fileSave;
	@Column(name = "RELATION_ID")
	private String relationId;
	@Column(name = "GROUP_ID")
	private String groupId;
	@Column(name = "FLAG")
	private String flag;
	@Column(name = "CONTENT")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = (Long) id;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getFileTime() {
		return this.fileTime;
	}

	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}

	public String getFileSave() {
		return this.fileSave;
	}

	public void setFileSave(String fileSave) {
		this.fileSave = fileSave;
	}

	public String getRelationId() {
		return this.relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

}
