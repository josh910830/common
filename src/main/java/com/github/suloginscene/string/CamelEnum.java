package com.github.suloginscene.string;


public interface CamelEnum {

    String name();

    default String toCamel() {
        String[] lowerTokens = name().toLowerCase().split("_");

        String firstWord = lowerTokens[0];
        String otherWords = combinedOtherWords(lowerTokens);

        return firstWord + otherWords;
    }

    private String combinedOtherWords(String[] lowerTokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < lowerTokens.length; i++) {
            String targetWord = lowerTokens[i];
            String firstLetter = targetWord.substring(0, 1).toUpperCase();
            String otherLetters = targetWord.substring(1);
            String resultWord = firstLetter + otherLetters;
            sb.append(resultWord);
        }
        return sb.toString();
    }

}
