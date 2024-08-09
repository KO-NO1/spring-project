package com.example.demo.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.Classification;
import com.example.demo.constant.IssueManagementDeleteResult;
import com.example.demo.constant.IssueManagementMessage;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.form.IssueManagementForm;
import com.example.demo.service.IssueManagementService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 課題管理画面Controllerクラス
 *
 * @author ***
 *
 */
@Controller
@RequiredArgsConstructor
public class IssueManagementController {

	/** メッセージソース */
	private final MessageSource messageSource;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** 課題管理一覧画面Serviceクラス */
	private final IssueManagementService service;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** モデルキー：課題管理情報リスト */
	private static final String KEY_DTO_LIST_INFO = "dtoListInfo";

	/** モデルキー：課題管理情報フォーム */
	private static final String KEY_ISSUEMANAGEMENT_FORM = "IssueManagementForm";

	/**
	 * 画面の初期表示を行います。
	 *
	 * @param model モデル
	 * @param form 入力情報
	 * @return 課題管理一覧画面テンプレート名
	 */
	@GetMapping(UrlConst.ISSUEMANAGEMENT_LIST)
	public String view(Model model, IssueManagementForm form) {

		if ( session.getAttribute(SessionKeyConst.SELECETED_CONTROLNUMBER) != null) {
			session.removeAttribute(SessionKeyConst.SELECETED_CONTROLNUMBER);
		}

		//課題管理一覧取得
		List<IssueManagementListInfo> dtoListInfo = service.searchList();

		//trueの場合、確認と更新が可能
		var mdAuthorityKind = session.getAttribute("mdAuthorityKind");
		//tureの場合確認と更新が不可
		var mdAuthorityKindUnknown = session.getAttribute("mdAuthorityKindUnknown");

		model.addAttribute(KEY_DTO_LIST_INFO, dtoListInfo);
		model.addAttribute("form", form);
		model.addAttribute("mdAuthorityKind", mdAuthorityKind);
		model.addAttribute("mdAuthorityKindUnknown", mdAuthorityKindUnknown);

		return ViewNameConst.ISSUEMANAGEMENT_LIST;
	}

	/**
	 * 検索条件に合致する課題管理情報を画面に表示します。
	 *
	 * @param model モデル
	 * @param form 入力情報
	 * @return 課題管理一覧画面テンプレート名
	 */
	@PostMapping(value = UrlConst.ISSUEMANAGEMENT_LIST, params = "search")
	public String search(Model model, IssueManagementForm form) {

		var searchDto = mapper.map(form, IssueManagementListInfo.class);
		//課題管理名で検索実行
		List<IssueManagementListInfo> dtoListInfo = service.searchByParam(searchDto);

		var mdAuthorityKind = session.getAttribute("mdAuthorityKind");
		var mdAuthorityKindUnknown = session.getAttribute("mdAuthorityKindUnknown");

		model.addAttribute("mdAuthorityKind", mdAuthorityKind);
		model.addAttribute("mdAuthorityKindUnknown", mdAuthorityKindUnknown);
		model.addAttribute(KEY_DTO_LIST_INFO, dtoListInfo);
		model.addAttribute("form", form);

		return ViewNameConst.ISSUEMANAGEMENT_LIST;
	}

	/**
	 * 登録画面を表示します。
	 *
	 * @param model モデル
	 * @return 課題管理一覧画面テンプレート名
	 */
	@GetMapping(UrlConst.REGISTER)
	public String registerView(Model model, IssueManagementForm form ) {

		model.addAttribute("form", form);
		model.addAttribute("classificationOptions", Classification.values());

		return ViewNameConst.ISSUEMANAGEMENTREGISTER_REGISTER;
	}

	/**
	 * 登録の実行と画面の再表示。
	 *
	 * @param form 入力情報
	 * @param bdResult 入力内容の単項目チェック結果
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ISSUEMANAGEMENT_LIST, params = "register")
	public String register(@Validated IssueManagementForm form, BindingResult bdResult, RedirectAttributes redirectAttributes,
							Model model) {

		if (bdResult.hasErrors()) {
			editGuideMessage(form, bdResult, MessageConst.FORM_ERROR, redirectAttributes);
			return AppUtil.doRedirect(UrlConst.REGISTER);
		}

		//登録
		IssueManagementMessage resultMessage = service.register(mapper.map(form, IssueManagementListInfo.class));
		if (resultMessage != IssueManagementMessage.SUCCEED) {
			editGuideMessage(form, bdResult, resultMessage.getMessageId(), redirectAttributes);

			return AppUtil.doRedirect(UrlConst.ISSUEMANAGEMENT_LIST);
		}

		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, resultMessage.getMessageId()));
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
		return AppUtil.doRedirect(UrlConst.ISSUEMANAGEMENT_LIST);

	}

	/**
	 * 選択行の課題管理情報を編集して画面を再表示します。
	 *
	 * @param form 入力情報
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ISSUEMANAGEMENT_LIST, params = "edit")
	public String update(IssueManagementForm form) {
		session.setAttribute(SessionKeyConst.SELECETED_CONTROLNUMBER, form.getSelectedControlNumber());

		//課題管理編集のコントローラーへリダイレクト
		return AppUtil.doRedirect(UrlConst.ISSUEMANAGEMENT_EDIT);
	}

	/**
	 * 選択行の課題管理情報を削除して、最新情報で画面を再表示します。
	 *
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ISSUEMANAGEMENT_LIST, params="delete")
	public String deleteIssueManagement(Model model, IssueManagementForm form, RedirectAttributes redirectAttributes) {
		//選択した品番で削除実行、削除結果を取得
		IssueManagementDeleteResult executeResult = service.deleteById(form.getSelectedControlNumber());

		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, executeResult == IssueManagementDeleteResult.ERROR);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
				AppUtil.getMessage(messageSource, executeResult.getMessageId()));

		// 削除後、フォーム情報の「選択された課題管理番号」は不要になるため、クリアします。
		redirectAttributes.addFlashAttribute(KEY_ISSUEMANAGEMENT_FORM, form.clearSelectedControlNumber());

		return AppUtil.doRedirect(UrlConst.ISSUEMANAGEMENT_LIST);

	}
	/*--- private メソッド --*/
	//入力内容の単項目チェックで例外が発生した場合エラー情報をモデルに設定
	private void editGuideMessage(IssueManagementForm form, BindingResult bdResult, String messageId,
								  RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, messageId));
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, true);
		redirectAttributes.addFlashAttribute(form);
		redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + KEY_ISSUEMANAGEMENT_FORM, bdResult);
	}

}
