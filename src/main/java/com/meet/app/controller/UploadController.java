//package com.meet.app.controller;
//
//import com.meet.app.dto.RoomFaceImageDTO;
//import com.meet.app.service.S3Uploader;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//
//@RestController
//@Slf4j
//@RequestMapping("/file")
//@RequiredArgsConstructor
//public class UploadController {
//
//    private final S3Uploader s3Uploader;
//
//    @PostMapping("/upload")
//    public HttpStatus uploadFile(@RequestBody RoomFaceImageDTO roomFaceImageDTO) throws IOException {
//        log.info("UploadController - uploadFile");
//        log.info("roomFaceImageDTO : " + roomFaceImageDTO);
//
//        s3Uploader.upload(roomFaceImageDTO, "static");
//
//        return HttpStatus.OK;
//    }
//
//    @GetMapping("/getImg/{roomID}")
//    public ResponseEntity<String> getImg(@PathVariable("roomID") Long roomID){
//        log.info("UploadController - getImg");
//        log.info("id : " + roomID);
//
//        String result = s3Uploader.getImg(roomID);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//}
