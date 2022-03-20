import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/MetropolisBase");
        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");


        MetropolisStore store = new MetropolisStore(dataSource);
        MetropolisViewer view = new MetropolisViewer();
        MetropolisController controller = new MetropolisController(view, store);
        view.setButtonsListener(controller);
        store.setDataChangedListener(view);
        view.show();
    }
}
