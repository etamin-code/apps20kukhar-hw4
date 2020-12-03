package ua.edu.ucu.tries;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class Letter {
    private Letter parent;
    private ArrayList<Letter> children = new ArrayList<>();
    private boolean isEndOfWord;
    private char letter;

    public Letter(Letter parent, char letter, boolean isEndOfWord) {
        this.parent = parent;
        this.letter = letter;
        this.isEndOfWord = isEndOfWord;
    }

    public void addChild(char letter, boolean isEndOfWord) {
        children.add(new Letter(this, letter, isEndOfWord));
    }

    public void deleteChild(char letter) {
        for (Letter child: children) {
            if (child.getLetter() == letter) {
                children.remove(child);
                break;
            }
        }
        throw new Error("letter " + letter + " not found");
    }

    public boolean hasChild(char letter) {
        for (Letter child: children) {
            if (child.getLetter() == letter) {
                return true;
            }
        }
        return false;
    }

//    if this child not exist then create this
    public Letter getChild(char letter) {
        for (Letter child : children) {
            if (child.getLetter() == letter) {
                return child;
            }
        }
        Letter newChild = new Letter(this, letter, false);
        children.add(newChild);
        return newChild;
    }

    public Letter getChildIfExist(char letter) {
        for (Letter child : children) {
            if (child.getLetter() == letter) {
                return child;
            }
        }
        return (Letter) null;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }




}
