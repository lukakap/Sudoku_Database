import javax.swing.*;
import java.util.List;

public class MetropolisController implements ButtonsListener{

    private MetropolisViewer view;
    private MetropolisStore store;

    public MetropolisController(MetropolisViewer view, MetropolisStore store){
        this.view = view;
        this.store = store;

        view.setTableModel(new MetropolisTableModel(store));
    }


    @Override
    public void addClickActionPerformed(Metropolis metropolis) {
        store.addMetropolis(metropolis);
    }

    @Override
    public void searchClickActionPerformed(Metropolis metropolis, int populationFilterIndex, int matchFilterIndex) {
        AndFilter andFilter = new AndFilter(metropolis);
        Filter populationFilter = new PopulationFilter(metropolis, populationFilterIndex);
        Filter matchFilter = new MatchFilter(metropolis, matchFilterIndex);
        andFilter.addFilter(populationFilter);
        andFilter.addFilter(matchFilter);
        store.filter(andFilter);
    }

}
