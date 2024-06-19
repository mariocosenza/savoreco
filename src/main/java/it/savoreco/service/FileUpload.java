package it.savoreco.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public class FileUpload {
    private static AmazonS3Client amazonS3 = null;

    static {
        amazonS3 = CloudFlareR2Config.getCloudFlareR2Client().orElseThrow();
    }

    public String saveImage(String path, InputStream stream) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/png");
        amazonS3.putObject("savoreco", path, stream, meta);
        return amazonS3.getUrl("savoreco", path).toString();
    }

}
