package com.example.demo.service;

import java.util.List;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.UserEditResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;

/**
 * ユーザー一覧画面Serviceインターフェース
 *
 * @author *****
 *
 */
public interface UserListService {

	/**
	 * ユーザー情報テーブルを全件検索し、ユーザー一覧画面に必要な情報へ変換して返却します。
	 *
	 * @return ユーザー情報テーブルの全登録情報
	 */
	public List<UserListInfo> editUserList();

	/**
	 * ユーザー情報を条件検索した結果を画面の表示用に変換して返却します。
	 *
	 * @param dto 検索に使用するパラメーター
	 * @return 検索結果
	 */
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto);

	/**
	 * 指定されたIDのユーザー情報を削除します。
	 *
	 * @param loginId ログインID
	 * @return 実行結果(エラー有無)
	 */
	public UserDeleteResult deleteUserInfoById(String loginId);

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
	public int getDiagnosticHistory(String argsloginId);

}
