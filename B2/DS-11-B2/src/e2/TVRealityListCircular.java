package e2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TVRealityListCircular implements TVRealityList {
    List<String> myList;

    public TVRealityListCircular(List<String> lista) {
        this.myList = lista;
    }

    public TVRealityListCircular() {
        this.myList = new ArrayList<>();
    }
    @Override
    public boolean add(String val) {
        return  myList.add(val);
    }

    @Override
    public int size() {
        return myList.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new CircularIterator(myList);
    }
    class CircularIterator<String> implements Iterator<String>{

        int indexPosition = 0;
        List<String> internalList;
        boolean usadoNext = false;
        CircularIterator(List<String> internalList){
            this.internalList=internalList;

        }

        @Override
        public void remove() {
            if(!usadoNext)
                throw new IllegalStateException("No se ha llamado a next");
            internalList.remove(indexPosition);
            usadoNext = false;
            if(indexPosition == internalList.size())
                indexPosition=0;
        }

        @Override
        public boolean hasNext() {
            return internalList.size()>1;
        }

        @Override
        public String next() {
            if(internalList.size()<1)
                throw new NoSuchElementException("No contiene elementos.");
            String val = internalList.get(indexPosition);
            usadoNext = true;
            indexPosition = ((indexPosition+1)>=internalList.size())?0:(indexPosition+1);
            return val;
        }
    }
}
