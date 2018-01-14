package com.jersey.tutorial.model;

public class CustomParam {
    public Character firstLetter;

    public CustomParam(String input) {
        firstLetter=input.charAt(0);
    }
}
