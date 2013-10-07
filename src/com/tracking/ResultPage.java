package com.tracking;

import com.pojo.TrackResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class ResultPage {

    private WebDriver driver;

    private final int TRACK_INDEX = 1;
    private final int STATUS_INDEX = 6;
    private final int RECORD_COLUMNS = 8;

    @FindBy(xpath = "//*[@id=\"tracks\"]/tbody")
    WebElement table;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/a[1]")
    WebElement homeButton;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<TrackResult> getResult(String pattern) {
        List<TrackResult> trackResults = new ArrayList<TrackResult>();
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            WebElement tableRow = rows.get(i);
            List<WebElement> columns = tableRow.findElements(By.tagName("td"));
            if (columns.size() < RECORD_COLUMNS) continue;
            String track = columns.get(TRACK_INDEX).getText();
            String status = columns.get(STATUS_INDEX).getText().trim();
            if (status.toLowerCase().contains(pattern.toLowerCase())) {
                TrackResult item = new TrackResult(track, status);
                trackResults.add(item);
            }
        }
        return trackResults;
    }

    public HomePage goToHome() {
        homeButton.click();
        HomePage homePage = new HomePage(driver, true);
        return homePage;
    }

    public void close() {
        driver.quit();
        ChromeEngine.service.stop();
    }
}
