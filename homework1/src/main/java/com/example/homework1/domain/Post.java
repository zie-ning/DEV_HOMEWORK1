package com.example.homework1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class Post {
    private Integer id;
    private String title;
    private String content;
    private Board board;
    private Member member;

    private LocalDate createDate;
    private LocalDate updateDate;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createDate =LocalDate.now();
    }

    //양방향 연관관계 메서드
    public void addPost(Board board){
        this.board = board;
        board.getHavePost().add(this);
    }

    @Override
    public String toString() {
        String memberName = member == null ? "비회원" : member.getName();

        return id+"번 게시글"
                +"\n작성일: "+createDate
                +"\n수정일: "+updateDate
                +"\n제목: "+title+"\n내용: "+content
                +"\n작성자: "+memberName+"\n";
    }
}
