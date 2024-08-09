package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.IssueManagementInfo;

/**
 * 課題管理テーブルRepositoryクラス
 *
 * @author ***
 *
 */
public interface IssueManagementInfoRepository extends JpaRepository<IssueManagementInfo, String> {

	/**
	 * 課題管理番号の部分一致検索を行います。
	 *
	 * @param loginId 課題管理番号
	 * @return 検索でヒットした課題管理情報のリスト
	 */
	List<IssueManagementInfo> findByControlNumberLike(String controlNumberParam);

	/**
	 * 課題管理番号、記入者、担当者の項目を使って検索を行います。
	 *
	 * <p>■検索条件
	 * <lu>
	 * <li>課題管理番号</li>
	 * <li>記入者</li>
	 * <li>担当者</li>
	 * </lu>
	 * <p>
	 *
	 * @param controlNumberParam 課題管理番号
	 * @param writtenBy 記入者
	 * @param manager 担当者
	 * @return 検索でヒットした課題管理情報のentityリスト
	 */
	List<IssueManagementInfo> findByControlNumberLikeAndWrittenByLikeAndManagerLike(String controlNumberParam, String writtenBy,
			String manager);

	List<IssueManagementInfo> findByControlNumberLikeAndWrittenByLike(String controlNumberParam, String writtenBy);

	List<IssueManagementInfo> findByControlNumberLikeAndManagerLike(String controlNumberParam, String manager);




}