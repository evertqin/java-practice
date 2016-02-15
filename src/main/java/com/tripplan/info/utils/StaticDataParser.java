package com.tripplan.info.utils;

import com.sun.deploy.util.StringUtils;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruoguqin on 2/15/16.
 */
public class StaticDataParser {

    List<List<String>> _data;

    public StaticDataParser() {

    }

    public static List<List<Integer>> parseCsv(String filePath) {
        List<List<Integer>> parseResult = new ArrayList<>();

        if (filePath == null) {
            System.out.println("File path cannot be null");
            return parseResult;
        }

        String line = null;

        try {
            FileReader fr = new FileReader(filePath);

            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {

                List<Integer> normalizedTokens = Arrays.stream(StringUtils.splitString(line, ","))
                        .map(u -> Integer.parseInt(StringUtils.trimWhitespace(u)))
                        .collect(Collectors.toList());
                parseResult.add(normalizedTokens);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseResult;
    }

}
