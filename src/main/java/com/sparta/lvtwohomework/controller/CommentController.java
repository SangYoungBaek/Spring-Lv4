package com.sparta.lvtwohomework.controller;


import com.sparta.lvtwohomework.dto.CommentRequestDto;
import com.sparta.lvtwohomework.dto.CommentResponseDto;
import com.sparta.lvtwohomework.dto.StatusResponseDto;
import com.sparta.lvtwohomework.security.UserDetailsImpl;
import com.sparta.lvtwohomework.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails.getUser());
    }

    @PutMapping("/comments/{id}")
    public StatusResponseDto updateComment(@PathVariable Long id,
                                           @RequestBody CommentRequestDto requestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(id, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/comments/{id}")
    public StatusResponseDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails.getUser());
    }

}
