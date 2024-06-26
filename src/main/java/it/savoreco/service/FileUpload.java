package it.savoreco.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileUpload {
    private static final AmazonS3Client amazonS3;
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5, Thread.ofVirtual().factory());
    private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    static {
        amazonS3 = CloudFlareR2Config.getCloudFlareR2Client().orElseThrow();
    }

    public String saveImage(String path, InputStream stream) {

        try {
            var file = File.createTempFile("upload", ".png");
            ObjectMetadata meta = new ObjectMetadata();
            try  {
                Files.write(stream.readAllBytes(), file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            meta.setContentType("image/png");
            meta.setContentLength(file.length());
            PutObjectRequest putObjectRequest = new PutObjectRequest("savoreco", path, file);
            putObjectRequest.setMetadata(meta);
            amazonS3.putObject(putObjectRequest);
            executor.schedule(file::delete, 5, TimeUnit.MINUTES);
        } catch (IOException e) {
            logger.warn("Could not save image", e);

        }

        return "https://r2.savoreco.it/" + path;
    }

}
