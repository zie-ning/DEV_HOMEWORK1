package com.example.homework1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@NoArgsConstructor
public class Board {
    private Integer id;
    private String name;
    private Member member;
    private ArrayList<Post> havePost=new ArrayList<>();

    public Board(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(havePost.isEmpty()) return "게시판에 게시글이 존재하지 않습니다.";
        for (Post post : havePost) {
            String memberName = post.getMember() == null ? "비회원" : member.getName();
            sb.append("글 번호: "+post.getId()+"/"+"글 제목: "+post.getTitle()+"/"+"작성일: "+post.getCreateDate()+"/작성자: "+memberName+ "\n");
        }
        return sb.toString();
    }
}
