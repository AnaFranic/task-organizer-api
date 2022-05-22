package com.taskorganizerapi.controller;

import com.taskorganizerapi.model.Board;
import com.taskorganizerapi.repo.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {
    @Autowired
    BoardRepo boardRepo;

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
}
