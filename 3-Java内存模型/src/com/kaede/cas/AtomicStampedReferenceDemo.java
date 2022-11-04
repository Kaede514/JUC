package com.kaede.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author kaede
 * @create 2022-09-13 11:12
 */

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        Book javaBook = new Book(1, "JavaBook");

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println(stampedReference.getReference() + "  version = " + stampedReference.getStamp());

        Book mySQLBook = new Book(2, "MySQLBook");
        boolean b;

        b = stampedReference.compareAndSet(javaBook, mySQLBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b);
        System.out.println(stampedReference.getReference() + "  version = " + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mySQLBook,javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b);
        System.out.println(stampedReference.getReference() + "  version = " + stampedReference.getStamp());
    }

}

class Book {
    private int id;
    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
