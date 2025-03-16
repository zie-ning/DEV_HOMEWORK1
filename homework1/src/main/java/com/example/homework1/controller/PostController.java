package com.example.homework1.controller;

import com.example.homework1.domain.Board;
import com.example.homework1.domain.Member;
import com.example.homework1.service.BoardService;
import com.example.homework1.service.PostService;
import com.example.homework1.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

    @PostMapping("/add")
    public String addPost(@RequestParam("boardId") Integer boardId, @RequestBody Post post
                        , @SessionAttribute(name="loginMember",required = false) Member loginMember
    ) {
        Board board = boardService.getBoard(boardId);
        Integer postId = postService.writePost(board, post, loginMember);
        return postId+"번 게시글이 성공적으로 작성되었습니다.";
    }

    @GetMapping("/remove")
    public String removePost(@RequestParam("postId") Integer postId) {
        postService.deletePost(postId);
        return "게시글이 성공적으로 삭제되었습니다.";
    }

    @PostMapping("/edit")
    public String editPost(@RequestParam("postId") Integer postId, @RequestBody Post post) {
        postService.updatePost(postId,post);
        return "게시글이 성공적으로 수정되었습니다.";
    }

    @GetMapping("/view")
    public String viewPost(@RequestParam("postId") Integer postId) {
        Post post = postService.getPost(postId);
        return post.toString();
    }
}
