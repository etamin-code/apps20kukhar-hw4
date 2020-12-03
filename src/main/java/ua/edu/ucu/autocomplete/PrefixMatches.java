package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        ArrayList<String> words = new ArrayList<>();
        String[] line;
        int numOfWords = 0;
        for (String string: strings) {
            line = string.split(" ");
            words.addAll(Arrays.asList(line));
        }
        for (String word: words) {
            if (word.length() > 2) {
                trie.add(new Tuple(word, word.length()));
                numOfWords++;
            }
        }
        return numOfWords;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("pref must has at least 2 chars");
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("pref must has at least 2 chars");
        }
        ArrayList<String> words = (ArrayList<String>) trie.wordsWithPrefix(pref);
        words.removeIf(word -> word.length() > 2 + k);
        return words;
    }

    public int size() {
        return trie.size();
    }
}
