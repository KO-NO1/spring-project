package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ToDoInfo;

/**
 * todoテーブルRepositoryクラス
 *
 * @author kouno
 *
 */
public interface ToDoRepository extends JpaRepository<ToDoInfo, Integer> {

	/**
	 * 日付の一致検索を行います。
	 *
	 * @param accrualDate 発生日
	 * @return 検索でヒットしたtodoリスト
	 */
	List<ToDoInfo> findByAccrualDate(LocalDate accrualDate);

	List<ToDoInfo> findByDiscriptionLike(LocalDate accrualDate);

	List<ToDoInfo> findByTitleLike(String title);

	List<ToDoInfo> findByStatusTrue(boolean status);

	List<ToDoInfo> findByStatusFalse(boolean status);

}