package com.taskorganizerapi.controller;

import com.taskorganizerapi.model.Board;
import com.taskorganizerapi.model.CardList;
import com.taskorganizerapi.repo.BoardRepo;
import com.taskorganizerapi.repo.CardListRepo;
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
    public Board createBoard(@RequestBody Board board)
    {
        return boardRepo.save(board);
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
    @GetMapping("/getCardList")
    public Optional<CardList> getCardList(@RequestParam int id)
    {
        return cardListRepo.findById(id);
    }

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
}
