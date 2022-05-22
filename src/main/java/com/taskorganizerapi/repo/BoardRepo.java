package com.taskorganizerapi.repo;

import com.taskorganizerapi.model.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepo extends CrudRepository<Board, Integer> {
}
