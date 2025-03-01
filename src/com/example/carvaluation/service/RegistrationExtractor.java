package com.example.carvaluation.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationExtractor {

    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("[A-Z]{2}[0-9]{2}\s?[A-Z]{3}|[A-Z]{3} [0-9]{3}");

    public List<String> extractRegistrationNumbers(String filePath) throws IOException {
        List<String> registrations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = REGISTRATION_PATTERN.matcher(line);
                while (matcher.find()) {
                    String reg = matcher.group();
                    if (!reg.contains(" "))
                        reg = reg.substring(0, 4)+ " "+reg.substring(4);
                    registrations.add(reg);
                }
            }
        }
        return registrations;
    }
}