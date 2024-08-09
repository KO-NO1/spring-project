package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.IssueManagementDeleteResult;
import com.example.demo.constant.IssueManagementMessage;
import com.example.demo.dto.IssueManagementListInfo;
import com.example.demo.entity.IssueManagementInfo;
import com.example.demo.repository.IssueManagementInfoRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 *課題管理一覧画面Service実装クラス
 *
 * @author ***
 *
 */
@Service
@RequiredArgsConstructor
public class IssueManagementServiceImpl implements IssueManagementService {

	/** 課題管理情報テーブルRepositoryクラス */
	private final IssueManagementInfoRepository repository;


	/** Dozer Mapper */
	private final Mapper mapper;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IssueManagementListInfo> searchList() {

		return toListInfos(repository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IssueManagementListInfo> searchByParam(IssueManagementListInfo searchDto) {
		return toListInfos(findInfoByParam(searchDto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<IssueManagementInfo> searchId(String argControlNumber) {
		Optional<IssueManagementInfo> opt = repository.findById(argControlNumber);
		return opt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IssueManagementMessage register(IssueManagementListInfo dto) {
		Optional<IssueManagementInfo> entityOpt = repository.findById(dto.getControlNumber());
		if (entityOpt.isPresent()) {
			return IssueManagementMessage.FAILUR_BY_ALREADY_COMPLETED;
		}

		//登録
		repository.save(mapper.map(dto, IssueManagementInfo.class));

		return IssueManagementMessage.SUCCEED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IssueManagementDeleteResult deleteById(String argControlNumber) {

		Optional<IssueManagementInfo> entityOpt = repository.findById(argControlNumber);

		if (entityOpt.isEmpty()) {
			return IssueManagementDeleteResult.ERROR;
		}

		repository.deleteById(argControlNumber);

		return IssueManagementDeleteResult.SUCCEED;
	}

	/*--- privateメソッド ---*/
	/**
	 *課題管理情報EntityのListを課題管理一覧情報DTOのListに変換します。
	 *
	 * @param merchandiseInfos課題管理情報EntityのList
	 * @return課題管理一覧情報DTOのList
	 */
	List<IssueManagementListInfo> toListInfos(List<IssueManagementInfo> entityList) {

		List<IssueManagementListInfo> dtolist = new ArrayList<IssueManagementListInfo>();
		for (IssueManagementInfo info : entityList) {
			IssueManagementListInfo dto = mapper.map(info, IssueManagementListInfo.class);
			dtolist.add(dto);
		}

		return dtolist;
	}

	/**
	 * 課題管理一覧情報の条件検索を行い、検索結果を返却します。
	 *
	 * @param searchDto 入力情報
	 * @return 検索結果
	 */
	private List<IssueManagementInfo> findInfoByParam(IssueManagementListInfo searchDto) {
		var controlNumberParam = AppUtil.addWildcard(searchDto.getControlNumber());
		var writtenByParam = AppUtil.addWildcard(searchDto.getWrittenBy());
		var managerParam = AppUtil.addWildcard(searchDto.getManager());
		
		if (!writtenByParam.equals("%%") && !managerParam.equals("%%")) {
			return repository.findByControlNumberLikeAndWrittenByLikeAndManagerLike(controlNumberParam,
					writtenByParam, managerParam);
		} else if (!writtenByParam.equals("%%")) {
			return repository.findByControlNumberLikeAndWrittenByLike(controlNumberParam, writtenByParam);
		} else if (!managerParam.equals("%%")) {
			return repository.findByControlNumberLikeAndManagerLike(controlNumberParam, managerParam);
		} else {
			return repository.findByControlNumberLike(controlNumberParam);
		}
	}
}
