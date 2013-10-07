package com.program;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileReader {

    private InputStream is;

    public FileReader(String fileName) throws FileNotFoundException {
        is = new FileInputStream(fileName);
    }

    public List<String> readLines() throws IOException {
        List<String> res = IOUtils.readLines(is);
        is.close();
        return res;
    }

}
