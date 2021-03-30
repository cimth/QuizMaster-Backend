package com.example.quizmaster_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AdminTokenService {

    /*======================================*
     * FIELD FOR TOKEN
     *======================================*/

    private final String adminToken;

    /*======================================*
     * INITIALIZATION
     *======================================*/

    /**
     * Creates a service for generating a secure random admin token which is need for creating, updating and deleting
     * questions from the database. Note that this token is generated newly by each startup of the application for
     * maximal security.
     */
    @Autowired
    public AdminTokenService() {
        this.adminToken = generateAdminToken();
    }

    /**
     * Returns a secure random admin token as string.
     * See in the given link below why to use this method and not e.g. UUIDs for securing the application.
     *
     * @return the generated admin token
     *
     * @see <a href="https://neilmadden.blog/2018/08/30/moving-away-from-uuids/">Moving away from UUIDs</a>
     */
    private String generateAdminToken() {
        SecureRandom secRandom = new SecureRandom();
        byte[] buffer = new byte[64];
        secRandom.nextBytes(buffer);
        return Base64.getEncoder().encodeToString(buffer);
    }

    /*======================================*
     * GETTER
     *======================================*/

    /**
     * @return the secure random admin token for this application run
     */
    public String getAdminToken() {
        return adminToken;
    }
}
