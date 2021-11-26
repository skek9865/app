package com.meet.app.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.meet.app.dto.RoomFaceImageDTO;
import com.meet.app.entity.RoomFaceImage;
import com.meet.app.entity.RoomInfo;
import com.meet.app.repository.RoomFaceImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

// 파일 업로드 기능(S3 사용)

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    private final RoomFaceImageRepository roomFaceImageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void upload(RoomFaceImageDTO roomFaceImageDTO, String dirName) throws IOException {

        String uuid = UUID.randomUUID().toString();

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(roomFaceImageDTO.getFileBase64().getBytes());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(decodedBytes);
        byteArrayOutputStream.flush();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        MultipartFile file = new MockMultipartFile(uuid,byteArrayInputStream.readAllBytes());

        File uploadFile = convert(file)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        upload(roomFaceImageDTO, uploadFile, dirName);
    }

    private void upload(RoomFaceImageDTO roomFaceImageDTO, File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        putS3(roomFaceImageDTO, uploadFile, fileName);
        removeNewFile(uploadFile);
    }

    private void putS3(RoomFaceImageDTO roomFaceImageDTO, File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));

        RoomInfo roomInfo = RoomInfo.builder().id(roomFaceImageDTO.getRoomID()).build();

        RoomFaceImage roomFaceImage = RoomFaceImage.builder().path(amazonS3Client.getUrl(bucket, fileName).toString()).roomInfo(roomInfo).build();

        roomFaceImageRepository.save(roomFaceImage);

    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {

        String uuid = UUID.randomUUID().toString();

        File convertFile = new File(uuid + ".jpg");
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    public String getImg(Long roomID){

        RoomFaceImage image = roomFaceImageRepository.getImage(roomID);

        return image.getPath();
    }
}
