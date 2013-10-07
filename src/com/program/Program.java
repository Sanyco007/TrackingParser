package com.program;

import com.pojo.TrackResult;
import com.tracking.ChromeEngine;
import com.tracking.HomePage;
import com.tracking.ResultPage;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        String fileName = "in.txt";
        String filePattern = "pattern.txt";
        try {

            try {
                new File("out.txt").delete();
            }
            catch (Exception e) {}
            List<String> lines = new FileReader(fileName).readLines();

            FileInputStream fis = new FileInputStream(filePattern);
            String pattern = IOUtils.readLines(fis).get(0);
            fis.close();

            final int ITEMS = 100;
            int count = lines.size() / ITEMS + (lines.size() % ITEMS > 0 ? 1 : 0);

            List<TrackResult> result = new ArrayList<TrackResult>();
            HomePage homePage = new HomePage(ChromeEngine.getInstance());
            ResultPage resultPage = null;
            for (int i = 0; i < count; i++) {

                List<String> partLines = new ArrayList<String>();
                for (int j = 0; j < ITEMS; j++) {
                    if (ITEMS * i + j >= lines.size()) break;
                    partLines.add(lines.get(ITEMS * i + j));
                }

                resultPage = homePage.search(partLines);
                List<TrackResult> pageData = resultPage.getResult(pattern);
                for (TrackResult item : pageData) {
                    result.add(item);
                }
                homePage = resultPage.goToHome();
            }

            FileOutputStream fos = new FileOutputStream("out.txt");
            IOUtils.writeLines(result, "\r\n", fos);
            fos.close();

            resultPage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
