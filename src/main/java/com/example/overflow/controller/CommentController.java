package com.example.overflow.controller;

import com.example.overflow.dto.Response;
import com.example.overflow.dto.request.CommentDto;
import com.example.overflow.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * comment 생성하기
     */
    @PostMapping
    public void create(@RequestBody CommentDto dto) {
        commentService.create(dto.getContent());
    }

    /**
     * comment 수정하기
     */
    @PatchMapping("/{comment_id}")
    public Response<Void> update(@PathVariable Integer comment_id, @RequestBody CommentDto dto) {
        commentService.update(comment_id, dto.getContent());
        return Response.success();
    }

    /**
     * comment 삭제하기
     */
    @DeleteMapping("{comment_id}")
    public Response<Void> delete(@PathVariable Integer comment_id, @RequestBody CommentDto dto) {
        commentService.delete(comment_id, dto.getContent());
        return Response.success();
    }

    /**
     * comment 가져오기
     */
    @GetMapping
    public Response<Void> getComment() {
//        commentService.find();
        return Response.success();
    }

}