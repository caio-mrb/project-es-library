package org.example;

import java.util.LinkedList;

public class Searchable {

    private final int code;
    private String name;

    public Searchable(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Searchable findBy(LinkedList<? extends Searchable> searchables, Object search) {
        for (Searchable searchable : searchables) {
            if (search instanceof String && searchable.getName().equals(search)) {
                return searchable;
            } else if (search instanceof Integer && searchable.getCode() == (Integer) search) {
                return searchable;
            }
        }
        return null;
    }



}
