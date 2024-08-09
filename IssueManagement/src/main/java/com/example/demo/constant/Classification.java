package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分類種別
 *
 * @author ***
 */
@Getter
@AllArgsConstructor
public enum Classification {

	/* 利用可能 */
	WORK("仕事"),

	/* 利用不可 */
	PRIVATE_LIFE("私生活");

	/** 画面表示する説明文 */
	private String classification;

}
