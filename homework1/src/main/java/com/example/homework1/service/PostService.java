package com.example.homework1.service;

import com.example.homework1.domain.Board;
import com.example.homework1.domain.Member;
import com.example.homework1.domain.Post;
import com.example.homework1.util.NotPresentException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PostService {
    private static ArrayList<Post> posts=new ArrayList<>();

    public Integer writePost(Board board, Post post, Member loginMember){
        post.setId(posts.size()+1);
        post.addPost(board);
        post.setMember(loginMember);
        post.setCreateDate(LocalDate.now());
        posts.add(post);
        return post.getId();
    }

    public Post getPost(Integer id) {
        checkPost(id);
        return posts.get(id - 1);
    }

    private boolean checkPost(Integer id){
        if(id>posts.size() || id<=0){
            throw new NotPresentException("존재하지 않은 게시글입니다.");
        }
        return true;
    }

    public void deletePost(Integer id){
        Post post = getPost(id);
        posts.remove(id-1);
        post.getBoard().getHavePost().remove(post); //연관관계 끊기

        for(int i=id-1; i<posts.size();i++){
            posts.get(i).setId(i+1);
        }
    }

    public void updatePost(Integer id, Post post){
        checkPost(id);
        post.setId(id);
        post.setUpdateDate(LocalDate.now());
        posts.set(id - 1, post);
    }

    public ArrayList<Post> getPosts(){
        return posts;
    }
}
