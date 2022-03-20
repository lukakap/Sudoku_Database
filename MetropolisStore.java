import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Same as Brain
public class MetropolisStore {
    private List<Metropolis> Metropolises;
    private dataChangedListener dataChangedListener;
    private BasicDataSource dataSource;
    private boolean baseChanged;

    public MetropolisStore(BasicDataSource dataSource){
        Metropolises = new ArrayList<>();
        this.dataSource = dataSource;
        baseChanged = false;
    }

    public void setList(List<Metropolis> Metropolises){
        this.Metropolises = Metropolises;
    }

    public void setDataChangedListener(dataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }

    public void addMetropolis(Metropolis metropolis){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("insert into metropolises values ('"+metropolis.getMetropolisName()+"', '" + metropolis.getContinent() + "', "+metropolis.getPopulation() +");");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Metropolis> list = new ArrayList<>();
        list.add(metropolis);
        dataChangedListener.alterTable(list);
        Metropolises = list;
    }

    public List<Metropolis> getMetropolises(){
        List<Metropolis> resultList = Metropolises;
        if(baseChanged) {
            resultList = new ArrayList<>();
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select * from metropolises");
                while (result.next()) {
                    resultList.add(convertToMetropolis(result));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Metropolises = resultList;
            baseChanged = false;
        }

        return resultList;
    }

    private Metropolis convertToMetropolis(ResultSet result) throws SQLException {
        Metropolis metropolis = new Metropolis();
        metropolis.setMetropolisName(result.getString("metropolis"));
        metropolis.setPopulation(result.getInt("population"));
        metropolis.setContinent(result.getString("continent"));
        return metropolis;
    }

    public void filter(Filter filter){
        List<Metropolis> result = new ArrayList<>();
        baseChanged = true;                          //filter everytime need to iterate on whole data
        List<Metropolis> list = getMetropolises();
        for(Metropolis metropolis: list){
            if(filter.filter(metropolis)) result.add(metropolis);
        }
        dataChangedListener.alterTable(result);
    }
}