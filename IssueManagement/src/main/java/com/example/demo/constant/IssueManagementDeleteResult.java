package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果種別
 * 
 * @author ***
 */
@Getter
@AllArgsConstructor
public enum IssueManagementDeleteResult {

	/* エラーなし */
	SUCCEED(MessageConst.DELETE_SUCCEED),

	/* エラーあり */
	ERROR(MessageConst.NON_EXISTED_ID);

	/** メッセージID */
	private String messageId;

}
