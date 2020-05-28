package com.benvonderhaar.honee.transpiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class HoneeClassLoader {

    public static void loadHoneeClass(String pathToClass) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(pathToClass));
            String fileContents = reader.lines()
                    .collect(Collectors.joining("\n"));

            Lexer.processHnFileContents(fileContents);

        } catch (Throwable throwable) {

            throwable.printStackTrace();
            System.exit(1);

        } finally {

            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }

    }
}
