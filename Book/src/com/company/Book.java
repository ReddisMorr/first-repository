package com.company;

/**
 * Created by THI on 21.03.2016.
 */
public class Book implements  Comparable<Book> {
    private String title;
    private String author;
    private int pages;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    @Override
    public int compareTo(Book o) {
        int result=compareToAuthor(o);
        if(result==0){
            result=compareToTitle(o);
            if(result==0){
                result=compareToPages(o);
            }
        }
        return result;
    }
    public int compareToAuthor(Book o) {
        return this.author.compareTo(o.author);
    }

    public int compareToPages(Book o) {
        if(this.pages>o.pages){
            return 1;
        }else if(this.pages<o.pages){
            return -1;
        }else {
            return 0;
        }
    }
    public int compareToTitle(Book o) {
        return this.title.compareTo(o.author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }
}
