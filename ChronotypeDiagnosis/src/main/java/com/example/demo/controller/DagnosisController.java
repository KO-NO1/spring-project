package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.UserEditResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.Q10Form;
import com.example.demo.form.Q20Form;
import com.example.demo.service.DagnosisService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 画面Controllerクラス
 *
 * @author *****
 *
 */
@Controller
@RequiredArgsConstructor
public class DagnosisController {

	private static final String DOLPHIN = "dolphin";

	private static final String WOLF = "wolf";

	private static final String LION = "lion";

	private static final String BEAR = "bear";

	private static final String FORM = "q20form";

	/** メッセージソース */
	private final MessageSource messageSource;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** ユーザー情報Serviceクラス */
	private final DagnosisService service;

	/**
	 * 診断画面の表示を行います。
	 *
	 * @param model モデル
	 * @return ログイン画面テンプレート名
	 */
	@GetMapping(UrlConst.DIAGNOSIS)
	public String view(Model model,Q10Form form) {

		model.addAttribute("q10form" ,form);
		return ViewNameConst.DIAGNOSIS;
	}

	/**
	 * 診断履歴の表示を行います。
	 *
	 * @param model モデル
	 * @return ログイン画面テンプレート名
	 */
	@GetMapping("/diagnosticHistory")
	public String diagnosticResult(Model model) throws Exception {

		String loginId = (String)session.getAttribute("loginId");
		UserInfo info = service.getDiagnosticHistory(loginId);

		int diagnosticResult = info.getDiagnosticResult();

		if (1 <= diagnosticResult && diagnosticResult <= 19) {
			model.addAttribute(DOLPHIN, true);
		} else if (20 <= diagnosticResult && diagnosticResult <= 32) {
			model.addAttribute(WOLF, true);
		} else if (33 <= diagnosticResult && diagnosticResult <= 47) {
			model.addAttribute(LION, true);
		} else if (48 <= diagnosticResult && diagnosticResult <= 60) {
			model.addAttribute(BEAR, true);
		} else {
			model.addAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource,MessageConst.NO_HISTORY ));
			model.addAttribute(ModelKey.IS_ERROR, true);
			model.addAttribute("diagnosisAuthorityKind", false);
			model.addAttribute("managementAuthorityKind", false);
			return ViewNameConst.DIAGNOSTIC_HISTORY;
		}

		model.addAttribute("diagnosisAuthorityKind", false);
		model.addAttribute("managementAuthorityKind", false);

		return ViewNameConst.DIAGNOSTIC_HISTORY;
	}

	/**
	 * Q10の診断画面の結果表示とQ20の診断がある場合に画面の表示
	 *
	 * @param model モデル
	 * @param form Q10Form
	 * @return ログイン画面テンプレート名
	 */
	@PostMapping(value = UrlConst.DIAGNOSIS, params = "q10")
	public String q10Diagnosis(Model model, Q10Form form) {

		List<Boolean> list = new ArrayList<>();
		list.add(form.isQOne());
		list.add(form.isQTwo());
		list.add(form.isQThree());
		list.add(form.isQFour());
		list.add(form.isQFive());
		list.add(form.isQSix());
		list.add(form.isQSeven());
		list.add(form.isQEight());
		list.add(form.isQNine());
		list.add(form.isQTen());

		int cnt = 0;
		for (boolean b : list) {
			if (b) {
				cnt++;
			}
		}

		if (cnt >= 7) {

			var diagnosisAuthorityKind = session.getAttribute("diagnosisAuthorityKind");
			var managementAuthorityKind = session.getAttribute("managementAuthorityKind");
			session.setAttribute("diagnosticResult", 1);

			if (diagnosisAuthorityKind == null) {
			diagnosisAuthorityKind = false;
			}
			if (managementAuthorityKind == null) {
				managementAuthorityKind = false;
			}
			
			//診断結果登録に失敗した場合に必要な権限情報を設定
			session.setAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
			session.setAttribute("managementAuthorityKind", managementAuthorityKind);

			model.addAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
			model.addAttribute("managementAuthorityKind", managementAuthorityKind);
			model.addAttribute(DOLPHIN, true);
			model.addAttribute(FORM ,new Q20Form());

			return ViewNameConst.RESULT;
		}

		model.addAttribute(FORM ,new Q20Form());

		return ViewNameConst.DIAGNOSIS_NEXT;
	}

	/**
	 * Q20の診断結果画面の表示
	 *
	 * @param model モデル
	 * @param form Q20Form
	 * @param bdResult 入力内容の単項目チェック結果
	 * @return ログイン画面テンプレート名
	 */
	@PostMapping(value = UrlConst.DIAGNOSIS, params = "q20")
	public String q20Diagnosis(@Validated Q20Form form, BindingResult bdResult, Model model) throws Exception{

		List<Integer> list = new ArrayList<>();
		list.add(form.getQOne());
		list.add(form.getQTwo());
		list.add(form.getQThree());
		list.add(form.getQFour());
		list.add(form.getQFive());
		list.add(form.getQSix());
		list.add(form.getQSeven());
		list.add(form.getQEight());
		list.add(form.getQNine());
		list.add(form.getQTen());
		list.add(form.getQEleven());
		list.add(form.getQTwelve());
		list.add(form.getQThirteen());
		list.add(form.getQFourteen());
		list.add(form.getQFifteen());
		list.add(form.getQSixteen());
		list.add(form.getQSeventeen());
		list.add(form.getQEighteen());
		list.add(form.getQNineteen());
		list.add(form.getQTwenty());

		int sum = 0;
		for (int n : list) {
			sum += n;
		}

		if (20 <= sum && sum <= 32) {
			model.addAttribute(WOLF, true);
			session.setAttribute("diagnosticResult", 20);
		} else if (33 <= sum && sum <= 47) {
			model.addAttribute(LION, true);
			session.setAttribute("diagnosticResult", 33);
		} else if (48 <= sum && sum <= 60) {
			model.addAttribute(BEAR, true);
			session.setAttribute("diagnosticResult", 48);
		} else if (60 < sum) {
			throw new Exception("err");
		}

		if (bdResult.hasErrors()) {
			model.addAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, MessageConst.UNSELECTED_ERROR));
			model.addAttribute(ModelKey.IS_ERROR, true);
			model.addAttribute(FORM, form);

			return ViewNameConst.DIAGNOSIS_NEXT;
		}

		//trueの場合、診断結果の登録を可能にする。
		var diagnosisAuthorityKind = session.getAttribute("diagnosisAuthorityKind");
		var managementAuthorityKind = session.getAttribute("managementAuthorityKind");

		if (diagnosisAuthorityKind == null) {
			diagnosisAuthorityKind = false;
		}
		if (managementAuthorityKind == null) {
			managementAuthorityKind = false;
		}

		//診断結果登録に失敗した場合に必要な権限情報を設定
		session.setAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
		session.setAttribute("managementAuthorityKind", managementAuthorityKind);
		
		model.addAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
		model.addAttribute("managementAuthorityKind", managementAuthorityKind);
		model.addAttribute(FORM, new Q20Form());

		return ViewNameConst.RESULT;
	}

	/**
	 * 診断結果をDBへ登録する。
	 *
	 * @param model モデル
	 * @param form Q20Form
	 * @param bdResult 入力内容の単項目チェッz8ク結果
	 * @return ログイン画面テンプレート名
	 */
	@PostMapping(value = UrlConst.DIAGNOSIS, params = "register")
	public String register(Model model, Q20Form form) {

		int result  = (int) session.getAttribute("diagnosticResult");
		String loginId = (String)session.getAttribute("loginId");

		UserEditResult updateResult = service.registerUserInfoByDiagnosticResult(loginId, result);
		UserEditMessage updateMessage = updateResult.getUpdateMessage();
		if (updateMessage == UserEditMessage.FAILED) {
			model.addAttribute(ModelKey.MESSAGE,
					AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
			model.addAttribute(ModelKey.IS_ERROR, true);
			
			var diagnosisAuthorityKind = session.getAttribute("diagnosisAuthorityKind");
			var managementAuthorityKind = session.getAttribute("managementAuthorityKind");
			
			model.addAttribute("diagnosisAuthorityKind", diagnosisAuthorityKind);
			model.addAttribute("managementAuthorityKind", managementAuthorityKind);


			return ViewNameConst.RESULT;
		}

		model.addAttribute(ModelKey.IS_ERROR, false);
		model.addAttribute(ModelKey.MESSAGE,
				AppUtil.getMessage(messageSource, updateMessage.getMessageId()));


		return ViewNameConst.DIAGNOSIS_REGISTER_COMPLETION;
	}
}
