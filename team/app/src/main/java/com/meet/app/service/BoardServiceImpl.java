package com.meet.app.service;

import com.meet.app.dto.BoardDTO;
import com.meet.app.entity.Board;
import com.meet.app.entity.Member;
import com.meet.app.entity.MemberInRoom;
import com.meet.app.entity.RoomInfo;
import com.meet.app.repository.BoardRepository;
import com.meet.app.repository.MemberInRoomRepository;
import com.meet.app.repository.MemberRepository;
import com.meet.app.repository.RoomInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final MemberInRoomRepository memberInRoomRepository;
    private final RoomInfoRepository roomInfoRepository;

    @Override
    public void register(BoardDTO boardDTO) {

        // 방장이 맞는지 확인
        Member findMember = memberRepository.findById(boardDTO.getMemberID()).get();

        String masterID = memberInRoomRepository.getMemberID(boardDTO.getRoomID()).get();

        if (!findMember.getId().equals(masterID)) throw new IllegalArgumentException("방장이 아닙니다");

        RoomInfo roomInfo = roomInfoRepository.findById(boardDTO.getRoomID()).get();

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getTitle())
                .member(findMember)
                .roomInfo(roomInfo)
                .build();

        log.info("register board" + boardDTO);

        boardRepository.save(board);
    }

    @Override
    public List<BoardDTO> getBoardList(RoomInfo roomInfo) {
        List<Board> boardList = boardRepository.getList(roomInfo.getId());

        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boardList) {
            boardDTOList.add(entityToDTO(board));
        }

        return boardDTOList;
    }
}
