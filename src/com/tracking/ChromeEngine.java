package com.tracking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class ChromeEngine {

    public static ChromeDriverService service;
    private static WebDriver instance;

    private ChromeEngine() {

    }

    public static WebDriver getInstance() {
        if (instance == null) {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            instance = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
            instance.manage().timeouts().implicitlyWait(1000, TimeUnit.MINUTES);
            instance.manage().timeouts().pageLoadTimeout(1000, TimeUnit.MINUTES);
            instance.manage().timeouts().setScriptTimeout(1000, TimeUnit.MINUTES);
        }
        return instance;
    }

}
