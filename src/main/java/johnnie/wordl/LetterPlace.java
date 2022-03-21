package johnnie.wordl;

public class LetterPlace {
    private final Character character;
    private final int index;

    public LetterPlace(Character character, int index) {
        this.character = character;
        this.index = index;
    }

    public Character getCharacter() { return this.character; }

    public int getIndex() { return this.index; }


}
