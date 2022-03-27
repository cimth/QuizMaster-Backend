package com.example.quizmaster_backend.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
    value = "server.ssl.enabled",
    havingValue = "true",
    matchIfMissing = false
)
public class ServerConfig {

    /*======================================*
     * FIELDS
     *======================================*/

    private final int HTTP_PORT = 8080;
    private final int HTTPS_PORT = 8443;

    /*======================================*
     * CONFIGURATION
     *======================================*/

    /**
     * Create a servlet container for redirecting HTTP requests to HTTPS.
     * <br />
     * Note that the redirection will always lead to GET requests, thus you need to use HTTPS URLs for POST, PUT
     * and DELETE requests.
     *
     * @return a servlet container with the described configuration
     *
     * @see
     *      <a href="https://www.thomasvitale.com/https-spring-boot-ssl-certificate/">
     *          How to enable HTTPS in a Spring Boot Java application
     *      </a>
     *
     * @see
     *      <a href="https://stackoverflow.com/questions/48629911/spring-boot-redirect-from-http-to-https-results-in-405-error-for-put-method>
     *          Infos about redirecting only to GET requests
     *      </a>
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectToHttpsConnector());
        return tomcat;
    }

    /**
     * Returns a connector for redirecting HTTP to HTTPS.
     * <br />
     * Note that the redirection will always lead to GET requests, thus you need to use HTTPS URLs for POST, PUT
     * and DELETE requests.
     *
     * @return the configured connector
     *
     * @see
     *      <a href="https://www.thomasvitale.com/https-spring-boot-ssl-certificate/">
     *          How to enable HTTPS in a Spring Boot Java application
     *      </a>
     *
     * @see
     *      <a href="https://stackoverflow.com/questions/48629911/spring-boot-redirect-from-http-to-https-results-in-405-error-for-put-method>
     *          Infos about redirecting only to GET requests
     *      </a>
     */
    private Connector redirectToHttpsConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(HTTP_PORT);
        connector.setSecure(false);
        connector.setRedirectPort(HTTPS_PORT);
        return connector;
    }
}
