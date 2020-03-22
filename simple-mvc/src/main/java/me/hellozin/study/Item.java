package me.hellozin.study;

import java.time.LocalDate;

public class Item {

    private String id;
    private String name;
    private LocalDate date;

    public Item(String id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
