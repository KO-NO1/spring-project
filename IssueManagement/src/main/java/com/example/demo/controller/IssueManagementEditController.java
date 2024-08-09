package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.Classification;
import com.example.demo.constant.IssueManagementEditMessage;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.IssueManagementEditResult;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.entity.IssueManagementInfo;
import com.example.demo.form.IssueManagementForm;
import com.example.demo.service.IssueManagementEditService;
import com.example.demo.service.IssueManagementService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 課題管理編集画面Controllerクラス
 *
 * @author ***
 *
 */
@Controller
@RequiredArgsConstructor
public class IssueManagementEditController {

	/** Dozer Mapper */
	private final Mapper mapper;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** 課題管理サービス　**/
	private final IssueManagementService service;

	/** 課題管理編集サービス　**/
	private final IssueManagementEditService editService;

	/** パラメータ:更新 **/
	private static final String POST_PARAM_UPDATE = "update";

	/** パラメータ：エラー有 */
	private static final String PRAM_ERR = "isError";

	/**
	 * 前画面で選択された課題管理番号紐づく課題管理情報を画面に表示します。
	 *
	 * @param model モデル
	 * @param form 入力情報
	 * @return 課題管理編集画面テンプレート名
	 * @throws Exception
	 */
	@GetMapping(UrlConst.ISSUEMANAGEMENT_EDIT)
	public String view(Model model, IssueManagementForm form) throws Exception {

		var controlNumber = (String) session.getAttribute(SessionKeyConst.SELECETED_CONTROLNUMBER);
		Optional<IssueManagementInfo> entityOpt = service.searchId(controlNumber);
		if (entityOpt.isEmpty()) {
			model.addAttribute(ModelKey.MESSAGE,
					AppUtil.getMessage(messageSource, MessageConst.NON_EXISTED_ID));
			model.addAttribute(PRAM_ERR, true);

			return ViewNameConst.ISSUEMANAGEMENT_EDIT_ERROR;
		}

		IssueManagementInfo entity = entityOpt.get();
		model.addAttribute("form", mapper.map(entity, IssueManagementForm.class));
		model.addAttribute("classificationOptions", Classification.values());
		return ViewNameConst.ISSUEMANAGEMENT_EDIT;
	}

	/**
	 * 画面の入力情報をもとに課題管理情報を更新します。
	 *
	 * @param form 入力情報
	 * @param user 認証済み課題管理情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ISSUEMANAGEMENT_EDIT, params = POST_PARAM_UPDATE)
	public String update(Model model, IssueManagementForm form, @AuthenticationPrincipal User user,
			RedirectAttributes redirectAttributes) {
		var updateDto = mapper.map(form, IssueManagementListInfo.class);
		updateDto.setControlNumber((String) session.getAttribute(SessionKeyConst.SELECETED_CONTROLNUMBER));

		//更新結果取得
		IssueManagementEditResult updateResult = editService.updateIssueManagementInfo(updateDto);
		var updateMessage = updateResult.getUpdateMessage();

		//
		if (updateMessage == IssueManagementEditMessage.FAILED || updateMessage == IssueManagementEditMessage.ALREADYCOMPLETED ) {
			model.addAttribute(ModelKey.MESSAGE,
					AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			model.addAttribute(PRAM_ERR, true);
			return  ViewNameConst.ISSUEMANAGEMENT_EDIT_ERROR;
		}

		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
				AppUtil.getMessage(messageSource, updateMessage.getMessageId()));

		//リダイレクトし編集した品番から再検索した課題管理情報を表示
		return AppUtil.doRedirect(UrlConst.ISSUEMANAGEMENT_LIST);
	}
}
