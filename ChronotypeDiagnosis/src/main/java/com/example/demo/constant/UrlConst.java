package com.example.demo.constant;

/**
 * URL定数クラス
 *
 * @author *****
 *
 */
public class UrlConst {

	/** ログイン画面 */
	public static final String LOGIN = "/login";

	/** ユーザー登録画面 */
	public static final String SIGNUP = "/signup";

	/** ユーザー登録情報確認画面 */
	public static final String SIGNUP_CONFIRM = "/signupConfirm";

	/** ユーザー登録情報確認結果画面 */
	public static final String SIGNUP_COMPLETION = "/signupCompletion";

	/** メニュー画面 */
	public static final String MENU = "/menu";

	/** ユーザー一覧画面 */
	public static final String USER_LIST = "/userList";

	/** ユーザー編集画面 */
	public static final String USER_EDIT = "/userEdit";

	/** 初期表示画面 */
	public static final String INIT = "/init";

	/** 登録画面 */
	public static final String REGISTER = "/registerView";

	/** 診断画面 */
	public static final String DIAGNOSIS= "/diagnosis";

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { MENU, INIT, LOGIN, SIGNUP, SIGNUP_CONFIRM, SIGNUP_COMPLETION,DIAGNOSIS,
			"/webjars/**", "/css/**", "/js/**", "/img/**" };
}
