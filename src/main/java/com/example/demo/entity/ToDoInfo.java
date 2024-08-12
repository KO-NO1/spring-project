package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 課題管理情報テーブルEntityクラス
 *
 * @author kouno
 *
 */
@Entity
@Table(name = "ToDo")
@Data
@NoArgsConstructor
public class ToDoInfo {


	/** 番号 */
	@Id
	@Column(name = "idx")
	private	 int	idx;

	/** タイトル */
	@Column(name = "title")
	private String title;

	/** 説明 */
	@Column(name = "discription")
	private String discription;

	/** 状態 */
	@Column(name = "status")
	private boolean status;

	/** 発生日 */
	@Column(name = "accrual_date")
	private	 LocalDate	accrualDate;

}