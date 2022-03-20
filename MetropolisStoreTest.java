import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MetropolisStoreTest {

    private MetropolisStore store;

    @BeforeEach
    public void init(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");

        store = new MetropolisStore(dataSource);    //DAO
        MetropolisViewer view = new MetropolisViewer();
        MetropolisController controller = new MetropolisController(view, store);
        view.setButtonsListener(controller);
        store.setDataChangedListener(view);
    }

    @Test
    public void testSimple1(){
        List<Metropolis> metropolisList = store.getMetropolises();

        assertTrue(metropolisList.isEmpty());

        Metropolis metropolis1 = new Metropolis("Tbilisi","Europe","123456");
        Metropolis metropolis2 = new Metropolis("Kutaisi","East Europe","12345");
        store.addMetropolis(metropolis1);
        List<Metropolis> metropolisList2 = store.getMetropolises();
        assertEquals(1,metropolisList2.size());
        assertEquals("Tbilisi",metropolisList2.get(0).getMetropolisName());
        assertEquals("Europe",metropolisList2.get(0).getContinent());
        assertEquals(123456,metropolisList2.get(0).getPopulation());

        store.addMetropolis(metropolis2);
        List<Metropolis> metropolisList3 = store.getMetropolises();
        assertEquals(1,metropolisList3.size());
        assertEquals("Kutaisi",metropolisList3.get(0).getMetropolisName());
        assertEquals("East Europe",metropolisList3.get(0).getContinent());
        assertEquals(12345,metropolisList3.get(0).getPopulation());
    }

    //filter Test
    @Test
    public void testSimple2(){
        Metropolis metropolisForFilter = new Metropolis("aisi","Europe","200000");
        Filter filter1 = new PopulationFilter(metropolisForFilter,PopulationFilter.POPULATION_SMALLER_THAN);
        Filter filter2 = new MatchFilter(metropolisForFilter,MatchFilter.PARTIAL_MATCH);
        AndFilter andFilter = new AndFilter(metropolisForFilter);
        andFilter.addFilter(filter1);
        andFilter.addFilter(filter2);
        store.filter(andFilter);
        //after filter getMetropolises should return filtered array(filter uses setList Method from MetropolisStore). \
        //contains only Kutaisi Metropolis.

        List<Metropolis> metropolisList = store.getMetropolises();
        assertEquals(1,metropolisList.size());
        assertEquals("Kutaisi",metropolisList.get(0).getMetropolisName());
        assertEquals("East Europe", metropolisList.get(0).getContinent());
        assertEquals(12345, metropolisList.get(0).getPopulation());

        //after filter add
        store.addMetropolis(new Metropolis("Batumi","East Coast","250000"));
        List<Metropolis> metropolisList1 = store.getMetropolises();
        assertEquals(1,metropolisList1.size());
        assertEquals("Batumi",metropolisList1.get(0).getMetropolisName());
        assertEquals(250000,metropolisList1.get(0).getPopulation());
    }

    @Test
    public void testSimple3(){
        Metropolis metropolisForFilter = new Metropolis("Tbilisi","Europe","10000");
        Filter filter1 = new PopulationFilter(metropolisForFilter,PopulationFilter.POPULATION_LARGER_THAN);
        Filter filter2 = new MatchFilter(metropolisForFilter,MatchFilter.EXACT_MATCH);
        AndFilter andFilter = new AndFilter(metropolisForFilter);
        andFilter.addFilter(filter1);
        andFilter.addFilter(filter2);
        store.filter(andFilter);
        //contains only TBILISI

        List<Metropolis> metropolisList = store.getMetropolises();
        assertEquals(1,metropolisList.size());
        assertEquals("Tbilisi",metropolisList.get(0).getMetropolisName());
        assertEquals("Europe", metropolisList.get(0).getContinent());
    }
//
    @Test
    public void testSimple4(){
        Metropolis metropolisForFilter = new Metropolis("","East","");
        Filter filter1 = new PopulationFilter(metropolisForFilter,PopulationFilter.POPULATION_LARGER_THAN);
        Filter filter2 = new MatchFilter(metropolisForFilter,MatchFilter.PARTIAL_MATCH);
        AndFilter andFilter = new AndFilter(metropolisForFilter);
        andFilter.addFilter(filter1);
        andFilter.addFilter(filter2);
        store.filter(andFilter);
        //Kutaisi and Batumi

        List<Metropolis> metropolisList = store.getMetropolises();
        assertEquals(2,metropolisList.size());
        assertEquals("Kutaisi",metropolisList.get(0).getMetropolisName());
        assertEquals("East Coast", metropolisList.get(1).getContinent());

        //add new
        store.addMetropolis(new Metropolis("New York", "North America", "2500000"));
        List<Metropolis> metropolisList1 = store.getMetropolises();
        assertEquals(1,metropolisList1.size());
        assertEquals("New York",metropolisList1.get(0).getMetropolisName());
        assertEquals("North America",metropolisList1.get(0).getContinent());
    }
//
//
    @Test
    public void testSimple5(){
        Metropolis metropolisForFilter = new Metropolis("","","");
        Filter filter1 = new PopulationFilter(metropolisForFilter,PopulationFilter.POPULATION_LARGER_THAN);
        Filter filter2 = new MatchFilter(metropolisForFilter,MatchFilter.PARTIAL_MATCH);
        AndFilter andFilter = new AndFilter(metropolisForFilter);
        andFilter.addFilter(filter1);
        andFilter.addFilter(filter2);
        store.filter(andFilter);

        List<Metropolis> metropolisList = store.getMetropolises();
        assertEquals(4,metropolisList.size());
        assertEquals("Tbilisi",metropolisList.get(0).getMetropolisName());
        assertEquals("East Europe",metropolisList.get(1).getContinent());
        assertEquals(250000,metropolisList.get(2).getPopulation());
        assertEquals("New York",metropolisList.get(3).getMetropolisName());
    }

}