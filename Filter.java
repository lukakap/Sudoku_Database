public abstract class Filter {
    private Metropolis metropolis;

    Filter(Metropolis metropolis){
        this.metropolis = metropolis;
    }

    public Metropolis getMetropolis() {
        return metropolis;
    }

    abstract boolean filter(Metropolis metropolis);
}
