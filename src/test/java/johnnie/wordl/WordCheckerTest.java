package johnnie.wordl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordCheckerTest {

    private WordSource wordSource = new LocalWordSource("/Users/jmccaughey/Downloads/words.txt");
    private WordChecker wordChecker = new WordChecker();

    @Test
    void testWordGreenAndBlackLetter() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('f');
        wordleState.addNotPresent('w');
        wordleState.addNotPresent('s');
        wordleState.addNotPresent('g');
        wordleState.addNotPresent('o');
        wordleState.addNotPresent('m');
        wordleState.addAt('l', 1);
        wordleState.addAt('o', 2);
        assertTrue(wordChecker.testWord("alone", wordleState));
        assertTrue(wordChecker.testWord("cloth", wordleState));
    }

    @Test
    void testYellows() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('p');
        wordleState.addNotPresent('n');
        wordleState.addNotPresent('e');
        wordleState.addNotAt('h', 1);
        wordleState.addNotAt('o', 2);
        assertFalse(wordChecker.testWord("phone", wordleState));
        assertFalse(wordChecker.testWord("cloth", wordleState));
    }

    @Test
    void testList() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('p');
        wordleState.addNotPresent('n');
        wordleState.addNotPresent('e');
        wordleState.addNotAt('h', 1);
        wordleState.addNotAt('o', 2);
        List<String> words = wordSource.getWords();
        List<String> possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        wordleState.addAt('h', 0);
        wordleState.addAt('o', 1);
        wordleState.addAt('r', 3);
        wordleState.addNotPresent('u');
        wordleState.addNotPresent('s');
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        wordleState.addAt('a', 2);
        wordleState.addAt('d', 4);
        possible = getPossible(wordleState, words);
        assertTrue(possible.size() == 1);
    }

    @Test
    void testList2() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('t');
        wordleState.addNotPresent('s');
        wordleState.addNotPresent('k');
        wordleState.addNotAt('a', 1);
        List<String> words = wordSource.getWords();
        List<String> possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 478);

        wordleState.addNotPresent('p');
        wordleState.addNotPresent('c');
        wordleState.addNotPresent('e');
        wordleState.addNotAt('a', 2);
        wordleState.addAt('l', 1);
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 15);

        wordleState.addNotPresent('b');
        wordleState.addNotPresent('u');
        wordleState.addNotPresent('m');
        wordleState.addAt('a', 0);
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());
        assertTrue(possible.size() == 10);

        wordleState.addAt('l', 2);
        wordleState.addAt('o', 3);
        possible = getPossible(wordleState, words);
        assertTrue(possible.size() == 2);
        wordleState.addAt('w', 4);
        possible = getPossible(wordleState, words);
        assertTrue(possible.size() == 1);
        possible = getPossible(wordleState, words);
        assertEquals("allow", possible.get(0));
    }
    
    private List<String> getPossible(WordleState wordleState, List<String> words) {
        List<String> possible;
        possible = new ArrayList<>();
        for (String word : words) {
            if (wordChecker.testWord(word, wordleState)) {
                possible.add(word);
            }
        }
        return possible;
    }


}