package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.dto.UserEditResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 *
 * @author *****
 *
 */
@Service
@RequiredArgsConstructor
public class DagnosisServiceImpl implements DagnosisService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserEditResult registerUserInfoByDiagnosticResult(String argsLoginId,int argsDiagnosticResult) {

		UserEditResult userUpdateResult = new UserEditResult();

		Optional<UserInfo> infoOpt = repository.findById(argsLoginId);
		if (infoOpt.isEmpty()) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}

		UserInfo info = infoOpt.get();
		info.setDiagnosticResult(argsDiagnosticResult);

		try {
			repository.save(info);
		} catch (Exception e) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}

		userUpdateResult.setUpdateMessage(UserEditMessage.SUCCEED);
		return userUpdateResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserInfo getDiagnosticHistory(String argsLoginId) {
		Optional<UserInfo> infoOpt = repository.findById(argsLoginId);
		UserInfo info = infoOpt.get();

		return info;
	}
}
