package com.example.demo.service;

import com.example.demo.dto.UserEditResult;
import com.example.demo.entity.UserInfo;

/**
 * ユーザー一覧画面Serviceインターフェース
 *
 * @author *****
 *
 */
public interface DagnosisService {

	/**
	 * 診断結果を登録します。
	 *
	 * @param loginId ログインID
	 * @param diagnosticResult 診断結果
	 * @return 更新結果
	 */
	public UserEditResult registerUserInfoByDiagnosticResult(String loginId, int diagnosticResult);

	/**
	 * 診断結果を取得します。
	 *
	 * @return 診断結果
	 */
	public UserInfo getDiagnosticHistory(String argsloginId);

}
