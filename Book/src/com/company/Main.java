package com.company;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        TreeSet<Book> books=new TreeSet<Book>();
        books.add(new Book("ettut", "wegrty",187));
        books.add(new Book("sttydjgvb","rtjrygfcv",24236));
        books.add(new Book("rtysdfv","sthetyjfgv",3457));
        books.add(new Book("sdrgrth","srgherth",13243255));
        for(Book i: books){
            System.out.println(i.toString());
        }
    }
}
