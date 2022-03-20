public class MatchFilter extends Filter{

    static final public int PARTIAL_MATCH = 0;
    static final public int EXACT_MATCH = 1;

    private int filterNum;
    private Metropolis toFilter;

    MatchFilter(Metropolis metropolis, int filterNum) {
        super(metropolis);
        this.filterNum = filterNum;
        toFilter = metropolis;
    }

    @Override
    boolean filter(Metropolis metropolis) {
        boolean continent = false;
        if(toFilter.getContinent().equals("")) {
            continent = true;
        } else {
            if(filterNum == PARTIAL_MATCH) continent = metropolis.getContinent().contains(toFilter.getContinent());
            if (filterNum == EXACT_MATCH) continent = metropolis.getContinent().equals(toFilter.getContinent());

//            switch (filterNum){
////                case PARTIAL_MATCH: continent = metropolis.getContinent().contains(toFilter.getContinent());
////                case PARTIAL_MATCH:  if(metropolis.getContinent().contains(toFilter.getContinent()))System.out.println("Sheicavs");
//                case PARTIAL_MATCH: boolHelper = metropolis.getContinent().contains(toFilter.getContinent());
//                case EXACT_MATCH: continent = metropolis.getContinent().equals(toFilter.getContinent());
//            }
        }

        boolean metropolisName = false;
        if(toFilter.getMetropolisName().equals("")){
            metropolisName = true;
        } else {

            if(filterNum == PARTIAL_MATCH) metropolisName =  metropolis.getMetropolisName().contains(toFilter.getMetropolisName());
            if (filterNum == EXACT_MATCH) metropolisName = metropolis.getMetropolisName().equals(toFilter.getMetropolisName());



//            switch (filterNum){
//                case PARTIAL_MATCH: metropolisName =  metropolis.getMetropolisName().contains(toFilter.getMetropolisName());
//                case EXACT_MATCH: metropolisName = metropolis.getMetropolisName().equals(super.getMetropolis().getMetropolisName());
//            }
        }
        return continent && metropolisName;
    }
}
