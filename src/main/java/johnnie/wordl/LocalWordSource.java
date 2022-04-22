package johnnie.wordl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalWordSource implements WordSource {

    List<String> words;
    String path;

    public LocalWordSource(String path) {
        this.path = path;
    }

    @Override
    public List<String> getWords() {
        if (words == null) {
            words = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(this.path)));
                while (true) {
                    String word = br.readLine();
                    if (word != null) {
                        words.add(word);
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return Collections.unmodifiableList(words);
    }

}
