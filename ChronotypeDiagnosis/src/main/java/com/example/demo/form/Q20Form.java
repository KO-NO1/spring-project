package com.example.demo.form;

import jakarta.validation.constraints.Positive;
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
public class Q20Form {

	/** 問１ */
	@Positive
	private int qOne;

	/** 問２ */
	@Positive
	private int qTwo;

	/** 問３ */
	@Positive
	private int qThree;

	/** 問４ */
	@Positive
	private int qFour;

	/** 問５ */
	@Positive
	private int qFive;

	/** 問６ */
	@Positive
	private int qSix;

	/** 問７ */
	@Positive
	private int qSeven;

	/** 問８ */
	@Positive
	private int qEight;

	/** 問９ */
	@Positive
	private int qNine;

	/** 問１０ */
	@Positive
	private int qTen;

	/** 問１１ */
	@Positive
	private int qEleven;

	/** 問１２ */
	@Positive
	private int qTwelve;

	/** 問１３ */
	@Positive
	private int qThirteen;

	/** 問１４ */
	@Positive
	private int qFourteen;

	/** 問１５ */
	@Positive
	private int qFifteen;

	/** 問１６ */
	@Positive
	private int qSixteen;

	/** 問１７ */
	@Positive
	private int qSeventeen;

	/** 問１８ */
	@Positive
	private int qEighteen;

	/** 問１９ */
	@Positive
	private int qNineteen;

	/** 問２０ */
	@Positive
	private int qTwenty;

	/** 診断結果数値 */
	private int diagnosticResult;


}
