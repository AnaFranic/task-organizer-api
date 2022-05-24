package com.taskorganizerapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    public List<CardList> cardLists = new ArrayList<>();

    public Board() {
    }
    public Board(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
