package ua.edu.ucu.tries;

import ua.edu.ucu.Queue;
import ua.edu.ucu.immutable.ImmutableLinkedList;

import java.util.ArrayList;
import java.util.Collections;

public class RWayTrie implements Trie {
    private final Letter root = new Letter((Letter) null, ' ', false);
    private int size = 0;

    @Override
    public void add(Tuple t) {
        Letter curNode = root, nextNode;
        for (int i = 0; i < t.weight; i++) {
            nextNode = curNode.getChild(t.term.charAt(i));
            curNode = nextNode;
        }
        size++;
        curNode.setEndOfWord(true);
    }

    @Override
    public boolean contains(String word) {
            Letter curNode = root, nextNode;
            for (int i = 0; i < word.length(); i++) {
                nextNode = curNode.getChildIfExist(word.charAt(i));
                if (nextNode == null) {
                    return false;
                }
                curNode = nextNode;
            }
            return curNode.isEndOfWord();
    }

    @Override
    public boolean delete(String word) {
        Letter curNode = root, nextNode;
        for (int i = 0; i < word.length(); i++) {
            nextNode = curNode.getChildIfExist(word.charAt(i));
            if (nextNode == null) {
                return false;
            }
            curNode = nextNode;
        }
        if (!curNode.isEndOfWord()) {
            return false;
        }
        size--;
        curNode.setEndOfWord(false);
        return true;
    }


    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }


    private String getWordFromLettersAbove(Letter letter) {
        ArrayList<Character> charList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (letter.getLetter() != ' ') {
            charList.add(letter.getLetter());
            letter = letter.getParent();
        }
        Collections.reverse(charList);
        for (Character ch : charList) {
            sb.append(ch);
        }
        return sb.toString();
    }


    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Letter curNode = root;
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (curNode.hasChild(s.charAt(i))) {
                curNode = curNode.getChild(s.charAt(i));
            } else {
                return words;
            }
        }
        if (curNode.isEndOfWord()) {
            words.add(getWordFromLettersAbove(curNode));
        }

        Queue queue = new Queue(new ImmutableLinkedList());
        Letter letter;
        queue.enqueue(curNode);
        while (!queue.isEmpty()) {
            curNode = (Letter) queue.peek();
            for (Letter child : curNode.getChildren()) {
                queue.enqueue(child);
                if (child.isEndOfWord()) {
                    words.add(getWordFromLettersAbove(child));
                }
            }
            queue.dequeue();
        }
        return words;

    }

    @Override
    public int size() {
         return size;
    }

}
