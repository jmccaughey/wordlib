package johnnie.wordl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordCheckerTest {

    // words file from https://github.com/charlesreid1/five-letter-words/blob/master/sgb-words.txt
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

    @Test
    void testList3() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('f');
        wordleState.addNotPresent('l');
        wordleState.addNotPresent('s');
        wordleState.addNotPresent('h');
        wordleState.addNotAt('e', 2);
        List<String> words = wordSource.getWords();
        List<String> possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 831);

        wordleState.addNotPresent('m');
        wordleState.addNotPresent('o');
        wordleState.addNotPresent('y');
        wordleState.addAt('n', 2);
        wordleState.addAt('e', 3);
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 19);

        wordleState.addAt('r', 0);
        wordleState.addAt('e', 1);
        wordleState.addAt('w', 4);
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());
        assertEquals("renew", possible.get(0));
    }

    @Test
    void testList4() {
        WordleState wordleState = new WordleState();
        wordleState.addNotPresent('f');
        wordleState.addNotPresent('l');
        wordleState.addNotPresent('w');
        wordleState.addNotPresent('s');
        wordleState.addAt('o', 2);
        List<String> words = wordSource.getWords();
        List<String> possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 138);

        wordleState.addNotPresent('q');
        wordleState.addNotPresent('u');
        wordleState.addNotPresent('t');
        wordleState.addNotAt('e', 4);
        //wordleState.addAt('n', 2);
        //wordleState.addAt('e', 3);
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());

        assertTrue(possible.size() == 13);

        wordleState.addAt('e', 0);
        wordleState.addAt('p', 1);
        wordleState.addNotPresent('c');
        wordleState.addNotPresent('h');
        possible = getPossible(wordleState, words);
        assertFalse(possible.isEmpty());
        assertEquals("epoxy", possible.get(0));
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