package com.taskorganizerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CardList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String name;

    @ManyToOne
    @JoinColumn(name="board_id")
    @JsonIgnore
    public Board board;

    @OneToMany(mappedBy = "cardList", cascade = CascadeType.ALL)
    public List<Card> cards = new ArrayList<>();

    public CardList() {
    }
    public CardList(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
