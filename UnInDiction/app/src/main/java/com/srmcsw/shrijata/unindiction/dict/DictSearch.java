package com.srmcsw.shrijata.unindiction.dict;

/**
 * Created by SHRIJATA on 013 13-08-2014.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DictSearch {

    protected Context context;
    private BufferedReader rd = null;
    private ArrayList<DictEntry> entries = new ArrayList<DictEntry>();

    public DictSearch(Context context) {
        this.context = context;
    }

    public void makeDictionary() throws IOException {
        InputStream input;

        try {
            input = context.getAssets().open("shabdanjali.txt");
            rd = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String line, wordData = "";
            while ((line = rd.readLine()) != null) {
                //entry reading complete
                if (line.length() == 0) {
                    if (!wordData.equals("")) {
                        makeEntry(wordData);
                        wordData = "";
                    }
                } //entry reading in progress
                else {
                    wordData += line + "\n";
                }
            }

            if (!wordData.equals("")) {
                makeEntry(wordData);
                wordData = "";
            }
        } finally {
            if (rd != null) {
                rd.close();
            }
        }
    }

    public void makeEntry(String wordData) {
        DictEntry entry = new DictEntry();
        String[] lines;
        String senses = "";
        lines = wordData.split("\n");
        lines[0] = lines[0].replaceAll("\"", "");
        String[] items = lines[0].split(",");

        if (items.length == 3) {
            entry.addWord(items[0]);
            entry.addPOS(items[1]);
            String[] sensesData = wordData.split(",");
            for (int i = 2; i <sensesData.length; i++) {
                senses += sensesData[i];
            }
            //pass to DictEntry's function
            entry.addSenses(senses);
            entries.add(entry);
        }
    }

    public String search(String word) {
        int f = 0;

        for (int i = 0; i < entries.size(); i++)
            if (entries.get(i).getWord().toLowerCase().equals(word.toLowerCase())) {
                f++;
            }
        int[] index = new int[f];
        int in = 0;

        for (int i = 0; i < entries.size(); i++) {

            if (entries.get(i).getWord().toLowerCase().equals(word.toLowerCase())) {
                index[in] = i;
                in++;
            }
        }
        String info = f + (f > 1 ? " entries found:" : " entry found:") + "\n";
        for (int i : index) {
            info += "\nWord: " + entries.get(i).getWord() + "\nPOS: ";

            if (entries.get(i).getPOS().equals("Adj"))
                info += "Adjective";
            else if (entries.get(i).getPOS().equals("Abbr:"))
                info += "Abbreviation";
            else if (entries.get(i).getPOS().equals("Adv"))
                info += "Adverb";
            else if (entries.get(i).getPOS().equals("AuxV"))
                info += "Auxiliary verb";
            else if (entries.get(i).getPOS().equals("Comb form"))
                info += "Combination form";
            else if (entries.get(i).getPOS().equals("Conj"))
                info += "Conjunct";
            else if (entries.get(i).getPOS().equals("Det"))
                info += "Determiner";
            else if (entries.get(i).getPOS().equals("IDM"))
                info += "Idiom";
            else if (entries.get(i).getPOS().equals("Interj"))
                info += "Interjection";
            else if (entries.get(i).getPOS().equals("Interro"))
                info += "Interrogative";
            else if (entries.get(i).getPOS().equals("MV"))
                info += "Modal verb";
            else if (entries.get(i).getPOS().equals("N"))
                info += "Noun";
            else if (entries.get(i).getPOS().equals("Part"))
                info += "Particle";
            else if (entries.get(i).getPOS().equals("PhrV"))
                info += "Phrasal verb";
            else if (entries.get(i).getPOS().equals("PhrVI"))
                info += "Phrasal verb intransitive";
            else if (entries.get(i).getPOS().equals("PhrVT"))
                info += "Phrasal verb transitive";
            else if (entries.get(i).getPOS().equals("Pref"))
                info += "Prefix";
            else if (entries.get(i).getPOS().equals("Prep"))
                info += "Preposition";
            else if (entries.get(i).getPOS().equals("Pron"))
                info += "Pronoun";
            else if (entries.get(i).getPOS().equals("PropN"))
                info += "Proper name";
            else if (entries.get(i).getPOS().equals("Refl Pron"))
                info += "Reflexive pronoun";
            else if (entries.get(i).getPOS().equals("Rel Pron"))
                info += "Relative pronoun";
            else if (entries.get(i).getPOS().equals("Suffix"))
                info += "suffix";
            else if (entries.get(i).getPOS().equals("V"))
                info += "Verb";
            else if (entries.get(i).getPOS().equals("VI"))
                info += "Verb intransitive";
            else if (entries.get(i).getPOS().equals("Vneg"))
                info += "Verb negation";
            else if (entries.get(i).getPOS().equals("VT"))
                info += "Verb transitive";
            else if (entries.get(i).getPOS().equals("VTI"))
                info += "Verb transitive, intransitive";

            int count = entries.get(i).getSensesCount();
            ArrayList<DictSense> sensesAll = entries.get(i).getSenses();
            for (int j = 0; j < count; j++) {
                info += "\n\nMeaning: " + sensesAll.get(j).getMeaning();
                int excount = sensesAll.get(j).getExamplesCount();
                for (int k = 0; k < excount; k++) {
                    DictExample exp = (DictExample) sensesAll.get(j).getExamples().get(k);//here
                    String ex = exp.getExample();
                    info += "\nExample " + ": \n" + ex;
                }
            }
            info += "\n";
        }

        if (f == 0)
            info = "Sorry, no entries found...";
        return info;
    }
}