package it.savoreco.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.Objects;
import java.util.Optional;


public class CloudFlareR2Config {

    public static Optional<AmazonS3Client> getCloudFlareR2Client() {
        ConfigurationProvider configurationProvider = new ConfigurationProvider();
        BasicAWSCredentials credentials = new BasicAWSCredentials(Objects.requireNonNull(configurationProvider.getProperties("accessKey")),
                Objects.requireNonNull(configurationProvider.getProperties("secretKey")));
        return Optional.ofNullable((AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("")
                .build());

    }

}
