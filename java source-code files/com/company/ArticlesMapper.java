package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ArticlesMapper {


    public static void main(String[] args) {
        HashSet<String> forbiddenPages = new HashSet<>();

        forbiddenPages.add("404_error/");
        forbiddenPages.add("Main_Page");
        forbiddenPages.add("Hypertext_Transfer_Protocol");
        forbiddenPages.add("Search");

        String forbiddenExtensions = new String("(.jpg|.gif|.png|.JPG|.GIF|.PNG|.txt|.ico)$");

        try {
            Stream<String> stream = Files.lines(Paths.get("./forbiddenTitles.txt"));
            StringBuilder forbiddenTitles = new StringBuilder();
            forbiddenTitles.append("^(");
            stream.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    forbiddenTitles.append(s + '|');
                }
            });
            forbiddenTitles.setCharAt(forbiddenTitles.length() - 1, ')');

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while((input = br.readLine()) != null) {
                try {
                    String[] exp = input.split("\\s+");
                    if(!forbiddenPages.contains(exp[1]) && exp[0].equals("en")) {
                        Pattern p = Pattern.compile(forbiddenTitles.toString());
                        Matcher m = p.matcher(exp[1]);
                        if(!m.find()) {
                            p = Pattern.compile(forbiddenExtensions);
                            m = p.matcher(exp[1]);
                            if(!m.find()) {
                                p = Pattern.compile("^[A-Z]");
                                m = p.matcher(exp[1]);
                                if(m.find()) {
                                    System.out.println(exp[1] + '\t' + exp[2]);
                                }
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException exp) {
                    exp.printStackTrace();
                }
            }
        } catch (Exception io) {
            io.printStackTrace();
        }
    }
}
