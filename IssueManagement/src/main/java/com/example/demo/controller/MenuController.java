package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.AuthorityKind;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * メニュー画面Controllerクラス
 *
 * @author ***
 *
 */
@Controller
@RequiredArgsConstructor
public class MenuController {

	/** セッションオブジェクト */
	private final HttpSession session;

	/**
	 * 画面の初期表示を行います。
	 *
	 * <p>その際、ユーザー情報から権限を確認し、ユーザー管理が可能かどうかを判定した結果を画面に渡します。
	 *
	 * @param user 認証済みユーザー情報
	 * @param model モデル
	 * @return メニュー画面テンプレート名
	 */
	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model) {

		//取得コードが2か3の場合、商品の確認と更新が可能
		boolean hasUserManageAuth3 = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		boolean hasUserManageAuth2 = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_MANAGER.getCode()));

		//取得コードが0の場合登録内容不正
		boolean hasUserManageAuth0 = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.UNKNOWN.getCode()));

		boolean mdAuthorityKind = false;
		if (hasUserManageAuth3 || hasUserManageAuth2) {
			mdAuthorityKind = true;
		}

		boolean mdAuthorityKindUnknown = false;
		if (hasUserManageAuth0) {
			mdAuthorityKindUnknown = true;
		}

		model.addAttribute("hasUserManageAuth", hasUserManageAuth3);
		session.setAttribute("mdAuthorityKind", mdAuthorityKind);
		session.setAttribute("mdAuthorityKindUnknown", mdAuthorityKindUnknown);
		session.setAttribute("loginId", user.getUsername());
		return ViewNameConst.MENU;
	}

}
