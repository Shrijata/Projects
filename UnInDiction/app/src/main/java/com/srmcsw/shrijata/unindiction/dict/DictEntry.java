package com.srmcsw.shrijata.unindiction.dict;

/**
 * Created by SHRIJATA on 013 13-08-2014.
 */

import java.util.ArrayList;

public class DictEntry {
    private String word, pos;
    private ArrayList<DictSense> senses = new ArrayList<DictSense>();

    public void addWord(String word) {
        this.word = word;
    }

    public void addPOS(String pos) {
        this.pos = pos;
    }

    public void addSenses(String senses) {
        senses = senses.replaceAll("\"", "");
        String[] sense = senses.split("--");
        for (String aSense : sense) {
            this.senses.add(new DictSense(aSense));
        }

    }

    public String getWord() {
        return word;
    }

    public String getPOS() {
        return pos;
    }

    public int getSensesCount() {
        return senses.size();
    }

    public ArrayList getSenses() {
        return senses;
    }
}