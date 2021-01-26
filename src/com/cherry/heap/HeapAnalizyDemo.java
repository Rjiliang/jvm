package com.cherry.heap;


import java.util.ArrayList;
import java.util.List;

public class HeapAnalizyDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000L);
        List<Note> notes = new ArrayList<>();
        while (true) {
            char[] array = new char[1024*1024*10];
            Note note = new Note();
            note.setText(new String(array));
            notes.add(note);
            if (notes.size() == 100) {
                System.out.println("2...");
                break;
            }
        }
        Thread.sleep(30000000000L);
    }
}

class Note{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
