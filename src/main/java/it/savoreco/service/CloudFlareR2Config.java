package it.savoreco.service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class CloudFlareR2Config {

    public static Optional<AmazonS3> getCloudFlareR2Client() {
        Properties amazonProps = new Properties();
        try {
            amazonProps.load(new FileInputStream("bucket.cfg.xml"));
            BasicAWSCredentials credentials = new BasicAWSCredentials(amazonProps.getProperty("accessKey"), amazonProps.getProperty("secretKey"));
            return Optional.ofNullable(AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion("")
                    .build());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
