package com.example.demo.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログイン画面Formクラス
 *
 * @author *****
 *
 */
@Data
@NoArgsConstructor
public class Q10Form {

	/** 問１ */
	private boolean qOne;

	/** 問２ */
	private boolean qTwo;

	/** 問３ */
	private boolean qThree;

	/** 問４ */
	private boolean qFour;

	/** 問５ */
	private boolean qFive;

	/** 問６ */
	private boolean qSix;

	/** 問７ */
	private boolean qSeven;

	/** 問８ */
	private boolean qEight;

	/** 問９ */
	private boolean qNine;

	/** 問１０ */
	private boolean qTen;
	
	/** 診断結果 */
	private int diagnosticResult;
}
