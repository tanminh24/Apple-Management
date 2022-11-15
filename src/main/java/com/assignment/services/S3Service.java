package com.assignment.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class S3Service {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3) {
        this.s3 = s3;
    }

    public boolean validateFileImage(File file) {
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        System.out.println(mimetype);
        if (!mimetype.startsWith("image/")) {
            return false;
        }

        long fileSizeInBytes = file.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        long fileSizeInMB = fileSizeInKB / 1024;
        if (fileSizeInMB > 20) {
            return false;
        }

        return true;
    }

    /*
     * Đẩy file lên s3
     * - Lấy tên của file truyền vào
     * - Convert file từ multiPart qua file
     * - Dùng PutObjectResult để đẩy file lên S3
     * getContentMd5() Trả về băm MD5 được mã hóa Base64 của nội dung đối tượng đã
     * được tính toán ở phía máy khách.
     */

    public String saveFile(MultipartFile multipartFile) {

        String time = String.valueOf(System.currentTimeMillis());
        String filename = time + "_" + multipartFile.getOriginalFilename();
        try {
            File file = converMultiPartToFile(multipartFile);
            if (!validateFileImage(file)) {
                return null;
            }
            s3.putObject(new PutObjectRequest(bucketName, filename, file));
            return filename;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Xóa file trên s3
     */
    public String deleteFile(String filename) {
        s3.deleteObject(bucketName, filename);
        return "File deleted";
    }

    /*
     * Trả về list file trong bucket
     */
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    /*
     * converMultiPartToFile
     */
    private File converMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    // Creating an AWS S3 Presigned URL
    public String getPresignedURL(String fileName) {
//		// Đặt URL được chỉ định trước để hết hạn sau một giờ
         java.util.Date expiration = new java.util.Date();
         long expTimeMillis = expiration.getTime();
         expTimeMillis += 1000 * 60 * 60;
         expiration.setTime(expTimeMillis);

        // Tạo link
        System.out.println("Generating pre-signed URL: ");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();

    }

}
