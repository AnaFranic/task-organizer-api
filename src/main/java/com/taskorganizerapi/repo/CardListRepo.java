package com.taskorganizerapi.repo;

import com.taskorganizerapi.model.CardList;
import org.springframework.data.repository.CrudRepository;

public interface CardListRepo extends CrudRepository<CardList, Integer>{
}
