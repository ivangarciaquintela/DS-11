package e2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface TVRealityList extends Iterable<String> {

    boolean add(String val);
    int size();

    @Override
    Iterator<String> iterator();

}
