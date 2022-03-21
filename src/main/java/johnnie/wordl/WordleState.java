package johnnie.wordl;

import java.util.ArrayList;
import java.util.List;

public class WordleState {

    private final List<Character> notPresent = new ArrayList<>();
    private final List<LetterPlace> present = new ArrayList<>();
    private final List<LetterPlace> presentAt = new ArrayList<>();

    public void addNotPresent(Character character) {
        this.notPresent.add(character);
    }

    public void addNotAt(Character character, int index) {
        this.present.add(new LetterPlace(character, index));
    }

    public void addAt(Character character, int index) {
        this.presentAt.add(new LetterPlace(character, index));
    }

    public List<Character> getNotPresent() {
        return notPresent;
    }

    public List<LetterPlace> getPresent() {
        return present;
    }

    public List<LetterPlace> getPresentAt() {
        return presentAt;
    }

}
