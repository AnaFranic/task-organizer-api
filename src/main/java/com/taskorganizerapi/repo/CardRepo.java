package com.taskorganizerapi.repo;

import com.taskorganizerapi.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<Card, Integer> {
}
