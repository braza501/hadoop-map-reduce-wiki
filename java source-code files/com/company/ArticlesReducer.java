package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Admin on 18.03.2017.
 */
public class ArticlesReducer {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            String article = null;
            String currentArticle = null;
            int currentCount = 0;
            TreeMap<Integer, String> treeMap = new TreeMap<>();

            while ((input = br.readLine()) != null) {
                String[] parts = input.split("\t");
                article = parts[0];
                int count = Integer.parseInt(parts[1]);
                if (currentArticle != null && currentArticle.equals(article)) {
                    currentCount += count;
                } else {
                    if (currentArticle != null) {
                        System.out.println(currentArticle + "\t" + currentCount);
                    }
                    currentArticle = article;
                    currentCount = count;
                }
            }
            if (currentArticle != null && currentArticle.equals(article)) {
                System.out.println(currentArticle + "\t" + currentCount);
            }
        } catch (Exception io) {
            io.printStackTrace();
        }

    }
}
