
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm, pm1;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");

        pm1 = new PrefixMatches(new RWayTrie());
        pm1.load("qabc", "abce", "wabcd", "abcde", "arbcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";
        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix1_String() {
        String pref = "ab";
        Iterable<String> result = pm1.wordsWithPrefix(pref);
        String[] expResult = {"abce", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithNotExistingPrefix_String() {
        String pref = "abz";
        Iterable<String> result = pm1.wordsWithPrefix(pref);
        String[] expResult = { };

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }



}
