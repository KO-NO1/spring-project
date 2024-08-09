package com.example.demo.constant.db;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー権限種別
 *
 * @author *****
 */
@Getter
@AllArgsConstructor
public enum AuthorityKind {

	/* 登録内容が不正 */
	UNKNOWN("", "登録内容が不正"),

	/* 診断結果の確認、登録が可能 */
	DIAGNOSTIC_WATCHER("1", "診断情報の確認、登録が可能"),

	/* 診断結果の確認、登録とユーザー管理情報の確認、更新が可能 */
	DIAGNOSTIC_MANAGER("2", "診断履歴の確認、登録とユーザー管理情報の更新の更新が可能");

	
	

	/** コード値 */
	private String code;

	/** 画面表示する説明文 */
	private String displayValue;

	/**
	 * Enum逆引き
	 *
	 * 権限種別からEnumを逆引きします。
	 *
	 * @param code 権限種別コード値
	 * @return 引数の権限種別コード値に対応するEnum、ただし見つからなかった場合はUNKNOWN
	 */
	public static AuthorityKind from(String code) {
		return Arrays.stream(AuthorityKind.values())
				.filter(authorityKind -> authorityKind.getCode().equals(code))
				.findFirst()
				.orElse(UNKNOWN);
	}
}
