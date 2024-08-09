package com.example.demo.form;

import lombok.Data;

/**
 * ログイン画面Formクラス
 * 
 * @author ***
 *
 */
@Data
public class LoginForm {

	/** ログインID */
	private String loginId;

	/** パスワード */
	private String password;
}
