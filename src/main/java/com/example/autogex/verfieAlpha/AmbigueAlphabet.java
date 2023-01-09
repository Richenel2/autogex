package com.example.autogex.verfieAlpha;


import java.util.*;
import java.util.stream.Collectors;

public class AmbigueAlphabet {
    public AmbigueAlphabet() {
    }


    public List<String> EliminerDoublons(List<String> alphabet) {
        Set<String> tempAlphabet = new LinkedHashSet();
        tempAlphabet.addAll(alphabet);
        alphabet.clear();
        alphabet.addAll(tempAlphabet);
        return alphabet;
    }

    public List<Integer> LengthCalculWord(List<String> alphabet) {
        List<String> alphabetWithNotDoublons = this.EliminerDoublons(alphabet);
        ArrayList<Integer> lengthWord = new ArrayList();

        for(int i = 0; i < alphabetWithNotDoublons.size(); ++i) {
            lengthWord.add(((String)alphabetWithNotDoublons.get(i)).length());
        }

        List<String> newList = (List)lengthWord.stream().map(String::valueOf).collect(Collectors.toList());
        newList = this.EliminerDoublons(newList);
        List<Integer> NewlengthWord = (List)newList.stream().map((s) -> {
            return Integer.parseInt(s);
        }).collect(Collectors.toList());
        return NewlengthWord;
    }

    public static Integer getMin(List<Integer> list) {
        if (list != null && list.size() != 0) {
            List<Integer> sortedList = new ArrayList(list);
            Collections.sort(sortedList);
            return (Integer)sortedList.get(0);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public boolean isSubstring(String s, String seq) {
        return s.contains(seq);
    }

    public String isContaint(String s, List<String> list) {
        String a = "false";

        for(int i = 0; i < list.size(); ++i) {
            if (s.equals(list.get(i))) {
                a = "true";
            }
        }

        return a;
    }

    public List<String> StartingConstructNewAlphabet(List<String> alphabet) {
        List<String> alphabetWithNotDoublons = this.EliminerDoublons(alphabet);
        List<Integer> NewlengthWord = this.LengthCalculWord(alphabet);
        int minLengthWord = getMin(NewlengthWord);
        List<String> ConstructNewAlphabet = new ArrayList();
        String a = "true";

        for(int i = 0; i < alphabetWithNotDoublons.size(); ++i) {
            if (((String)alphabetWithNotDoublons.get(i)).length() == minLengthWord) {
                ConstructNewAlphabet.add((String)alphabetWithNotDoublons.get(i));
            }
        }

        List<String> tempConstructNewAlphabet = new ArrayList();
        tempConstructNewAlphabet.addAll(alphabetWithNotDoublons);
        alphabetWithNotDoublons.removeAll(ConstructNewAlphabet);
        String temp = "";

        for(int i = 0; i < alphabetWithNotDoublons.size(); ++i) {
            tempConstructNewAlphabet.remove(alphabetWithNotDoublons.get(i));

            for(int j = 0; j < tempConstructNewAlphabet.size(); ++j) {
                temp = temp + (String)tempConstructNewAlphabet.get(j);
            }

            String[] symbol = ((String)alphabetWithNotDoublons.get(i)).split("");

            for(int k = 0; k < symbol.length; ++k) {
                if (!temp.contains(symbol[k])) {
                    ConstructNewAlphabet.add((String)alphabetWithNotDoublons.get(i));
                }
            }

            tempConstructNewAlphabet.add((String)alphabetWithNotDoublons.get(i));
            temp = "";
        }

        return ConstructNewAlphabet;
    }

    public List<String> CompletedConstructNewAlphabet(List<String> alphabet) throws Exception {
        List<String> restOfAlphabet = this.EliminerDoublons(alphabet);
        List<String> tempConstructNewAlphabet = new ArrayList<String>(restOfAlphabet);
        String temp = "";
        List<String> completedAlphabet = this.StartingConstructNewAlphabet(alphabet);
        restOfAlphabet.removeAll(completedAlphabet);
        List<String> result = new ArrayList<String>();
        boolean ambigue = false;

        for (String s : restOfAlphabet) {
            tempConstructNewAlphabet.remove(s);
            String[] symbols = s.split("");

            for (int j = 0; j < symbols.length; ++j) {
                temp = temp + symbols[j];
                if (Objects.equals(this.isContaint(temp, tempConstructNewAlphabet), "true") && j < symbols.length - 1) {
                    throw new Exception("Votre alphabet est ambigue sur le mots :" + s);
                }
            }

            tempConstructNewAlphabet.add(s);
            completedAlphabet.add(s);
        }

        return completedAlphabet;
    }

    public List<String> verifierAlphabet(List<String> alphabet) throws Exception {
        return this.CompletedConstructNewAlphabet(alphabet);
    }

    public List<String> verifierRegex(String regex) {
        return null;
    }
}
