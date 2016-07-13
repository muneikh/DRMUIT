package com.sample.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.mime.TypedInput;

public class Utility {

    public static String convertTypeInputToString(TypedInput body) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
