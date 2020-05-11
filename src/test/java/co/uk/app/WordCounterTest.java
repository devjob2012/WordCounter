package co.uk.app;

import co.uk.app.TranslateWord;
import co.uk.app.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class WordCounterTest {
    public static final String FLOWER = "Flower";
    WordCounter wordCounter = new WordCounter();
    TranslateWord translateWord = Mockito.mock(TranslateWord.class);

    @Before
    public void setup() {
        wordCounter.setTranslateWord(translateWord);
    }

    @Test
    public void should_add_word_count() {
        when(translateWord.translate(FLOWER)).thenReturn(FLOWER);
        wordCounter.addWord(FLOWER);
        Optional<Integer> count = wordCounter.getWordCount(FLOWER);
        assertEquals(1, count.get().intValue());
    }

    @Test
    public void should_increment_word_count() {
        when(translateWord.translate(FLOWER)).thenReturn(FLOWER);
        wordCounter.addWord(FLOWER);
        wordCounter.addWord(FLOWER);
        Optional<Integer> count = wordCounter.getWordCount(FLOWER);
        assertEquals(2, count.get().intValue());
    }

    @Test
    public void should_increment_word_count_for_language() {
        String spanishWord = "flor";
        String frenchWord = "blume";

        when(translateWord.translate(spanishWord)).thenReturn(FLOWER);
        when(translateWord.translate(frenchWord)).thenReturn(FLOWER);
        when(translateWord.translate(FLOWER)).thenReturn(FLOWER);

        wordCounter.addWord(spanishWord);
        wordCounter.addWord(frenchWord);
        wordCounter.addWord(FLOWER);

        Optional<Integer> count = wordCounter.getWordCount(FLOWER);
        assertEquals(3, count.get().intValue());
    }

    @Test
    public void should_not_add_word_count() {
        wordCounter.addWord("London123");
        Optional<Integer> count = wordCounter.getWordCount("London123");
        assertFalse(count.isPresent());
    }
}
