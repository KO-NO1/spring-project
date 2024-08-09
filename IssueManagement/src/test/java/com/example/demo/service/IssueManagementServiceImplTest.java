package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.constant.Classification;
import com.example.demo.constant.IssueManagementDeleteResult;
import com.example.demo.constant.IssueManagementMessage;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.entity.IssueManagementInfo;

@SpringBootTest
class IssueManagementServiceImplTest {

	@Autowired
    private IssueManagementServiceImpl service;

    @BeforeEach
    void beforeEach() {

        //DBの初期化、テスト実行前に読み込み
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
		service.register(dto);

		service.register(dto);
		service.deleteById("00002");
    }

	@Test
	public void 編集画面の編集前の情報を取得() {

		String controlNumber = "00001";

		//期待値の設定
		IssueManagementInfo entity = new IssueManagementInfo();
		entity.setControlNumber(controlNumber);
		entity.setAccrualDate(LocalDate.of(2024, 05, 23));
		entity.setDeadline(LocalDate.of(2024, 06, 23));
		entity.setCompletionDate(null);
		entity.setWrittenBy("河野");
		entity.setImportance("高");
		entity.setSituation("対応中");
		entity.setManager("河野");
		entity.setClassification(Classification.WORK.getClassification());
		entity.setAssignmentOverview("テスト課題");
		Optional<IssueManagementInfo> entityOpt = Optional.ofNullable(entity);

		//編集画面の編集前の情報を取得
		Optional<IssueManagementInfo> resultOpt = service.searchId(controlNumber);

        assertEquals(entityOpt.get(), resultOpt.get());
	 }

	@Test
	public void 画面の初期表示の情報を取得() {

		String controlNumber = "00001";

		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber(controlNumber);
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

		List<IssueManagementListInfo> resultList = service.searchList();
		assertEquals(dtoList, resultList);
	}

	@Test
	public void 検索条件に合致する課題管理情報の取得_検索条件_課題管理番号のみ() {

		String controlNumber = "00001";

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber(controlNumber);
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

		//検索条件の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber(controlNumber);
		argsDto.setWrittenBy("");
		argsDto.setManager("");

		//検索条件に合致する課題管理情報の取得
		List<IssueManagementListInfo> resultList = service.searchByParam(argsDto);

		assertEquals(dtoList, resultList);
	}

	@Test
	public void 検索条件に合致する課題管理情報の取得_検索条件_記入者と担当者のみ() {

		String controlNumber = "00001";

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber(controlNumber);
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

		//検索条件の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber("");
		argsDto.setWrittenBy("河野");
		argsDto.setManager("河野");

		//検索条件に合致する課題管理情報の取得
		List<IssueManagementListInfo> resultList = service.searchByParam(argsDto);

		assertEquals(dtoList, resultList);
	}

	@Test
	public void 検索条件に合致する課題管理情報の取得_検索条件_課題管理番号と記入者のみ() {

		String controlNumber = "00001";

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber(controlNumber);
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

		//検索条件の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber(controlNumber);
		argsDto.setWrittenBy("河野");
		argsDto.setManager("");

		//検索条件に合致する課題管理情報の取得
		List<IssueManagementListInfo> resultList = service.searchByParam(argsDto);

		assertEquals(dtoList, resultList);
	}

	@Test
	public void 検索条件に合致する課題管理情報の取得_検索条件_課題管理番号と担当者のみ() {

		String controlNumber = "00001";

		//期待値の設定
		List<IssueManagementListInfo> dtoList = new ArrayList<>();
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber(controlNumber);
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

		//検索条件の設定
		IssueManagementListInfo argsDto = new IssueManagementListInfo();
		argsDto.setControlNumber(controlNumber);
		argsDto.setWrittenBy("");
		argsDto.setManager("河野");

		//検索条件に合致する課題管理情報の取得
		List<IssueManagementListInfo> resultList = service.searchByParam(argsDto);

		assertEquals(dtoList, resultList);
	}

	@Test
	public void 課題管理情報の登録_成功() {

		//登録値の設定
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00002");
		dto.setAccrualDate(LocalDate.of(2024, 05, 29));
		dto.setDeadline(LocalDate.of(2024, 05, 30));
		dto.setCompletionDate(LocalDate.of(2024, 05, 30));
		dto.setWrittenBy("河野");
		dto.setImportance("低");
		dto.setSituation("未着手");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題２");

		//課題管理情報の登録後のメッセージを取得
		IssueManagementMessage resulMessage = service.register(dto);

		assertEquals(IssueManagementMessage.SUCCEED, resulMessage);
	}

	@Test
	public void 課題管理情報の登録_失敗() {

		//登録値の設定　既に登録済みの管理番号を設定
		IssueManagementListInfo dto = new IssueManagementListInfo();
		dto.setControlNumber("00001");
		dto.setAccrualDate(LocalDate.of(2024, 05, 29));
		dto.setDeadline(LocalDate.of(2024, 05, 30));
		dto.setCompletionDate(LocalDate.of(2024, 05, 30));
		dto.setWrittenBy("河野");
		dto.setImportance("低");
		dto.setSituation("未着手");
		dto.setManager("河野");
		dto.setClassification(Classification.WORK.getClassification());
		dto.setAssignmentOverview("テスト課題２");

		//課題管理情報の登録後のメッセージを取得
		IssueManagementMessage resulMessage = service.register(dto);

		assertEquals(IssueManagementMessage.FAILUR_BY_ALREADY_COMPLETED, resulMessage);
	}

	@Test
	public void 課題管理情報の削除_成功() {

		//削除後のメッセージを取得
		IssueManagementDeleteResult resulMessage = service.deleteById("00001");

		assertEquals(IssueManagementDeleteResult.SUCCEED, resulMessage);
	}

	@Test
	public void 課題管理情報の削除_失敗() {

		//削除後のメッセージを取得　引数に存在しない課題管理番号を渡す
		IssueManagementDeleteResult resulMessage = service.deleteById("99999");

		assertEquals(IssueManagementDeleteResult.ERROR, resulMessage);
	}
}
