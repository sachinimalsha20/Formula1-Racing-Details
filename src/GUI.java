import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GUI {
    JTable t1;
    DefaultTableModel listTableModel;
    protected int arraySize;

    // Constructor
    GUI(ArrayList<Formula1Driver> driver, ArrayList<Races> racesData) {
        //-----------------------------------------------------------------------
        JFrame frame = new JFrame("FORMULA 1 CHAMPIONSHIP");
        JLabel l1 = new JLabel(new ImageIcon("image/logo.jpg"));
        JButton b1 = new JButton(new ImageIcon("image/carWithFlag.jpg"));
        JLabel la1 = new JLabel("ALL DETAILS");
        JLabel la2 = new JLabel("ADD POSSIBILITY BASED RACE");
        JLabel la3 = new JLabel("GET NEW RACE");
        JLabel la4 = new JLabel("SEARCH DRIVERS");
        JButton exitGui=new JButton("EXIT FROM GUI");

        JButton b2 = new JButton(new ImageIcon("image/car.jpg"));
        JButton b3 = new JButton(new ImageIcon("image/driver.jpg"));
        JButton b4 = new JButton(new ImageIcon("image/lightCar.jpg"));


        l1.setBounds(7, 5, 757, 176);
        b1.setBounds(7, 265, 396, 252);
        la1.setBounds(35, 250, 336, 50);
        b2.setBounds(414, 195, 350, 232);
        la2.setBounds(35, 250, 336, 50);
        b3.setBounds(7, 533, 396, 160);
        exitGui.setBounds(7,195,389,60);

        la4.setBounds(35, 250, 336, 50);
        b4.setBounds(414, 441, 350, 252);

        Font btnIntroFont = new Font("Calibri", Font.BOLD, 2);
        Font newbtnIntroFont = btnIntroFont.deriveFont(30F);
        la1.setFont(newbtnIntroFont);
        la2.setFont(newbtnIntroFont);
        la3.setFont(newbtnIntroFont);
        la4.setFont(newbtnIntroFont);

        la1.setForeground(Color.MAGENTA);
        la2.setForeground(Color.CYAN);
        la3.setForeground(Color.white);
        la4.setForeground(Color.ORANGE);
        exitGui.setForeground(Color.white);

        exitGui.setBackground(Color.red);

        Font exitBtnFont =new Font("Calibri",Font.BOLD,5);
        Font newExitBtnFont=exitBtnFont.deriveFont(40F);
        exitGui.setFont(newExitBtnFont);

        frame.add(l1);
        frame.add(exitGui);
        b1.add(la1);
        b2.add(la2);
        b3.add(la3);
        b4.add(la4);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);

        frame.getContentPane().setBackground(Color.darkGray);

        frame.setSize(785, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //to exit gui without stop running the program
        exitGui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        //TO GO ALL DETAILS WINDOW
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                JFrame frame2 = new JFrame("FORMULA 1 RACE ALL DETAILS");
                JLabel l3 = new JLabel(new ImageIcon("image/logo.jpg"));
                JLabel l4 = new JLabel("FORMULA 01 2021");
                JButton homeBtn = new JButton(new ImageIcon("image/home.png"));
                JButton sortPointBtn = new JButton("ASCENDING BY POINTS");
                JButton sortFirstPBtn = new JButton("ASCENDING BY FIRST POSITIONS");

                frame2.getContentPane().setBackground(Color.darkGray);

                homeBtn.setBackground(Color.BLACK);
                sortFirstPBtn.setBackground(Color.BLACK);
                sortPointBtn.setBackground(Color.BLACK);

                arraySize = racesData.size();

                String[][] data = new String[driver.size()][7];

                String[] column = {"Name", "TEAM", "Location", "Points", "First Positions", "Second Positions",
                        "Third Positions"};

                for (int i = 0; i < driver.size(); i++) {

                    data[i][0] = driver.get(i).getDriverName();
                    data[i][1] = driver.get(i).getDriverTeam();
                    data[i][2] = driver.get(i).getDriverLocation();
                    data[i][3] = Integer.toString(driver.get(i).getFullPoints());
                    data[i][4] = Integer.toString(driver.get(i).getFirstPositions());
                    data[i][5] = Integer.toString(driver.get(i).getSecondPositions());
                    data[i][6] = Integer.toString(driver.get(i).getThirdPositions());
                }
                try {

                    //INITIALIZING THE JTABLE
                    listTableModel = new DefaultTableModel(data, column);
                    t1 = new JTable(listTableModel);

                    //SORT
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(t1.getModel());
                    t1.setRowSorter(sorter);
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                    frame2.setSize(785, 700);
                    l3.setBounds(7, 5, 757, 176);
                    l4.setBounds(7, 194, 757, 67);
                    t1.setBounds(7, 270, 757, 312);
                    homeBtn.setBounds(13, 600, 100, 50);
                    sortPointBtn.setBounds(134, 600, 250, 50);
                    sortFirstPBtn.setBounds(400, 600, 250, 50);

                    Font titleFont = new Font("Serif", Font.ITALIC | Font.BOLD, 20);
                    Font newTitleFont = titleFont.deriveFont(50F);
                    l4.setFont(newTitleFont);

                    frame2.getContentPane().setBackground(Color.darkGray);

                    frame2.add(l3);
                    frame2.add(l4);
                    frame2.add(t1);
                    frame2.add(homeBtn);
                    frame2.add(sortPointBtn);
                    frame2.add(sortFirstPBtn);

                    frame2.setLayout(null);
                    frame2.setVisible(true);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.setLocationRelativeTo(null);


                    //TO GO BACK HOME PAGE FROM FORMULA 1 RACE
                    homeBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame2.setVisible(false);
                            frame.setVisible(true);
                        }
                    });

                    //TO SORT DATA BY POINTS
                    sortPointBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t1.getRowSorter().toggleSortOrder(3);
                        }
                    });

                    //TO SORT BY DETAILS BY FIRST POSITIONS
                    sortFirstPBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            t1.getRowSorter().toggleSortOrder(4);
                        }
                    });
                }
                catch (Exception e1){
                    System.out.println("OOPS! SORRY WE ARE DEVELOPING OUR PAGE PLEASE COME BACK LATTER");
                    System.out.print("ENTER CODE: ");
                }
            }
        });


        //-----------------------------------------------------------------------
        //TO HAVE A POSSIBILITY BASED POSITIONS FOR A RACE ANT POINTS
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //RANDOM POSITIONS
                    double possibility[] = {0.4, 0.3, 0.1, 0.1, 0.02, 0.02, 0.02, 0.02, 0.02};
                    ArrayList<Integer> positions = toAddRandomPositions(possibility, driver.size());

                    // Current date
                    LocalDate date = LocalDate.now();

                    // Calculate points for race
                    for (int i = 0; i < driver.size(); i++) {
                        driver.get(i).claculateDriverPoint(true, positions.get(i));
                    }

                    // DISPLAY TABLE
                    String[] columns = {"Driver", "Position"};
                    String[][] raceData = new String[driver.size()][2];

                    Map<String, Integer> posMap = new HashMap<>();

                    //TO ADD DETAILS TO TABLE
                    for (int i = 0; i < driver.size(); i++) {

                        raceData[i][0] = driver.get(i).getDriverName();
                        raceData[i][1] = Integer.toString(positions.get(i));

                        // Save in map
                        posMap.put(driver.get(i).getDriverName(), positions.get(i));
                        GUI.this.arraySize++;

                    }

                    //ADD OBJECT TO ARRAYLIST
                    Races race = new Races(date.toString(), posMap);
                    racesData.add(race);


                    listTableModel = new DefaultTableModel(raceData, columns);
                    t1.setModel(listTableModel);
                }
                catch (Exception e1){
                    System.out.println("SORRY! No PLAYER REGISTERED REGISTERED FOR FORMULA 01");
                    System.out.print("ENTER CODE: ");
                }

            }

        });
        //NEW RACE
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //RANDOM POSITIONS
                    ArrayList<Integer> positions = haveRandomPositionsToNewRace(driver.size());

                    //TO GET TODAY DATE
                    LocalDate date = LocalDate.now();

                    // CALCULATE POINTS FOR THE RACE
                    for (int i = 0; i < driver.size(); i++) {
                        driver.get(i).claculateDriverPoint(true, positions.get(i));
                    }

                    String[] columns = {"Driver", "Position"};
                    String[][] raceData = new String[driver.size()][2];

                    Map<String, Integer> posMap = new HashMap<>();


                    for (int i = 0; i < driver.size(); i++) {

                        raceData[i][0] = driver.get(i).getDriverName();
                        raceData[i][1] = Integer.toString(positions.get(i));

                        //SAVE IN MAP
                        posMap.put(driver.get(i).getDriverName(), positions.get(i));
                        GUI.this.arraySize++;

                    }

                    //ADD OBJECT TO NEW
                    Races race = new Races(date.toString(), posMap);
                    racesData.add(race);

                    //ADD DATA TO THE DEFAULTTABLE
                    listTableModel = new DefaultTableModel(raceData, columns);
                    t1.setModel(listTableModel);

                }
                catch (Exception e1){
                    System.out.println("SORRY! No PLAYER REGISTERED REGISTERED FOR FORMULA 01");
                    System.out.print("ENTER CODE: ");
                }
            }
        });
        //TO GET SEARCH RACE DETAILS
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    String[] columns = {"Date", "Driver", "Position"};

                    int x = 0;
                    String[][] data = new String[arraySize][3];

                    for (int i = 0; i < racesData.size(); i++) {
                        String date = racesData.get(i).getDate();

                        Map<String, Integer> racePosMap = racesData.get(i).getPositions();

                        for (String driverN : racePosMap.keySet()) {
                            data[x][0] = date;
                            data[x][1] = driverN;
                            data[x][2] = Integer.toString(racePosMap.get(driverN));
                            x++;
                        }
                    }

                    //NEW WINDOW AS ALL RACE DETAILS
                    JTextField textField = new JTextField();
                    textField.setPreferredSize(new Dimension(200, 20));
                    JButton searchBtn = new JButton("SEARCH");
                    JFrame frame3 = new JFrame("ALL RACE DETAILS");
                    JLabel l5 = new JLabel(new ImageIcon("image/logo.jpg"));
                    JButton homeBtn = new JButton(new ImageIcon("image/home.png"));
                    JTable t2 = new JTable();
                    l5.setBounds(7, 5, 757, 176);

                    homeBtn.setBackground(Color.BLACK);
                    searchBtn.setBackground(Color.DARK_GRAY);

                    listTableModel = new DefaultTableModel(data, columns);
                    t2 = new JTable();
                    t2.setModel(listTableModel);

                    t2.setBounds(30, 60, 200, 300);
                    homeBtn.setBounds(13, 600, 130, 30);
                    JScrollPane sp = new JScrollPane(t2);
                    JPanel panel = new JPanel();
                    JPanel panel2 = new JPanel();

                    frame3.add(sp);
                    panel.add(l5);
                    panel2.add(homeBtn);
                    panel2.add(textField);
                    panel2.add(searchBtn);
                    frame3.add(panel, BorderLayout.NORTH);
                    frame3.add(panel2, BorderLayout.SOUTH);
                    frame3.setSize(780, 700);
                    frame3.setVisible(true);
                    frame3.setLocationRelativeTo(null);

                    // TO FILTER DATA BY DRIVER NAME
                    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(t2.getModel());
                    t2.setRowSorter(rowSorter);

                    //SORT BY DATE
                    t2.getRowSorter().toggleSortOrder(0);

                    //TO SEARCH BUTTON
                    searchBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String text = textField.getText();

                            if (text.length() == 0) {
                                rowSorter.setRowFilter(null);
                            } else {
                                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                            }
                        }

                    });

                    //TO GO BACK TO THE HOME FORM SORT ALL RACE DETAILS WINDOW
                    homeBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame3.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
                }
                catch (Exception e1){
                    System.out.println("OOPS! SORRY WE ARE DEVELOPING OUR PAGE PLEASE COME BACK LATTER");
                    System.out.print("ENTER CODE: ");
                }
            }
        });
    }

    //-------------------------------------------------------
    private ArrayList haveRandomPositionsToNewRace(int setSize) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < setSize + 1; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list;
    }


    private ArrayList toAddRandomPositions(double[] possibility, int size) {
        //REFERENCE: Random (Java Platform SE 7 ) (2021). Docs.oracle.com. Available from https://docs.oracle.com/javase/7/docs/api/java/util/Random.html [Accessed 14 December 2021].

        ArrayList<Integer> newInitial = haveRandomPositionsToNewRace(size);

        Object[] startingNew = newInitial.toArray();

        // RANDOM POSITION
        Random randomPosition = new Random();
        double positions = randomPosition.nextDouble();
        double sum = 0.0;
        int i = 0;
        int indexNo = Arrays.asList(possibility).indexOf(null);
        possibility = Arrays.copyOfRange(possibility, 0, size);

        while (sum < positions && i < possibility.length) {
            sum += possibility[i];
            i++;
        }

        int raceWinner = (int) startingNew[i - 1];

        //CHANGE FIRST POSITIONS
        int x = 0, y = 0;
        for (int j = 0; j < newInitial.size(); j++) {

            if ((int) newInitial.get(j) == 1) {
                x = j;
            }
            if ((int) newInitial.get(j) == raceWinner) {
                y = j;
            }
        }

        //Java, E. and Crowder, T. (2021). Efficient swapping of elements of an array in Java. Stack Overflow. Available from https://stackoverflow.com/questions/13766209/efficient-swapping-of-elements-of-an-array-in-java [Accessed 14 December 2021].

        //SWAP POSITIONS
        newInitial.set(y, 1);
        newInitial.set(x, raceWinner);

        newInitial.remove(y);

        //SHUFFLE AGAIN
        Collections.shuffle(newInitial);

        //ADD FIRST POSITION AGAIN
        newInitial.add(y, 1);
        return newInitial;
    }
}