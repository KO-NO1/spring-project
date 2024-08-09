package com.example.demo.dto;

import com.example.demo.constant.IssueManagementEditMessage;
import com.example.demo.entity.IssueManagementInfo;

import lombok.Data;

/**
 * 商品情報編集結果DTOクラス
 *
 * @author ***
 *
 */
@Data
public class IssueManagementEditResult {

	/**　商品情報更新結果 */
	private IssueManagementInfo issueManagementInfo;

	/** 商品情報更新結果メッセージEnum */
	private IssueManagementEditMessage updateMessage;
}
