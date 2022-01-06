//package com.meet.app.controller;
//
//import com.meet.app.dto.RoomFaceImageDTO;
//import com.meet.app.service.S3Uploader;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//
//@RestController
//@Log4j2
//@RequestMapping("/file")
//@RequiredArgsConstructor
//public class UploadController {
//
//    private final S3Uploader s3Uploader;
//
//    @PostMapping("/upload")
//    public HttpStatus uploadFile(@RequestBody RoomFaceImageDTO roomFaceImageDTO) throws IOException {
//
//        s3Uploader.upload(roomFaceImageDTO, "static");
//
//        return HttpStatus.OK;
//    }
//
//    @GetMapping("/getImg/{roomID}")
//    public ResponseEntity<String> getImg(@PathVariable("roomID") Long roomID){
//
//        String result = s3Uploader.getImg(roomID);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//}
