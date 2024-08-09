package com.example.demo.constant;

import com.example.demo.entity.IssueManagementInfo;

import lombok.Data;

/**
 * 課題管理情報編集結果クラス
 *
 * @author ***
 *
 */
@Data
public class IssueManagementEditResult {

	/**　情報更新結果entity */
	private IssueManagementInfo issueManagementInfo;

	/** 課題管理情報更新結果メッセージEnum */
	private IssueManagementEditMessage updateMessage;
}
