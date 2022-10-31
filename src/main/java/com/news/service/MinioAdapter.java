//package com.news.service;
//
//import io.minio.GetPresignedObjectUrlArgs;
//import io.minio.messages.Bucket;
//import io.minio.MinioClient;
//import io.minio.messages.Bucket;
//import lombok.var;
//import org.apache.commons.io.IOUtils;
////import org.apache.http.auth.params.AuthPNames;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.tinify.Options;
//import com.tinify.Source;
//import com.tinify.Tinify;
//
//import io.minio.PutObjectArgs;
//import io.minio.http.Method;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.io.FilenameUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.UUID;
//import javax.annotation.PostConstruct;
//import java.io.*;
//import java.util.List;
//
//
//@Service
//public class MinioAdapter {
//    @Autowired
//    MinioClient minioClient;
//
//    @Value("${minio.buckek.name}")
//    String defaultBucketName;
//
//    @Value("${minio.default.folder}")
//    String defaultBaseFolder;
//
//    @Value("${minio.bucket.name}")
//    private String bucketName;
//    @Value("${tinify.api.key}")
//    private String apiKey;
//
//    private final Logger LOGGER = LoggerFactory.getLogger(MinioAdapter.class);
//
//    public String objectUpload(MultipartFile file) {
//            String uuidName = UUID.randomUUID().toString();
//            String objectName = uuidName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
//            try {
//                var imageType = objectName.substring(objectName.lastIndexOf(".") + 1);
//                BufferedImage originalObject = ImageIO.read(file.getInputStream());
//                var width = Math.min(originalObject.getWidth(), 1920);
//                byte[] bytes;
//                if ("png".equalsIgnoreCase(imageType) && originalObject.getWidth() > 1920) {
//                    Tinify.setKey(apiKey);
//                    Source source = Tinify.fromBuffer(file.getBytes());
//                    Options options = new Options()
//                            .with("method", "scale")
//                            .with("width", width);
//                    Source resized = source.resize(options);
//                    bytes = resized.toBuffer();
//                } else {
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    Thumbnails.of(originalObject)
//                            .width(width)
//                            .keepAspectRatio(true)
//                            .outputFormat(imageType)
//                            .toOutputStream(outputStream);
//                    bytes = outputStream.toByteArray();
//                }
//                InputStream inputStream = new ByteArrayInputStream(bytes);
//                minioClient.putObject(PutObjectArgs.builder()
//                        .stream(new BufferedInputStream(inputStream), bytes.length, -1)
//                        .bucket(bucketName)
//                        .object(objectName)
//                        .contentType("image/jpeg")
//                        .build());
//                return objectName;
//
//            } catch (Exception e) {
//                LOGGER.error("Lỗi trong khi tải ảnh lên Minio: {}", e.getMessage(), e);
//                throw new BadRequestException("Lỗi trong khi tải ảnh lên Minio");
//            }
//        }
//
//        public String getObject(String filename) {
//            try {
//                return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
//                        .bucket(bucketName)
//                        .object(filename)
//                        .method(Method.GET)
//                        .build());
//            } catch (Exception ex) {
//                LOGGER.error("Lỗi trong khi lấy ảnh: {}", filename, ex);
//                throw new BadRequestException("Lỗi trong khi lấy ảnh");
//            }
//        }
//    }
//
