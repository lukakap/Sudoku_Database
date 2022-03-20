public class Metropolis {
    private String Metropolis;
    private String Continent;
    private int Population;

    public Metropolis(){

    }

    public Metropolis(String Metropolis, String Continent, String Population){
        this.Population = 0;
        this.Metropolis = Metropolis;
        this.Continent = Continent;
        if(!Population.isEmpty()) this.Population = Integer.valueOf(Population);
    }

    public String getMetropolisName(){
        return Metropolis;
    }

    public String getContinent(){
        return Continent;
    }

    public int getPopulation(){
        return Population;
    }

    public void setMetropolisName(String Metropolis){
        this.Metropolis = Metropolis;
    }

    public void setContinent(String Continent){
        this.Continent = Continent;
    }

    public void setPopulation(String Population) {
        this.Population = 0;
        if(!Population.isEmpty()) this.Population = Integer.valueOf(Population);
    }

    public void setPopulation(int Population){
        this.Population = Population;
    }
}
