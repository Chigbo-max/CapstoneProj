package com.antiTheftTracker.antiTheftTrackerApp.services;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    public String getAccessToken(String serviceAccountKeyPath) throws IOException {
        ServiceAccountCredentials credentials = (ServiceAccountCredentials)
                GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath))
                        .createScoped(Collections.singletonList("https://www.googleapis.com/auth/androidmanagement "));
        credentials.refreshAccessToken();
        return credentials.getAccessToken().getTokenValue();
    }

    public String getAccessTokenFromBase64(String base64KeyContent) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(java.util.Base64.getDecoder().decode(base64KeyContent))) {
            ServiceAccountCredentials credentials = (ServiceAccountCredentials)
                    GoogleCredentials.fromStream(inputStream)
                            .createScoped(Collections.singletonList("https://www.googleapis.com/auth/androidmanagement "));
            credentials.refreshAccessToken();
            return credentials.getAccessToken().getTokenValue();
        }
    }
}