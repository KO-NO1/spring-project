package com.example.demo.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ToDoInfo;
import com.example.demo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;

/**
 * 課題管理編集画面Service実装クラス
 *
 * @author kouno
 *
 */
@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

	/** 課題管理情報テーブルRepository */
	private final ToDoRepository repository;

}
