package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * 一覧画面DTOクラス
 *
 * @author ***
 *
 */
@Data
public class IssueManagementListInfo {

	/** 課題管理番号 */
	 private String controlNumber;

	 /** 発生日 */
	 private LocalDate accrualDate;

	 /** 期限 */
	 private  LocalDate deadline;

	 /** 完了日 */
	 private LocalDate completionDate;

	 /** 記入者 */
	 private String writtenBy;

	 /** 重要度 */
	 private String importance;

	 /** 状況 */
	 private String situation;

	 /** 担当者 */
	 private String manager;

	 /** 分類 */
	 private String classification;

	 /** 課題概要 */
	 private String assignmentOverview;

}
