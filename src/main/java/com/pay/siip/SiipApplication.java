package com.pay.siip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.TimeZone;

@RestController
@SpringBootApplication
public class SiipApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.CHINA);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(SiipApplication.class, args);
    }

    @GetMapping("/")
    public String startTip() {
        return "siip pay api is running !!!";
    }
}
