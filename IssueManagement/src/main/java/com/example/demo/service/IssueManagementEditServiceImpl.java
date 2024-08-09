package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.IssueManagementEditMessage;
import com.example.demo.constant.IssueManagementEditResult;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.entity.IssueManagementInfo;
import com.example.demo.repository.IssueManagementInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * 課題管理編集画面Service実装クラス
 *
 * @author ***
 *
 */
@Service
@RequiredArgsConstructor
public class IssueManagementEditServiceImpl implements IssueManagementEditService {

	/** Dozer Mapper */
	private final Mapper mapper;

	/** 課題管理情報テーブルRepository */
	private final IssueManagementInfoRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IssueManagementEditResult updateIssueManagementInfo(IssueManagementListInfo argDto) {
		IssueManagementEditResult editResult = new IssueManagementEditResult();

		// 現在の登録情報を取得
		Optional<IssueManagementInfo> updateInfoOpt = repository.findById(argDto.getControlNumber());
		if (updateInfoOpt.isEmpty()) {
			editResult.setUpdateMessage(IssueManagementEditMessage.FAILED);
			return editResult;
		}

		IssueManagementInfo updateInfo = updateInfoOpt.get();

		mapper.map(argDto, updateInfo);
		//更新
		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			editResult.setUpdateMessage(IssueManagementEditMessage.FAILED);
			return editResult;
		}

		editResult.setIssueManagementInfo(updateInfo);
		editResult.setUpdateMessage(IssueManagementEditMessage.SUCCEED);
		return editResult;
	}

}
