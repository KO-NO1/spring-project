package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserEditResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.Q10Form;
import com.example.demo.form.Q20Form;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.DagnosisService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpServletRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DagnosisControllerTest {

	private MockMvc mockMvc;

	@Mock
	private DagnosisService service;

	@Autowired
	private UserInfoRepository repository;

	@Autowired
    private WebApplicationContext wac;

	@Autowired
	private HttpServletRequest request;

	@Autowired
    Validator validator;

	@Autowired
	private MessageSource messageSource;

	@BeforeEach
    void setup() {

		//イルカ型
		List<UserInfo> userInfoList = new ArrayList<>();
	    UserInfo userInfo = new UserInfo();
	    userInfo.setLoginId("testtest");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest");
		userInfo.setDiagnosticResult(1);
		userInfoList.add(userInfo);

		//診断結果なし
		userInfo = new UserInfo();
		userInfo.setLoginId("testtest2");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest2");
		userInfo.setDiagnosticResult(0);
		userInfoList.add(userInfo);

		//オオカミ型
		userInfo = new UserInfo();
		userInfo.setLoginId("testtest3");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest3");
		userInfo.setDiagnosticResult(20);
		userInfoList.add(userInfo);

		//ライオン型
		userInfo = new UserInfo();
		userInfo.setLoginId("testtest4");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest4");
		userInfo.setDiagnosticResult(33);
		userInfoList.add(userInfo);

		//クマ型
		userInfo = new UserInfo();
		userInfo.setLoginId("testtest5");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest5");
		userInfo.setDiagnosticResult(48);
		userInfoList.add(userInfo);
		
		//診断結果60より大きいエラー用データ
		userInfo = new UserInfo();
		userInfo.setLoginId("testtest6");
		userInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		userInfo.setMailAddress("a3142108@gmail.com");
		userInfo.setOneTimeCode(null);
		userInfo.setOneTimeCodeSendTime(null);
		userInfo.setLoginFailureCount(0);
		userInfo.setAccountLockedTime(null);
		userInfo.setUserStatusKind(UserStatusKind.ENABLED);
		userInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		userInfo.setSignupCompleted(true);
		userInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		userInfo.setUpdateUser("testtest5");
		userInfo.setDiagnosticResult(61);
		userInfoList.add(userInfo);

		for (int i=0;i<userInfoList.size();i++) {
			repository.save(userInfoList.get(i));
		}

		request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletWebRequest(request));

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void 診断画面の表示_正常終了() throws Exception {

		Q10Form expectedForm = new Q10Form();
		expectedForm.setQOne(false);
		expectedForm.setQTwo(false);
		expectedForm.setQThree(false);
		expectedForm.setQFour(false);
		expectedForm.setQFive(false);
		expectedForm.setQSix(false);
		expectedForm.setQSeven(false);
		expectedForm.setQEight(false);
		expectedForm.setQNine(false);
		expectedForm.setQTen(false);
		expectedForm.setDiagnosticResult(0);

		 mockMvc.perform(get("/diagnosis"))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosis"))
 	  		 .andExpect(model().attribute("q10form", expectedForm));
	}

	@Test
	public void 診断履歴の表示_診断結果1より小さい場合_エラー() throws Exception {

		UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest2");
		expectedUserInfo.setPassword("$2a$10$fXGH/qiePS2qxOWOzX0GC.GWWAU0prbs9HQYcRI5IPdh0PExcQNFq");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest2");
		expectedUserInfo.setDiagnosticResult(0);

		when(service.getDiagnosticHistory("testtest2")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest2");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false))
 	  		  	  		 .andExpect(model().attribute(ModelKey.IS_ERROR,true))
 	  		 .andExpect(model().attribute(ModelKey.MESSAGE, "診断履歴はありません。"));
	}


	@Test
	public void 診断履歴の表示_診断結果1_イルカ型の場合() throws Exception {

		UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest");
		expectedUserInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest");
		expectedUserInfo.setDiagnosticResult(1);

		when(service.getDiagnosticHistory("testtest")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("dolphin", true))
  	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false));
	}

	@Test
	public void 診断履歴の表示_診断結果20_オオカミ型の場合() throws Exception {

		UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest3");
		expectedUserInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest3");
		expectedUserInfo.setDiagnosticResult(20);

		when(service.getDiagnosticHistory("testtest3")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest3");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("wolf", true))
  	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false));
	}

	@Test
	public void 診断履歴の表示_診断結果33_ライオン型の場合() throws Exception {

		UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest4");
		expectedUserInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest4");
		expectedUserInfo.setDiagnosticResult(33);

		when(service.getDiagnosticHistory("testtest4")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest4");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("lion", true))
  	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false));
	}

	@Test
	public void 診断履歴の表示__診断結果48_クマの場合() throws Exception {

		UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest5");
		expectedUserInfo.setPassword("$2a$10$dduILQn7pcvkPbapGTxTY.0LY15qlfghQ9/xnorgT6uqsIqPbOgO2");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest5");
		expectedUserInfo.setDiagnosticResult(48);

		when(service.getDiagnosticHistory("testtest5")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest5");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("bear", true))
  	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false));
	}
	
	@Test
	public void 診断履歴の表示__診断結果60より大きい場合_エラー() throws Exception {
		
			UserInfo expectedUserInfo = new UserInfo();
		expectedUserInfo.setLoginId("testtest6");
		expectedUserInfo.setPassword("$2a$10$fXGH/qiePS2qxOWOzX0GC.GWWAU0prbs9HQYcRI5IPdh0PExcQNFq");
		expectedUserInfo.setMailAddress("a3142108@gmail.com");
		expectedUserInfo.setOneTimeCode(null);
		expectedUserInfo.setOneTimeCodeSendTime(null);
		expectedUserInfo.setLoginFailureCount(0);
		expectedUserInfo.setAccountLockedTime(null);
		expectedUserInfo.setUserStatusKind(UserStatusKind.ENABLED);
		expectedUserInfo.setAuthorityKind(AuthorityKind.DIAGNOSTIC_MANAGER);
		expectedUserInfo.setSignupCompleted(true);
		expectedUserInfo.setCreateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateTime(LocalDateTime.of(2024, 05, 23, 00, 00));
		expectedUserInfo.setUpdateUser("testtest2");
		expectedUserInfo.setDiagnosticResult(61);

		when(service.getDiagnosticHistory("testtest6")).thenReturn(expectedUserInfo);

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("loginId", "testtest6");

		mockMvc.perform(get("/diagnosticHistory")
			 .session(mockSession))
	  		 .andExpect(status().isOk())
	  		 .andExpect(view().name("diagnosticHistory"))
 	  		 .andExpect(model().attribute("managementAuthorityKind", false))
 	  		 .andExpect(model().attribute("diagnosisAuthorityKind", false))
 	  		  	  		 .andExpect(model().attribute(ModelKey.IS_ERROR,true))
 	  		 .andExpect(model().attribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource,MessageConst.NO_HISTORY )));

	}

	@Test
	public void Q10の診断画面の結果表示_ログイン無しの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", null);
            put("managementAuthorityKind", null);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

        //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 1);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

        mockMvc.perform(post("/diagnosis")
        		.param("q10", "q10")
        		.param("qOne","true")
				.param("qTwo","true")
				.param("qThree","true")
				.param("qFour","true")
				.param("qFive","true")
				.param("qSix","true")
				.param("qSeven","true")
				.param("qEight","false")
				.param("qNine","false")
				.param("qTen","false")
				.session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("diagnosisAuthorityKind", false))
                .andExpect(model().attribute("managementAuthorityKind", false))
                .andExpect(model().attribute("dolphin", true))
                .andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q10の診断画面の結果表示_ログイン済みの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", true);
            put("managementAuthorityKind", false);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

        //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 1);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

        mockMvc.perform(post("/diagnosis")
        		.param("q10", "q10")
        		.param("qOne","true")
				.param("qTwo","true")
				.param("qThree","true")
				.param("qFour","true")
				.param("qFive","true")
				.param("qSix","true")
				.param("qSeven","true")
				.param("qEight","false")
				.param("qNine","false")
				.param("qTen","false")
				.session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("diagnosisAuthorityKind", true))
                .andExpect(model().attribute("managementAuthorityKind", false))
                .andExpect(model().attribute("dolphin", true))
                .andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20の診断画面の表示_正常終了() throws Exception {

        mockMvc.perform(post("/diagnosis")
        		.param("q10", "q10")
        		.param("qOne","true")
				.param("qTwo","true")
				.param("qThree","true")
				.param("qFour","true")
				.param("qFive","true")
				.param("qSix","true")
				.param("qSeven","false")
				.param("qEight","false")
				.param("qNine","false")
				.param("qTen","false"))
                .andExpect(status().isOk())
                .andExpect(view().name("diagnosisNext"))
                .andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20診断結果画面の表示_オオカミ型_チェック20_ログイン有りの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", true);
            put("managementAuthorityKind", false);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

		 //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 20);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","1")
						.param("qEight","1")
						.param("qNine","1")
						.param("qTen","1")
						.param("qEleven","1")
						.param("qTwelve","1")
						.param("qThirteen","1")
						.param("qFourteen","1")
						.param("qFifteen","1")
						.param("qSixteen","1")
						.param("qSeventeen","1")
						.param("qEighteen","1")
						.param("qNineteen","1")
						.param("qTwenty","1")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("result"))
		                .andExpect(model().attribute("wolf", true))
		                .andExpect(model().attribute("diagnosisAuthorityKind", true))
	                	.andExpect(model().attribute("managementAuthorityKind", false))
	                	.andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20診断結果画面の表示_オオカミ型_チェックが20_ログイン無しの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", null);
            put("managementAuthorityKind", null);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

		 //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 20);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","1")
						.param("qEight","1")
						.param("qNine","1")
						.param("qTen","1")
						.param("qEleven","1")
						.param("qTwelve","1")
						.param("qThirteen","1")
						.param("qFourteen","1")
						.param("qFifteen","1")
						.param("qSixteen","1")
						.param("qSeventeen","1")
						.param("qEighteen","1")
						.param("qNineteen","1")
						.param("qTwenty","1")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("result"))
		                .andExpect(model().attribute("wolf", true))
		                .andExpect(model().attribute("diagnosisAuthorityKind", false))
	                	.andExpect(model().attribute("managementAuthorityKind", false))
	                	.andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20診断結果画面の表示_チェックが20より小さいエラー_ログイン無しの場合() throws Exception {

		Q20Form expectedForm = new Q20Form();
		expectedForm.setQOne(0);
		expectedForm.setQTwo(1);
		expectedForm.setQThree(1);
		expectedForm.setQFour(1);
		expectedForm.setQFive(1);
		expectedForm.setQSix(1);
		expectedForm.setQSeven(1);
		expectedForm.setQEight(1);
		expectedForm.setQNine(1);
		expectedForm.setQTen(1);
		expectedForm.setQEleven(1);
		expectedForm.setQTwelve(1);
		expectedForm.setQThirteen(1);
		expectedForm.setQFourteen(1);
		expectedForm.setQFifteen(1);
		expectedForm.setQSixteen(1);
		expectedForm.setQSeventeen(1);
		expectedForm.setQEighteen(1);
		expectedForm.setQNineteen(1);
		expectedForm.setQTwenty(1);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
						.param("qOne","0")
    		    		.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","1")
						.param("qEight","1")
						.param("qNine","1")
						.param("qTen","1")
						.param("qEleven","1")
						.param("qTwelve","1")
						.param("qThirteen","1")
						.param("qFourteen","1")
						.param("qFifteen","1")
						.param("qSixteen","1")
						.param("qSeventeen","1")
						.param("qEighteen","1")
						.param("qNineteen","1")
						.param("qTwenty","1"))
		                .andExpect(status().isOk())
		                .andExpect(view().name("diagnosisNext"))
		                .andExpect(model().attribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, MessageConst.UNSELECTED_ERROR)))
		                .andExpect(model().attribute(ModelKey.IS_ERROR, true))
	                	.andExpect(model().attribute("q20form", expectedForm));
	}

	//以下、格型のログインありの場合のテストはオオカミ型で実施済みの為しない。
	@Test
	public void Q20診断結果画面の表示_ライオン型_チェックが33_ログイン無しの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", null);
            put("managementAuthorityKind", null);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

		 //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 33);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","1")
						.param("qEight","1")
						.param("qNine","3")
						.param("qTen","2")
						.param("qEleven","2")
						.param("qTwelve","2")
						.param("qThirteen","2")
						.param("qFourteen","2")
						.param("qFifteen","2")
						.param("qSixteen","2")
						.param("qSeventeen","2")
						.param("qEighteen","2")
						.param("qNineteen","2")
						.param("qTwenty","2")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("result"))
		                .andExpect(model().attribute("lion", true))
		                .andExpect(model().attribute("diagnosisAuthorityKind", false))
	                	.andExpect(model().attribute("managementAuthorityKind", false))
	                	.andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20診断結果画面の表示_クマ型_チェックが48_ログイン無しの場合() throws Exception {

		//セッション情報の取得値の設定　ログイン無しの場合、権限はすべて無し
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosisAuthorityKind", null);
            put("managementAuthorityKind", null);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

		 //セッション情報のセット値の設定
        MockHttpSession session = new MockHttpSession();
		session.setAttribute("diagnosticResult", 48);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","3")
						.param("qEight","3")
						.param("qNine","3")
						.param("qTen","3")
						.param("qEleven","3")
						.param("qTwelve","3")
						.param("qThirteen","3")
						.param("qFourteen","3")
						.param("qFifteen","3")
						.param("qSixteen","3")
						.param("qSeventeen","3")
						.param("qEighteen","3")
						.param("qNineteen","3")
						.param("qTwenty","3")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("result"))
		                .andExpect(model().attribute("bear", true))
		                .andExpect(model().attribute("diagnosisAuthorityKind", false))
	                	.andExpect(model().attribute("managementAuthorityKind", false))
	                	.andExpect(model().attribute("q20form", new Q20Form()));
	}

	@Test
	public void Q20診断結果画面の表示_チェックが60より大きいエラー_ログイン無しの場合() throws Exception {

        assertThatThrownBy(() ->
		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","4")
						.param("qTwo","3")
						.param("qThree","3")
						.param("qFour","3")
						.param("qFive","3")
						.param("qSix","3")
						.param("qSeven","3")
						.param("qEight","3")
						.param("qNine","3")
						.param("qTen","3")
						.param("qEleven","3")
						.param("qTwelve","3")
						.param("qThirteen","3")
						.param("qFourteen","3")
						.param("qFifteen","3")
						.param("qSixteen","3")
						.param("qSeventeen","3")
						.param("qEighteen","3")
						.param("qNineteen","3")
						.param("qTwenty","3"))
		                ).hasCause(new Exception("err"));

	}

	@Test
	public void Q20診断結果画面の表示_チェックがない場合_値0_バリデーションエラー() throws Exception {

		//1番目がチェックなし
		Q20Form expectedForm = new Q20Form();
		expectedForm.setQOne(0);
		expectedForm.setQTwo(3);
		expectedForm.setQThree(3);
		expectedForm.setQFour(3);
		expectedForm.setQFive(3);
		expectedForm.setQSix(3);
		expectedForm.setQSeven(3);
		expectedForm.setQEight(3);
		expectedForm.setQNine(3);
		expectedForm.setQTen(3);
		expectedForm.setQEleven(3);
		expectedForm.setQTwelve(3);
		expectedForm.setQThirteen(3);
		expectedForm.setQFourteen(3);
		expectedForm.setQFifteen(3);
		expectedForm.setQSixteen(3);
		expectedForm.setQSeventeen(3);
		expectedForm.setQEighteen(3);
		expectedForm.setQNineteen(3);
		expectedForm.setQTwenty(3);

		BindingResult bdResult = new BindException(expectedForm, "q20form");
		validator.validate(expectedForm, bdResult);

		mockMvc.perform(post("/diagnosis")
						.param("q20","q20")
		        		.param("qOne","0")
						.param("qTwo","3")
						.param("qThree","3")
						.param("qFour","3")
						.param("qFive","3")
						.param("qSix","3")
						.param("qSeven","3")
						.param("qEight","3")
						.param("qNine","3")
						.param("qTen","3")
						.param("qEleven","3")
						.param("qTwelve","3")
						.param("qThirteen","3")
						.param("qFourteen","3")
						.param("qFifteen","3")
						.param("qSixteen","3")
						.param("qSeventeen","3")
						.param("qEighteen","3")
						.param("qNineteen","3")
						.param("qTwenty","3"))
		                .andExpect(status().isOk())
		                .andExpect(view().name("diagnosisNext"))
		                .andExpect(model().attribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, MessageConst.UNSELECTED_ERROR)))
		                .andExpect(model().attribute(ModelKey.IS_ERROR, true))
	                	.andExpect(model().attribute("q20form", expectedForm));
	}

	@Test
	public void 診断結果をDBへ登録_正常終了() throws Exception {

		//セッション情報の取得値の設定
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosticResult", 48);
            put("loginId", "testtest5");
            put("diagnosisAuthorityKind", false);
            put("managementAuthorityKind", true);
    
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

        UserEditResult expectedUserEditResult = new UserEditResult();
        expectedUserEditResult.setUpdateMessage(UserEditMessage.SUCCEED);

        when(service.registerUserInfoByDiagnosticResult("testtest5", 48)).thenReturn(expectedUserEditResult);

        mockMvc.perform(post("/diagnosis")
						.param("register","register")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","3")
						.param("qEight","3")
						.param("qNine","3")
						.param("qTen","3")
						.param("qEleven","3")
						.param("qTwelve","3")
						.param("qThirteen","3")
						.param("qFourteen","3")
						.param("qFifteen","3")
						.param("qSixteen","3")
						.param("qSeventeen","3")
						.param("qEighteen","3")
						.param("qNineteen","3")
						.param("qTwenty","3")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("diagnosisRegisterCompletion"))
		                .andExpect(model().attribute(ModelKey.IS_ERROR, false))
		                .andExpect(model().attribute(ModelKey.MESSAGE,
            			AppUtil.getMessage(messageSource, expectedUserEditResult.getUpdateMessage().getMessageId())));
	}
	
	@Test
	public void 診断結果をDBへ登録失敗_エラー() throws Exception {

		//セッション情報の取得値の設定
		Map<String,Object> sessionMap =  new LinkedHashMap<>() {{
            put("diagnosticResult", 48);
            put("loginId", "存在しないID");
            put("diagnosisAuthorityKind", false);
            put("managementAuthorityKind", true);
        }};
        MockHttpSession mockSession = getMockHttpSession(sessionMap);

        UserEditResult expectedUserEditResult = new UserEditResult();
        expectedUserEditResult.setUpdateMessage(UserEditMessage.FAILED);

        when(service.registerUserInfoByDiagnosticResult("存在しないID", 48)).thenReturn(expectedUserEditResult);

        mockMvc.perform(post("/diagnosis")
						.param("register","register")
		        		.param("qOne","1")
						.param("qTwo","1")
						.param("qThree","1")
						.param("qFour","1")
						.param("qFive","1")
						.param("qSix","1")
						.param("qSeven","3")
						.param("qEight","3")
						.param("qNine","3")
						.param("qTen","3")
						.param("qEleven","3")
						.param("qTwelve","3")
						.param("qThirteen","3")
						.param("qFourteen","3")
						.param("qFifteen","3")
						.param("qSixteen","3")
						.param("qSeventeen","3")
						.param("qEighteen","3")
						.param("qNineteen","3")
						.param("qTwenty","3")
						.session(mockSession))
		                .andExpect(status().isOk())
		                .andExpect(view().name("result"))
		                .andExpect(model().attribute(ModelKey.MESSAGE,
            			AppUtil.getMessage(messageSource, expectedUserEditResult.getUpdateMessage().getMessageId())));
	}

	/**
	 * mapに格納した情報をセッションに設定する
	 *
	 * @param　mapオブジェクト　
	 * @return セッションインスタンス
	 */
	private static MockHttpSession getMockHttpSession(Map<String, Object> sessions) {
        MockHttpSession mockHttpSession = new MockHttpSession();
        for (Map.Entry<String, Object> session: sessions.entrySet()) {
            mockHttpSession.setAttribute(session.getKey(), session.getValue());
        }
        return mockHttpSession;
    }
}