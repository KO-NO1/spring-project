package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 重要度種別
 *
 * @author ***
 */
@Getter
@AllArgsConstructor
public enum Importance {

	HIGH("高"),

	DURING("中"),

	LOW("低");

	/** 画面表示する説明文 */
	private String importance;

}
