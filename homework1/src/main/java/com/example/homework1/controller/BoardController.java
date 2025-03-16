package com.example.homework1.controller;

import com.example.homework1.domain.Board;
import com.example.homework1.domain.Member;
import com.example.homework1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/add")
    public String add(@RequestBody Board board, @SessionAttribute(name="loginMember",required = false) Member loginMember) {
        Integer boardId = boardService.createBoard(board, loginMember);
        return boardId+"번 게시판이 성공적으로 생성되었습니다.";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("boardId") Integer boardId, @RequestBody Board board) {
        boardService.updateBoard(boardId, board);
        return "게시판이 성공적으로 수정되었습니다.";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("boardId") Integer boardId) {
        boardService.deleteBoard(boardId);
        return "게시판이 성공적으로 삭제되었습니다.";
    }

    @GetMapping("/view")
    public String view(@RequestParam("boardName") String boardName) {
        Board board = boardService.getBoard(boardName);
        return board.toString();
    }

}
