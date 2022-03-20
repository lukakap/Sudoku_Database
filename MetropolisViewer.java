import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MetropolisViewer extends JFrame implements dataChangedListener{

    private JFrame frame;
    //  private MetropolisStore store;
    private MetropolisTableModel tableModel;
    private ButtonsListener buttonsListener;

    private JTextField Metropolis;
    private JTextField Continent;
    private JTextField Population;

    private JComboBox<String> PopulationJCB;
    private JComboBox<String> MatchTypeJCB;

    private final String populationTextFieldNoFilterMember = "No Population Filter";
    private final String populationTextFieldGreaterThanMember = "Population Larger Than";
    private final String populationTextFieldSmallerThanMember = "Population Smaller Than";

    private JTable table;

    private final String matchTextFieldPartial = "Partial Match";
    private final String matchTextFieldExact = "Exact Match";

    public MetropolisViewer(){
        super("Metropolis Viewer");

//        this.store = store;

        frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        JPanel topPanel = new JPanel(new GridLayout(1,4));
        mainPanel.add(topPanel,BorderLayout.NORTH);
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));

        JPanel rightPanel = new JPanel();
        mainPanel.add(rightPanel,BorderLayout.EAST);
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(180,800));

        Metropolis = new JTextField();
        JLabel MetropolisLabel = new JLabel("Metropolis: ");
        Continent = new JTextField();
        JLabel ContinentLabel = new JLabel("Continent: ");
        Population = new JTextField();
        JLabel PopulationLabel = new JLabel("Population: ");

        JButton addNew = new JButton("Add");
        JButton search = new JButton("Search");

        addNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClickAction();
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchClickAction();
            }
        });

        topPanel.add(Box.createRigidArea(new Dimension(30,0)));
        topPanel.add(MetropolisLabel);
        topPanel.add(Metropolis);
        topPanel.add(Box.createRigidArea(new Dimension(5,0)));
        topPanel.add(ContinentLabel);
        topPanel.add(Continent);
        topPanel.add(Box.createRigidArea(new Dimension(5,0)));
        topPanel.add(PopulationLabel);
        topPanel.add(Population);
        topPanel.add(Box.createRigidArea(new Dimension(30,0)));

        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(addNew, BorderLayout.WEST);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(search, BorderLayout.WEST);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel searchOptionPanel = new JPanel();
        rightPanel.add(searchOptionPanel);
        searchOptionPanel.setLayout(new BoxLayout(searchOptionPanel,BoxLayout.Y_AXIS));

        String[] strings1 = {populationTextFieldNoFilterMember, populationTextFieldGreaterThanMember, populationTextFieldSmallerThanMember};
        PopulationJCB = new JComboBox<>(strings1);
        String[] strings2 = {"Partial Match","Exact Match"};
        MatchTypeJCB = new JComboBox<>(strings2);

        JPanel comboPanel = new JPanel();
        comboPanel.setBorder(new TitledBorder("Search Options"));
        comboPanel.add(PopulationJCB);
        comboPanel.add(MatchTypeJCB);
        searchOptionPanel.add(comboPanel,BorderLayout.NORTH);

        JPanel emptyPanel = new JPanel();
        searchOptionPanel.add(emptyPanel, BorderLayout.SOUTH);
        JPanel emptyPanel1 = new JPanel();
        searchOptionPanel.add(emptyPanel1, BorderLayout.SOUTH);


        table = new JTable();
        table.setPreferredSize(new Dimension(400,500));
        JPanel tablePanel = new JPanel(new BorderLayout());

        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
        table.getTableHeader().setPreferredSize(new Dimension(400,20));
        tablePanel.add(table,BorderLayout.CENTER);

        mainPanel.add(tablePanel);

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setTableModel(MetropolisTableModel tableModel){
        this.tableModel = tableModel;
        table.setModel(tableModel);
    }


    public void show(){
        frame.setVisible(true);
    }

    public void setButtonsListener(ButtonsListener buttonsListener){
        this.buttonsListener = buttonsListener;
    }

    @Override
    public void alterTable(List<Metropolis> metropolises){
        tableModel.setMetropolises(metropolises);
    }

    private void addClickAction(){
        Metropolis metropolis = createNewMetropolisObject();
        buttonsListener.addClickActionPerformed(metropolis);
    }

    private void searchClickAction(){
        Metropolis metropolisForFilter = createNewMetropolisObject();
        buttonsListener.searchClickActionPerformed(metropolisForFilter, PopulationJCB.getSelectedIndex(), MatchTypeJCB.getSelectedIndex());
    }


    private Metropolis createNewMetropolisObject(){
        String metropolis = Metropolis.getText();
        String continent = Continent.getText();
        String populationString = Population.getText();
        return new Metropolis(metropolis,continent,populationString);
    }

}
