package com.taskorganizerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String name;
    public String text;

    @ManyToOne
    @JoinColumn(name="cardList_id")
    @JsonIgnore
    public CardList cardList;

    public Card(){
    }
    public Card(int id, String name, String text){
        this.id = id;
        this.name = name;
        this.text = text;
    }
}
