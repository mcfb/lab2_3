package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimilarityFinderTest {

    @Test
    public void shouldReturnOneIfSetsAreTheSame() {

        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {1, 2, 3, 4, 5};

        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, Matchers.equalTo(1.0));

    }


    @Test
    public void shouldReturnZeroIfSetsAreCompletelyDifferent() {

        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {6, 7, 8, 9};

        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, Matchers.equalTo(0.0));
    }


    @Test
    public void shouldReturnHalfIfHalfElementsAreTheSameAsSecondSequence() {

        int[] seq1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] seq2 = {1, 2, 3, 4, 5};

        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, Matchers.equalTo(0.5));
    }

    @Test
    public void shouldReturnZeroIfOneSetIsEmpty() {

        int[] seq1 = {1, 2, 3};
        int[] seq2 = {};

        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertThat(result, Matchers.equalTo(0.0));
    }

    @Test
    public void searchMethodMustBeCalledForEveryElementInFirstSequence() {

        int[] seq1 = {1, 2, 3, 4, 6};
        int[] seq2 = {2, 3, 5};

        SequenceSearcherDoubler.setNumOfCalls(0);
        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        assertThat(SequenceSearcherDoubler.getNumOfCalls(), Matchers.equalTo(seq1.length));
    }


    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfSequencesAreNotInitialized() {

        int[] seq1 = null;
        int[] seq2 = null;

        SequenceSearcherDoubler.setNumOfCalls(0);
        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcherDoubler());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        assertThat(result, Matchers.equalTo(1.0));

    }


}
