package com.example.carvaluation.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String[]> readCSVFile(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("VARIANT_REG")) {
                    String[] values = line.split(",");
                    records.add(values);
                }
            }
        }
        return records;
    }
}
