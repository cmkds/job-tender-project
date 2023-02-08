package com.springboot.pjt1.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.springboot.pjt1.data.entity.UploadFile;
import com.springboot.pjt1.data.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private final UploadFileRepository uploadFileRepository;
    private String bucket = "team-a502-bucket";

    @Transactional
    public void saveUploadFile(MultipartFile multipartFile) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);

        //String storeFileName = UUID.randomUUID() + "." + ext;
        String storeFileName = originalFilename;
        String key = "test/" + storeFileName;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }

        String storeFileUrl = amazonS3Client.getUrl(bucket, key).toString();
        UploadFile uploadFile = new UploadFile(originalFilename, storeFileUrl);
        uploadFileRepository.save(uploadFile);

        System.out.println(storeFileUrl);
        System.out.println(uploadFile.toString());
    }

}
