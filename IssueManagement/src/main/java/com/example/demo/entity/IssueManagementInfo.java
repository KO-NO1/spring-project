package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 課題管理情報テーブルEntityクラス
 *
 * @author ***
 *
 */
@Entity
@Table(name = "Issue_Management_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueManagementInfo {


	/** 課題管理番号 */
	@Id
	@Column(name = "control_number")
	private	 String	controlNumber;

	/** 発生日 */
	@Column(name = "accrual_date")
	private	 LocalDate	accrualDate;

	/** 期限 */
	@Column(name = "deadline")
	private	 LocalDate	deadline;

	/** 完了日 */
	@Column(name = "completion_date")
	private	 LocalDate	completionDate;

	/** 記入者 */
	@Column(name = "written_by")
	private	 String	writtenBy;

	/** 重要度 */
	@Column(name = "importance")
	private	 String	importance;

	/** 状況 */
	@Column(name = "situation")
	private	 String	situation;
	/** 担当者 */
	@Column(name = "manager")
	private	 String	manager;

	/** 分類 */
	@Column(name = "classification")
	private	 String	classification;

	/** 課題概要 */
	@Column(name = "assignment_overview")
	private	 String	assignmentOverview;

}