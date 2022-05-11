package com.chopin.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chopin
 * @date 2022/3/11
 */
@RestController
public class FileAccess {

    /**
     * The way can not be applied to accessing file from jar.
     * @throws IOException IOException
     */
    @GetMapping("read-way-one")
    public void readWayOne() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("file.csv");

        File file = classPathResource.getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(",");
            System.out.println(strings[0]);
        }
    }

    /**
     * This way is for access file from jar
     * @throws IOException IOException
     */
    @GetMapping("read-way-two")
    public void readWayTwo() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("file.csv");

        InputStream inputStream = classPathResource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split(",");
            System.out.println(strings[0]);
        }

    }
}