
package lab2_3;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.similarity.SimilarityFinder;

public class SimilarityFinderTest {

    SimilarityFinder similarityFinder;
    final static int THE_SAME = 1;
    final static int DIFFERENT = 0;

    @Before
    public void prepare() {
        similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
    }

    @Test
    public void shouldReturnOneIfSequencesAreEmpty() {
        int[] seq1 = {};
        int[] seq2 = {};

        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        boolean result = Math.round(jackardSimilarity) == 1 ? true : false;
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
        boolean result = Math.round(jackardSimilarity) == 1 ? true : false;

        assertThat(result, is(equalTo(false)));
    }

}
