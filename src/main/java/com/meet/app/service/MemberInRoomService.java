package com.meet.app.service;

public interface MemberInRoomService {

    Boolean inRoom(Long roomID, String memberID);

    Boolean outRoom(Long roomID, String memberID);
}
