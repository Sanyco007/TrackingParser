package com.tracking;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Random;

public class HomePage {

    private WebDriver driver;
    private final String url = "http://tracking.st";

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div[1]/form/div[2]/input")
    WebElement trackItButton;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div[1]/form/div[1]/div[2]/textarea")
    WebElement textArea;

    public HomePage(WebDriver driver) {
        driver.get(url);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage(WebDriver driver, boolean someOther) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void pasteTextToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public ResultPage search(List<String> lines) {
        StringBuilder searchText = new StringBuilder();
        String CRLF = "\r\n";
        for (String track : lines) {
            searchText.append(track + CRLF);
        }

        pasteTextToClipboard(searchText.toString());

        textArea.sendKeys(Keys.CONTROL + "V");
        trackItButton.click();
        return new ResultPage(driver);
    }

}
