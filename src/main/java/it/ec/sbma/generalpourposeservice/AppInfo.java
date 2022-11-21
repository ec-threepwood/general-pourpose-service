package it.ec.sbma.generalpourposeservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppInfo {

    @Value("${spring.application.name}")
    private String appName;

    private final Environment env;


    @PostConstruct
    public void init() throws UnknownHostException {
        log.info("HOST: {} - {} |", InetAddress.getLocalHost().getHostAddress(), appName);

        final String profiles = Arrays
                .stream(env.getActiveProfiles())
                .reduce("", (acc, elem) -> acc + elem + " | ");

        log.info("PROFILES: {}", profiles);
    }
}
