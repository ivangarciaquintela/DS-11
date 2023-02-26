package e2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TVRealityListRebote implements TVRealityList {
    List<String> myList ;

    public TVRealityListRebote(List<String> lista) {
        this.myList = lista;
    }

    public TVRealityListRebote() {
        this.myList =  new ArrayList<>();
    }
    @Override
    public boolean add(String val) {
        return myList.add(val);
    }

    @Override
    public int size() {
        return myList.size();
    }

    @Override
    public ReboteIterator<String> iterator() {
        return new ReboteIterator(myList);
    }
    public class ReboteIterator<String> implements Iterator<String>{

        int indexPosition = 0;
        boolean rebote = false;
        boolean usadoNext = false;
        List<String> internalList;
        ReboteIterator(List<String> internalList){
            this.internalList=internalList;

        }

        @Override
        public void remove() {
            if(!usadoNext)
                throw new IllegalStateException("No se ha llamado a next");
            internalList.remove(indexPosition);
            usadoNext = false;
            if(rebote)
                indexPosition--;
            if(internalList.size()==1)
                indexPosition = 0;
        }
        @Override
        public boolean hasNext() {
            return internalList.size()>1;
        }

        @Override
        public String next() {
            if(internalList.size()<1)
                throw new NoSuchElementException("No contiene elementos.");
            usadoNext = true;
            String val = internalList.get(indexPosition);
            if(hasNext()) {
                if (rebote && (indexPosition - 1) < 0)
                    rebote = false;

                if (!rebote && (indexPosition + 1) >= internalList.size())
                    rebote = true;

                indexPosition = rebote ? (indexPosition - 1) : (indexPosition + 1);
            }
            return val;
        }
    }
}
