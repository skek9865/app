package com.meet.app.service;

import com.meet.app.dto.BoardDTO;
import com.meet.app.entity.Board;
import com.meet.app.entity.Member;
import com.meet.app.entity.RoomInfo;

import java.util.List;

public interface BoardService {
    void register(BoardDTO boardDTO);

    List<BoardDTO> getList(RoomInfo roomInfo);

    BoardDTO getOne(Long id);

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .boardID(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .memberID(board.getMember().getId())
                .roomID(board.getRoomInfo().getId())
                .regDate(board.getRegDate())
                .build();
    }

    default Board dtoToEntity(BoardDTO boardDTO) {
        Member member = Member.builder()
                .id(boardDTO.getMemberID())
                .build();

        RoomInfo roomInfo = RoomInfo.builder()
                .id(boardDTO.getRoomID())
                .build();

        return Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .member(member)
                .roomInfo(roomInfo)
                .build();
    }
}
