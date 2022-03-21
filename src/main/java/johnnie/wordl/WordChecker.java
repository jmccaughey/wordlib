package johnnie.wordl;

import java.util.List;

public class WordChecker {

    public boolean testWord(String word, WordleState state) {
        return passesNotPresent(word, state)
                && passesAt(word, state.getPresentAt())
                && passesNotAt(word, state);
    }

    private static boolean passesNotAt(String word, WordleState state) {
        // not at entries should
        //  all be present in word
        //  not be at current location
        System.out.println("testing '" + word + "' for not at" );
        for (LetterPlace lp : state.getPresent()) {
            if (!word.contains(lp.getCharacter() + "")) {
                System.out.println(word + " does not contain " + lp.getCharacter());
                return false;
            } else {
                if (word.charAt(lp.getIndex()) == lp.getCharacter()) {
                    System.out.println(word + " contains " + lp.getCharacter() + " at " + lp.getIndex());
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean passesAt(String word, List<LetterPlace> at) {
        System.out.println("testing '" + word + "' for at" );
        for (LetterPlace lp : at) {
            if (lp.getCharacter().compareTo(word.charAt(lp.getIndex())) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean passesNotPresent(String word, WordleState state) {
        System.out.println("testing '" + word + "' for not present" );
        for (Character character : state.getNotPresent()) {
            if (word.indexOf(character) > -1) {
                System.out.println("found '" + character + "' from not present");
                // if char is green elsewhere, don't fail
                boolean exempt = false;
                for (LetterPlace lp : state.getPresentAt()) {
                    if (lp.getCharacter() == character) {
                        System.out.println("found " + character + " but exempting");
                        exempt = true;
                        break;
                    }
                }
                if (!exempt) {
                    return false;
                }
            }
        }
        return true;
    }

}
