package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserEditResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@SpringBootTest
class DagnosisServiceImplTest {


	@Autowired
    private DagnosisServiceImpl service;

	@Autowired
	private UserInfoRepository repository;

   @BeforeEach
    void beforeEach() {
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

		repository.save(expectedUserInfo);
    }


    @Test
	public void 診断履歴画面表示_正常終了() {

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

    	UserInfo actualUserInfo = service.getDiagnosticHistory("testtest");

    	assertEquals(expectedUserInfo ,actualUserInfo);
	 }

    @Test
	public void 診断結果登録_正常終了() {

    	//登録完了後のUserEditResultの期待値
    	UserEditResult expectedUserEditResult = new UserEditResult();
    	expectedUserEditResult.setUpdateMessage(UserEditMessage.SUCCEED);

    	UserEditResult actualUserEditResult = service.registerUserInfoByDiagnosticResult("testtest", 1);

    	assertEquals(expectedUserEditResult ,actualUserEditResult);
	 }

    @Test
	public void 診断結果登録_idが存在しない場合_エラー() {

    	//登録完了後のUserEditResultの期待値
    	UserEditResult expectedUserEditResult = new UserEditResult();
    	expectedUserEditResult.setUpdateMessage(UserEditMessage.FAILED);

    	UserEditResult actualUserEditResult = service.registerUserInfoByDiagnosticResult("存在しないId", 1);

    	assertEquals(expectedUserEditResult ,actualUserEditResult);
	 }

    //DBに登録する値がnull以外で例外を発生させることができない為、テストしない。
    @Disabled
	public void 診断結果登録_DBエラー() {
	 }
}
