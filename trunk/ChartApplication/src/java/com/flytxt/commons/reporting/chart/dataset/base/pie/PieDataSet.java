package com.flytxt.commons.reporting.chart.dataset.base.pie;

import com.flytxt.commons.reporting.chart.dataset.base.DataSet; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PieDataSet  extends DataSet implements Iterable<PieSet>{

    private Collection<PieSet> set = null;
    public PieDataSet () {
        set =  new ArrayList<PieSet>();

    }

    public PieSet addPiece (String label, String value) {

        PieSet ps =  new PieSet();
        ps.setLabel(label);
        ps.setValue(value);
        set.add(ps);
        return ps;
    }

    public Iterator<PieSet> iterator() {
       return set.iterator();
    }

}

