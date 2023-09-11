package com.sparta.lvtwohomework.controller;

import com.sparta.lvtwohomework.dto.BoardCommentResponseDto;
import com.sparta.lvtwohomework.dto.BoardRequestDto;
import com.sparta.lvtwohomework.dto.BoardResponseDto;
import com.sparta.lvtwohomework.dto.StatusResponseDto;
import com.sparta.lvtwohomework.security.UserDetailsImpl;
import com.sparta.lvtwohomework.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    //게시물 작성 API
    @PostMapping("/boards")
    public BoardResponseDto createBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return boardService.createBoard(requestDto, userDetails.getUser());
    }

    //전체 게시글 목록 조회하기 API
    @GetMapping("/boards")
    public List<BoardCommentResponseDto> getBoard() {
        return boardService.getBoard();
    }

    //선택한 게시글 조회 API
    @GetMapping("/boards/{id}")
    public BoardCommentResponseDto selectGetBoard(@PathVariable Long id) {
        return boardService.selectGetBoard(id);
    }

    //선택한 게시글 수정 API
    @PutMapping("/boards/{id}")
    public StatusResponseDto updateBoard(@PathVariable Long id,
                                         @RequestBody BoardRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, requestDto, userDetails.getUser());
    }

    //선택한 게시글 삭제 API
    @DeleteMapping("/boards/{id}")
    public StatusResponseDto deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(id, userDetails.getUser());
    }
}
