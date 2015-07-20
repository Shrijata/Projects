package com.srmcsw.shrijata.unindiction.dict;



import java.util.ArrayList;

public class DictSense {
    private String meaning;
    private ArrayList<DictExample> examples = new ArrayList<DictExample>();

    DictSense(String sense) {
        String[] currSense = sense.split("\n");
        meaning = currSense[0];
        for (int i = 1; i < currSense.length; i++)
            examples.add(new DictExample(currSense[i]));
    }

    public String getMeaning() {
        return meaning;
    }

    public int getExamplesCount() {
        return examples.size();
    }

    public ArrayList getExamples() {
        return examples;
    }
}