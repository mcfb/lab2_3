package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDoubler implements SequenceSearcher {

    private static int numOfCalls = 0;

    @Override
    public SearchResult search(int key, int[] seq) {

        numOfCalls++;

        SearchResult.Builder builder = SearchResult.builder();
        for (int i = 0; i < seq.length; i++) {
            if (key == seq[i]) {
                builder.withFound(true);
                builder.withPosition(i);
            }
        }

        return builder.build();
    }

    public static void setNumOfCalls(int numOfCalls) {
        SequenceSearcherDoubler.numOfCalls = numOfCalls;
    }

    public static int getNumOfCalls() {
        return numOfCalls;
    }
}
