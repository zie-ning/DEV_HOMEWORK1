package com.example.homework1.service;

import com.example.homework1.domain.Board;
import com.example.homework1.domain.Member;
import com.example.homework1.util.NotPresentException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {
    private static ArrayList<Board> boards = new ArrayList<>();

    public Integer createBoard(Board board, Member loginMember) {
        board.setId(boards.size() + 1);
        board.setMember(loginMember);
        boards.add(board);
        return board.getId();
    }

    public static boolean checkBoard(Integer id){
        if(id>boards.size() || id<=0){
            throw new NotPresentException("존재하지 않은 게시판입니다.");
        }
        return true;
    }

    public void updateBoard(Integer id, Board board) {
        checkBoard(id);
        board.setId(id);
        boards.set(id-1,board);
    }

    public void deleteBoard(Integer id) {
        checkBoard(id);
        boards.remove(id-1);
        for(int i=id-1;i<boards.size();i++){
            boards.get(i).setId(i+1);
        }
    }

    public Board getBoard(String boardName) {
        for (Board board : boards) {
            if(board.getName().equals(boardName)){
                return board;
            }
        }
        throw new NotPresentException("존재하지 않는 게시판 이름입니다.");
    }

    public Board getBoard(Integer boardId) {
        checkBoard(boardId);
        return boards.get(boardId-1);
    }
}
