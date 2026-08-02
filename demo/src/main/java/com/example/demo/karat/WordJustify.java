package com.example.demo.karat;

import java.util.ArrayList;
import java.util.List;

public class WordJustify {
    public List<String> fullJustify1(String[] words, int maxWidth) {
        int i = 1;
        int j = words.length;
        int len = words[0].length();
        List<String> lineWords = new ArrayList<>();
        List<String> res = new ArrayList<>();
        int count = 0;

        lineWords.add(words[0]);
        while (i < j) {
            if (len + words[i].length() + count < maxWidth) {
                len += words[i].length();
                lineWords.add(words[i]);
                count += 1;
            } else {
                res.add(justified(lineWords, len, maxWidth));
                lineWords = new ArrayList<>();
                lineWords.add(words[i]);
                len = words[i].length();
                count = 0;
            }
            i++;
        }
        if (i == j && lineWords.size() > 0) {
            res.add(leftJustified(lineWords, len, maxWidth));
        }
        return res;
    }

    private String justified(List<String> input, int len, int k) {
        int size = input.size();
        int firstGap = 0;
        int gap = 0;
        if (size > 1) {
            firstGap = (k - len) % (size - 1);
            gap = (k - len) / (size - 1);
        } else {
            gap = k - len;
        }
        int space = 0;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb = sb.append(input.get(i));

            if (i == 0) {
                space = gap + firstGap;
            } else {
                space = gap;
            }
            if (i != size - 1 || size == 1) {
                sb = sb.append(" ".repeat(space));
            }
        }
        return sb.toString();

    }

    private String leftJustified(List<String> input, int len, int k) {
        int size = input.size();
        int space = 0;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb = sb.append(input.get(i));
            sb.append(" ");
        }
        space = sb.length();

        sb = sb.append(" ".repeat(k - space));
        return sb.toString();

    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            List<String> currentLine = getWords(i, words, maxWidth);
            i += currentLine.size();
            ans.add(createLine(currentLine, i, words, maxWidth));
        }

        return ans;
    }

    private List<String> getWords(int i, String[] words, int maxWidth) {
        List<String> currentLine = new ArrayList<>();
        int currLength = 0;

        while (i < words.length && currLength + words[i].length() <= maxWidth) {
            currentLine.add(words[i]);
            currLength += words[i].length() + 1;
            i++;
        }

        return currentLine;
    }

    private String createLine(
            List<String> line,
            int i,
            String[] words,
            int maxWidth
    ) {
        int baseLength = -1;
        for (String word : line) {
            baseLength += word.length() + 1;
        }

        int extraSpaces = maxWidth - baseLength;

        if (line.size() == 1 || i == words.length) {
            return String.join(" ", line) + " ".repeat(extraSpaces);
        }

        int wordCount = line.size() - 1;
        int spacesPerWord = extraSpaces / wordCount;
        int needsExtraSpace = extraSpaces % wordCount;

        for (int j = 0; j < needsExtraSpace; j++) {
            line.set(j, line.get(j) + " ");
        }

        for (int j = 0; j < wordCount; j++) {
            line.set(j, line.get(j) + " ".repeat(spacesPerWord));
        }

        return String.join(" ", line);
    }


    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words1 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        WordJustify wj = new WordJustify();
        int k = 16;
        // List<String> res = wj.fullJustify(words, k);
        List<String> res = wj.fullJustify(words1, k);
        System.out.println("[");
        for (String s : res) {
            System.out.println("\"" + s + "\",");
        }
        System.out.println("]");
        //System.out.println(res.toString());
    }
}
