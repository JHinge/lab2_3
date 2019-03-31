package lab2_3;

import java.util.Stack;
import java.util.Vector;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SearchResult.Builder;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDoubler implements SequenceSearcher {

    public static int callCounter = 0;
    public static Vector<Integer> recivedKeysToSearch = new Vector<Integer>();
    public static Stack<Boolean> valuesToReturn = new Stack<Boolean>();

    @Override
    public SearchResult search(int key, int[] seq) {
        callCounter++;
        recivedKeysToSearch.add(key);

        Builder builder = SearchResult.builder();
        builder.withFound(valuesToReturn.pop());
        return builder.build();
    }

}
