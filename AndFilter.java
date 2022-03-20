import java.util.ArrayList;
import java.util.List;

public class AndFilter extends Filter{

    private List<Filter> filters;


    public AndFilter(Metropolis metropolis) {
        super(metropolis);
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    @Override
    boolean filter(Metropolis metropolis) {
        for (Filter filter: filters){
            if(!filter.filter(metropolis))
                return false;
        }
        return true;
    }
}
