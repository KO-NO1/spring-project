package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.constant.IssueManagementDeleteResult;
import com.example.demo.constant.IssueManagementMessage;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.entity.IssueManagementInfo;

/**
 * 課題管理登録画面Serviceインターフェース
 *
 * @author ***
 *
 */
public interface IssueManagementService {

	/**
	 * {@inheritDoc}
	 */
	public Optional<IssueManagementInfo> searchId(String argControlNumber);

	/**
	 * {@inheritDoc}
	 */
	public List<IssueManagementListInfo> searchList();

	/**
	 * {@inheritDoc}
	 */
	public List<IssueManagementListInfo> searchByParam(IssueManagementListInfo searchDto);

	/**
	 * {@inheritDoc}
	 */
	public IssueManagementMessage register(IssueManagementListInfo searchDto);

	/**
	 * {@inheritDoc}
	 */
	public IssueManagementDeleteResult deleteById(String argsControlNumber);

}
