package com.example.demo.form;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 課題管理登録画面Formクラス
 *
 * @author ***
 *
 */
@Data
public class IssueManagementForm {

	/** 課題管理番号 */
	 @Length(min = 5, max = 5, message = "{Length_Check}")
	 private String controlNumber;

	 /** 発生日 */
	 private LocalDate accrualDate;

	 /** 期限 */
	 private  LocalDate deadline;

	 /** 完了日 */
	 private LocalDate completionDate;

	 /** 記入者 */
	 @Length(min = 1, max = 20, message = "{Length_Check}")
	 private String writtenBy;

	 /** 重要度 */
	 private String importance;

	 /** 状況 */
	 private String situation;

	 /** 担当者 */
	 @Length(min = 1, max = 20, message = "{Length_Check}")

	 private String manager;

	 /** 分類 */
	 private String classification;

	 /** 課題概要 */
	 private String assignmentOverview;

	 /** 課題管理一覧情報から選択されたログインID */
	 @Length(min = 0, max = 300)
	 private String selectedControlNumber;

	/**
	 * 課題管理一覧情報から選択されたログインIDをクリアします。
	 *
	 * @return 課題管理一覧情報から選択されたログインIDクリア後のインスタンス
	 */
	public IssueManagementForm clearSelectedControlNumber() {
		this.selectedControlNumber = null;

		return this;
	}
}
