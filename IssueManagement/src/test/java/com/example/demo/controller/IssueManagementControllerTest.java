package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.demo.constant.Classification;
import com.example.demo.constant.IssueManagementMessage;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.form.IssueManagementForm;
import com.example.demo.service.IssueManagementServiceImpl;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpServletRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class IssueManagementControllerTest {

	private MockMvc mockMvc;

//	@InjectMocks
//	private IssueManagementController target;

	@Mock
	private IssueManagementServiceImpl service;

	@Autowired
    private WebApplicationContext wac;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Mapper mapper;

	@BeforeEach
	public void setup() {

	  request = new MockHttpServletRequest();
	  RequestContextHolder.setRequestAttributes(new ServletWebRequest(request));

	  mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	  	//mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	//common/headerのコメントアウト 後で対応
	//<!-- <span class="text-light" th:text="|ログインID：${#authentication.principal.username}|"></span>  -->
	@Test
 	public void 初期表示_選択管理番号が入力ありの場合() throws Exception {

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

		IssueManagementForm form = new IssueManagementForm();

        when(service.searchList()).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{
            put("selectedControlNumber", 123);
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

		MockHttpSession mockSession = getMockHttpSession(sessionMap);

		mockMvc.perform(get("/IssueManagementList")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("dtoListInfo", dtoList))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
    }

	@Test
 	public void 初期表示_選択管理番号が入力無しの場合() throws Exception {

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

		IssueManagementForm form = new IssueManagementForm();

        when(service.searchList()).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{

        	//択管理番号をnullに設定
            put("selectedControlNumber", null);
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

		MockHttpSession mockSession = getMockHttpSession(sessionMap);

		mockMvc.perform(get("/IssueManagementList")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("dtoListInfo", dtoList))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
    }

	@Test
 	public void 検索_管理番号のみ() throws Exception {

		//検索値の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber("00001");

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

        when(service.searchByParam(argsDto)).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{

        	//択管理番号をnullに設定
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

    	MockHttpSession mockSession = getMockHttpSession(sessionMap);

    	//検索条件管理番号のみ
	    IssueManagementForm form = new IssueManagementForm();
        form.setControlNumber("00001");

    	//paramの引数にPostMappingのparamsの値を代入する
		mockMvc.perform(post("/IssueManagementList")
				.param("search","search")
				.param("controlNumber", "00001")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("dtoListInfo", dtoList))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
	}

	@Test
 	public void 検索_記入者のみ() throws Exception {

		//検索値の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber("00001");

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

        when(service.searchByParam(argsDto)).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{

        	//択管理番号をnullに設定
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

    	MockHttpSession mockSession = getMockHttpSession(sessionMap);

		//検索条件は記入者のみ
	    IssueManagementForm form = new IssueManagementForm();
        form.setWrittenBy("河野");

    	//paramの引数にPostMappingのparamsの値を代入する
		mockMvc.perform(post("/IssueManagementList")
				.param("search","search")
				.param("writtenBy", "河野")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("dtoListInfo", dtoList))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
	}

	@Test
 	public void 検索_担当者のみ() throws Exception {

		//検索値の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber("00001");

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

        when(service.searchByParam(argsDto)).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{

        	//択管理番号をnullに設定
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

    	MockHttpSession mockSession = getMockHttpSession(sessionMap);

    	//検索条件は担当者のみ
	    IssueManagementForm form = new IssueManagementForm();
        form.setManager("河野");

    	//paramの引数にPostMappingのparamsの値を代入する
		mockMvc.perform(post("/IssueManagementList")
				.param("search","search")
				.param("manager", "河野")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("dtoListInfo", dtoList))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
	}

	@Test
 	public void 検索_担当者と記入者() throws Exception {

		//検索値の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber("00001");

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 23));
		dto.setDeadline(LocalDate.of(2024, 06, 23));
		dto.setCompletionDate(null);
		dto.setWrittenBy("河野");
		dto.setImportance("高");
		dto.setSituation("対応中");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題");
		dtoList.add(dto);

        when(service.searchByParam(argsDto)).thenReturn(dtoList);

        Map<String,Object> sessionMap =  new LinkedHashMap<>(){{

        	//択管理番号をnullに設定
            put("mdAuthorityKind", true);
            put("mdAuthorityKindUnknown", true);
        }};

    	MockHttpSession mockSession = getMockHttpSession(sessionMap);

	    IssueManagementForm form = new IssueManagementForm();
	    form.setWrittenBy("河野");
	    form.setManager("河野");

		mockMvc.perform(post("/IssueManagementList")
				.param("search","search")
				.param("writtenBy", "河野")
				.param("manager", "河野")
				.session(mockSession))
		 		.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementList"))
                .andExpect(model().attribute("form", form))
        		.andExpect(model().attribute("dtoListInfo", dtoList))
        		.andExpect(model().attribute("mdAuthorityKind", true))
        		.andExpect(model().attribute("mdAuthorityKindUnknown", true));
	}

	@Test
 	public void 登録の画面表示() throws Exception {

		IssueManagementForm form = new IssueManagementForm();

		mockMvc.perform(get("/registerView"))
				.andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("IssueManagementRegister"))
                .andExpect(model().attribute("form", form))
                .andExpect(model().attribute("classificationOptions", Classification.values()));
	}

	@Test
 	public void 登録の実行_正常終了() throws Exception {

		//検索値の設定
		IssueManagementForm form = new IssueManagementForm();
		form.setControlNumber("00002");
		form.setAccrualDate(LocalDate.of(2024, 05, 23));
		form.setDeadline(LocalDate.of(2024, 06, 23));
		form.setCompletionDate(LocalDate.of(2024, 06, 23));
		form.setWrittenBy("河野");
		form.setImportance("中");
		form.setSituation("未着手");
		form.setManager("山田");
		form.setClassification(Classification.PRIVATE_LIFE.getClassification());
		form.setAssignmentOverview("テスト課題２");

		IssueManagementListInfo argsDto = mapper.map(form, IssueManagementListInfo.class);
		//期待値の設定
		IssueManagementMessage resultMessage = IssueManagementMessage.SUCCEED;

		when(service.register(argsDto)).thenReturn(resultMessage);


		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "00002")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "河野")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "山田")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/IssueManagementList"))
                .andExpect(status().isFound())//リダイレクト時のステータス302
                .andExpect(flash().attribute("message", AppUtil.getMessage(messageSource, resultMessage.getMessageId())))
                .andExpect(flash().attribute("isError", false));
	}

	@Test
 	public void 登録の実行_バリデーションエラー_管理番号_5文字未満() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "1234")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "河野")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "山田")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

	}

	@Test
 	public void 登録の実行_バリデーションエラー_管理番号_5文字より多い() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "123456")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "河野")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "山田")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

	}

	@Test
 	public void 登録の実行_バリデーションエラー_記入者_1文字未満() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "12345")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "山田")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

	}

	@Test
 	public void 登録の実行_バリデーションエラー_記入者_20文字より多い() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "12345")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "012345678901234567890")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "山田")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

	}

	@Test
 	public void 登録の実行_バリデーションエラー_担当者_1文字未満() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "12345")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "012345678901234567890")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

	}

	@Test
 	public void 登録の実行_バリデーションエラー_担当者_20文字より多い() throws Exception {

    	//バリデーションエラーの時は登録画面表示にリダイレクト
		mockMvc.perform(post("/IssueManagementList")
				.param("register","register")
				.param("controlNumber", "12345")
				.param("accrualDate", LocalDate.of(2024, 05, 23).toString())
				.param("deadline", LocalDate.of(2024, 06, 23).toString())
				.param("completionDate", LocalDate.of(2024, 06, 23).toString())
				.param("writtenBy", "012345678901234567890")
				.param("importance", "中")
				.param("situation", "未着手")
				.param("manager", "012345678901234567890")
				.param("classification", Classification.PRIVATE_LIFE.getClassification())
				.param("assignmentOverview", "テスト課題２"))
                .andExpect(redirectedUrl("/registerView"))
                .andExpect(status().isFound());//リダイレクト時のステータス302

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
