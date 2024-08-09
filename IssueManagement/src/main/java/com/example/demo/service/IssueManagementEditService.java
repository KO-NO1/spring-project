package com.example.demo.service;

import com.example.demo.constant.IssueManagementEditResult;
import com.example.demo.dto.IssueManagementListInfo;


/**
 * 課題管理編集画面Serviceインターフェース
 *
 * @author ***
 *
 */
public interface IssueManagementEditService {

	/**
	 * 課題管理情報テーブルを更新します。
	 *
	 * @param argDto 課題管理DTO
	 * @return 更新結果
	 */
	public IssueManagementEditResult updateIssueManagementInfo(IssueManagementListInfo argDto);

}