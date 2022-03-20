public class PopulationFilter extends Filter {

    final static public int  NO_POPULATION_FILTER = 0;
    final static public int POPULATION_LARGER_THAN = 1;
    final static public int POPULATION_SMALLER_THAN = 2;

    private int filterNum;
    private Metropolis toFilter;

    PopulationFilter(Metropolis metropolis, int filterNum) {
        super(metropolis);
        this.filterNum = filterNum;
        toFilter = metropolis;
    }

    @Override
    boolean filter(Metropolis metropolis) {
        if(toFilter.getPopulation() == 0) return true;
        switch (filterNum) {
            case NO_POPULATION_FILTER : return true;
            case POPULATION_LARGER_THAN : return metropolis.getPopulation() > super.getMetropolis().getPopulation();
            case POPULATION_SMALLER_THAN : return metropolis.getPopulation() < super.getMetropolis().getPopulation();
            default:
                throw new IllegalStateException("Unexpected value: " + filterNum);
        }
    }
}
