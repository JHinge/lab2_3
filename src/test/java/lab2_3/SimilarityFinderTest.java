
package lab2_3;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.similarity.SimilarityFinder;

public class SimilarityFinderTest {

    SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
    final static int THE_SAME = 1;
    final static int DIFFERENT = 0;

    @Before
    public void prepare() {
        SequenceSearcherDoubler.callCounter = 0;

    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    @Test
    public void shouldReturnOneIfSequencesAreEmpty() {
        int[] seq1 = {};
        int[] seq2 = {};

        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        boolean result = Math.round(jackardSimilarity) == THE_SAME ? true : false;
        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void shouldReturnZeroIfOneSequenceIsEmpty() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {};
        SequenceSearcherDoubler.valuesToReturn.push(false);
        SequenceSearcherDoubler.valuesToReturn.push(false);
        SequenceSearcherDoubler.valuesToReturn.push(false);

        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        boolean result = Math.round(jackardSimilarity) == DIFFERENT ? true : false;

        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void shouldReturnProperJackardSimilarityValueForDifferentSequences() {
        int[] seq1 = {1, 2};
        int[] seq2 = {3, 3, 4};
        SequenceSearcherDoubler.valuesToReturn.push(true);
        SequenceSearcherDoubler.valuesToReturn.push(false);

        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        boolean result = roundAvoid(jackardSimilarity, 2) == 0.25 ? true : false;

        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void shouldReturnOneIfSequencesAreTheSame() {
        int[] seq1 = {3, 3, 4};
        int[] seq2 = {3, 3, 4};
        SequenceSearcherDoubler.valuesToReturn.push(true);
        SequenceSearcherDoubler.valuesToReturn.push(true);
        SequenceSearcherDoubler.valuesToReturn.push(true);

        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        boolean result = Math.round(jackardSimilarity) == THE_SAME ? true : false;

        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void shouldCallSearchMethodZeroTimesIfFirstSequenceIsEmpty() {
        int[] seq1 = {};
        int[] seq2 = {3, 3, 4};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);

        assertThat(0, is(equalTo(SequenceSearcherDoubler.callCounter)));
    }

}
