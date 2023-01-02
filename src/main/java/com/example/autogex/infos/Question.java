package com.example.autogex.infos;

import java.util.ArrayList;

public class Question {
    public int maxSize,minSize,fixedSize;
    public ArrayList<String> prefix = new ArrayList<String>(),suffix=new ArrayList<String>();
    public ArrayList<Repetition> repetitions=new ArrayList<Repetition>();
    public Question(){
    }

}
