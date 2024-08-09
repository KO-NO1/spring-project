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
 * @author *****
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

	
		//取得コードが1の場合、診断結果確認のみ可能
		boolean hasUserManageAuth1 = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.DIAGNOSTIC_WATCHER.getCode()));

		//取得コードが2の場合、診断結果確認とユーザー管理の更新が可能
		boolean hasUserManageAuth2 = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.DIAGNOSTIC_MANAGER.getCode()));


		boolean diagnosisAuthorityKind = false;
		if ( hasUserManageAuth1) {
			diagnosisAuthorityKind = true;
		}

		boolean managementAuthorityKind = false;
		if ( hasUserManageAuth2) {
			managementAuthorityKind = true;
		}

		session.setAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
		session.setAttribute("managementAuthorityKind", managementAuthorityKind);
		session.setAttribute("loginId", user.getUsername());

		model.addAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
		model.addAttribute("managementAuthorityKind", managementAuthorityKind);
		
		return ViewNameConst.MENU;
	}

}
