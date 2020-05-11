package co.uk.app;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Setter
public class WordCounter {
    Map<String, Integer> wordCount = new HashMap<>();

    TranslateWord translateWord;

    public void addWord(String word) {
        if (!word.matches("[a-zA-Z]+")) {
            System.out.println("Invalid word " + word);
        } else {
            String englishWord = translateWord.translate(word);
            wordCount.computeIfPresent(englishWord, (s, count) -> ++count);
            wordCount.computeIfAbsent(englishWord, s -> 1);
        }
    }

    public Optional<Integer> getWordCount(String word) {
        return Optional.ofNullable(wordCount.get(word));
    }
}
