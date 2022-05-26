package com.taskorganizerapi.controller;

import com.taskorganizerapi.model.Board;
import com.taskorganizerapi.model.Card;
import com.taskorganizerapi.model.CardList;
import com.taskorganizerapi.repo.BoardRepo;
import com.taskorganizerapi.repo.CardListRepo;
import com.taskorganizerapi.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {
    @Autowired
    BoardRepo boardRepo;

    @Autowired
    CardListRepo cardListRepo;

    @Autowired
    CardRepo cardRepo;

    /* BOARD MAPPING */
    @GetMapping("/getBoards")
    public Iterable<Board> getBoards() {
        return boardRepo.findAll();
    }

    @GetMapping("/getBoard")
    public Optional<Board> getBoard(@RequestParam int id)
    {
        return boardRepo.findById(id);
    }

    @PostMapping("/createBoard")
    public Optional<Board> createBoard(@RequestBody Board board)
    {
        Board newBoard = boardRepo.save(board);
        createCardList(newBoard.id, new CardList(0, "To Do"));
        createCardList(newBoard.id, new CardList(0, "Doing"));
        createCardList(newBoard.id, new CardList(0, "Done"));
        return boardRepo.findById(newBoard.id);
    }

    @PostMapping("/updateBoard")
    public Board updateBoard(@RequestBody Board board)
    {
        return boardRepo.save(board);
    }

    @DeleteMapping("/deleteBoard")
    public void deleteBoard(@RequestParam int id)
    {
        boardRepo.deleteById(id);
    }

    /* CARD LIST MAPPING */
    @PostMapping("/createCardList")
    public CardList createCardList(@RequestParam int boardId, @RequestBody CardList cardList)
    {
        Optional<Board> board = boardRepo.findById(boardId);
        if (!board.isPresent()) {
            // throw error
            return null;
        }
        cardList.board = board.get();
        return cardListRepo.save(cardList);
    }

    @PostMapping("/updateCardList")
    public CardList updateCardList(@RequestBody CardList cardList)
    {
        Optional<CardList> oldCardList = cardListRepo.findById(cardList.id);
        if (!oldCardList.isPresent()) {
            // throw error
            return null;
        }
        cardList.board = oldCardList.get().board;
        return cardListRepo.save(cardList);
    }

    @DeleteMapping("/deleteCardList")
    public void deleteCardList(@RequestParam int id)
    {
        cardListRepo.deleteById(id);
    }

    /* CARD MAPPING */
    @PostMapping("/createCard")
    public Card createCard(@RequestParam int cardListId, @RequestBody Card card)
    {
        Optional<CardList> cardList = cardListRepo.findById(cardListId);
        if (!cardList.isPresent()) {
            // throw error
            return null;
        }
        card.cardList = cardList.get();
        return cardRepo.save(card);
    }

    @PostMapping("/updateCard")
    public Card updateCard(@RequestBody Card card)
    {
        Optional<Card> oldCard = cardRepo.findById(card.id);
        if (!oldCard.isPresent()) {
            // throw error
            return null;
        }
        card.cardList = oldCard.get().cardList;
        return cardRepo.save(card);
    }

    @DeleteMapping("/deleteCard")
    public void deleteCard(@RequestParam int id)
    {
        cardRepo.deleteById(id);
    }
}
