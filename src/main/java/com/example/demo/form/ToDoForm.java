package com.example.demo.form;

import java.time.LocalDate;

import lombok.Data;

/**
 * 課題管理登録画面Formクラス
 *
 * @author kouno
 *
 */
@Data
public class ToDoForm {

  /** 番号 */
	private int idx;

	/** タイトル */
	private String title;

	/** 説明 */
	private String discription;

	/** 状態 */
	private String status;

	/** 発生日 */
	private LocalDate accrualDate;
}
