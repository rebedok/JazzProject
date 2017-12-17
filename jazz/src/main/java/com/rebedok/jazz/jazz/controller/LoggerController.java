package com.rebedok.jazz.jazz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


@RestController
@RequestMapping(value = "/api/log")
public class    LoggerController {

    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<String>> getLog() {
        ArrayList<String> strings = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new File("D:/Jazz/logs/jazzLog.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            if(strings.size() > 10) strings.remove(0);
            strings.add(in.nextLine());
        }
        return new ResponseEntity<>(strings, HttpStatus.OK);
    }
}
