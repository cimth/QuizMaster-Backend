package com.example.quizmaster_backend;

import com.example.quizmaster_backend.service.AdminTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartup implements CommandLineRunner {

    /*======================================*
     * FIELDS
     *======================================*/

    private final Logger logger = LogManager.getLogger(CommandLineStartup.class);
    private final AdminTokenService adminTokenService;

    /*======================================*
     * CONSTRUCTOR
     *======================================*/

    @Autowired
    public CommandLineStartup(AdminTokenService adminTokenService) {
        this.adminTokenService = adminTokenService;
    }

    /*======================================*
     * METHOD TO RUN AFTER STARTUP OF THE APPLICATION
     *======================================*/

    /**
     * Print admin token on console after startup.
     * The token is needed for creating, updating and deleting questions.
     *
     * @param args (unused) arguments from the console
     * @throws Exception possible thrown exception
     *
     * @see <a href="https://dzone.com/articles/spring-boot-applicationrunner-and-commandlinerunne">
     *          Spring Boot: ApplicationRunner and CommandLineRunner
     *      </a>
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("Admin token: {}", adminTokenService.getAdminToken());
    }
}
