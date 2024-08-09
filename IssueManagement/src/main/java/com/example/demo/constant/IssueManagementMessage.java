package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 課題管理更新結果メッセージEnumクラス
 *
 * @author ***
 *
 */
@Getter
@AllArgsConstructor
public enum IssueManagementMessage {

	/* エラーなし */
	SUCCEED(MessageConst.REGISTER_SUCCEED),

	/* 既に登録済み */
	FAILUR_BY_ALREADY_COMPLETED(MessageConst.ALREADY_COMPLETED);

	String messageId;
}
