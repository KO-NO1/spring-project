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
public enum IssueManagementEditMessage {

	/** 更新成功 */
	SUCCEED(MessageConst.UPDATE_SUCCEED),

	/** 更新失敗 */
	FAILED(MessageConst.UPDATE_FAILED),

	/** 既に登録済み　*/
	ALREADYCOMPLETED(MessageConst.ALREADY_COMPLETED);

	/** メッセージID */
	private String messageId;
}
