import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {
    int numOfDrivers; //TO COUNT ALL REGISTERED DRIVERS
    ArrayList<Formula1Driver>driver=new ArrayList<>();
    ArrayList<String>driverTeams=new ArrayList<>();
    ArrayList<Races>racesData=new ArrayList<Races>();


    @Override
    public void restoreData() {
        // READ DRIVER DATA FILE
        try {
            File file = new File("driversDetails.txt");
            Scanner input = new Scanner(file);
            int count = 0;

            //SAVE TO ARRAYLIST
            while (input.hasNextLine()) {
                String data = input.nextLine();
                String info[] = data.split("\t", -1); // split from tab spaces

                Formula1Driver fdriver = new Formula1Driver(info[0], info[1], info[2]);

                fdriver.setFullPoints(Integer.parseInt(info[3]));
                fdriver.setFirstPositions(Integer.parseInt(info[4]));
                fdriver.setSecondPositions(Integer.parseInt(info[5]));
                fdriver.setThirdPositions(Integer.parseInt(info[6]));

                driver.add(fdriver);
                count++;
            }
            System.out.println("DRIVER'S DETAILS DATA LOADED FILE");
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SAVED PREVIOUS DATA");
        }

        // READ RACE DETAILS FILE
        try {
            File file = new File("racesDetails.txt");
            Scanner input = new Scanner(file);
            int count = 0;
            while (input.hasNextLine()) {
                String data = input.nextLine();
                String infomationArray[] = data.split("\t", -1); // split from tab spaces

                Map<String, Integer> position = new HashMap<>();

                // SAVE RACE DETAILS
                position.put(infomationArray[1], Integer.parseInt(infomationArray[2]));

                Races r1 = new Races(infomationArray[0], position);
                if (r1.getDate() != "")
                    racesData.add(r1); // Add object to arrayList
                count++;
            }
            System.out.println("RACES DETAILS DATA LOADED FROM FILE\n");
            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("NO SAVED DATA\n");
        }
    }

    @Override
    public void writeData() {
        // WRITE DRIVER DATA TO FILE
        try {
            FileWriter file = new FileWriter("driversDetails.txt");

            //DATA  WRITE TO FILE
            for (Formula1Driver d : driver) {
                file.write(d.getDriverName() + "\t" + d.getDriverTeam() + "\t" + d.getDriverLocation() + "\t" + d.getFullPoints() + "\t"
                        + d.getFirstPositions() + "\t" + d.getSecondPositions() + "\t" + d.getThirdPositions() + "\n");
            }

            file.close();
            System.out.println("SUCCESSFULLY WROTE DRIVER DETAILS TO FILE");
        } catch (IOException e) {
            System.out.println("AN ERROR OCCURRED");
            e.printStackTrace();
        }

        // WRITE RACE DETAILS
        try {
            FileWriter file = new FileWriter("racesDetails.txt");

            int x = 0;
            for (int i = 0; i < racesData.size(); i++) {
                String date = racesData.get(i).getDate();
                Map<String, Integer> mp = racesData.get(i).getPositions();

                //DATA
                for (String driver : mp.keySet()) {
                    file.write(date + "\t" + driver + "\t" + mp.get(driver) + "\n");
                    x++;
                }
            }

            file.close();
            System.out.println("SUCCESSFULLY WROTE RACES DETAILS TO FILE");

        } catch (IOException e) {
            System.out.println("AN ERROR OCCURRED");
            e.printStackTrace();
        }
    }



    @Override
    public void compliedRaceUpdate(String date, Map<String, Integer> positions) {
        //Java Iterator (2021). W3schools.com. Available from https://www.w3schools.com/java/java_iterator.asp [Accessed 14 December 2021].
        Iterator it = positions.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            //UPDATE STATISTICS
            for (Formula1Driver d : driver) {
                if (d.getDriverName().equals(pair.getKey())) {
                    d.claculateDriverPoint(true, (int) pair.getValue()); // Set positions
                }
            }
        }
    }




    @Override
    public void detailsTable() {
        Formula1Driver details[]=new Formula1Driver[driver.size()];
        //ADD DATA TO ARRAY
        for(int i=0;i<driver.size();i++){
            details[i]=driver.get(i);
        }

        // SORT ACCORDING TO DESCENDING ORDER
        for (int i = 0; i < driver.size(); i++) {
            for (int j = i + 1; j < driver.size(); j++) {
                Formula1Driver sortDetails;


                if (details[i].getFullPoints() < details[j].getFullPoints()) {
                    sortDetails = details[i];
                    details[i] = details[j];
                    details[j] = sortDetails;
                }
                // IF THERE IS SAME POINTS TO TWO OR MORE DRIVERS
                else if (details[i].getFullPoints() == details[j].getFullPoints()) {
                    if (details[i].getFirstPositions() > details[j].getFirstPositions()) {
                        sortDetails = details[j];
                        details[j] = details[i];
                        details[i] = sortDetails;
                    } else {
                        sortDetails = details[i];
                        details[i] = details[j];
                        details[j] = sortDetails;
                    }
                }
            }
        }

        System.out.println("\n\n");
        System.out.println("_____________________________________________________________________________________________________________________");
        System.out.println("DRIVER'S NAME          DRIVER'S TEAM          DRIVER'S LOCATION          DRIVER'S POINTS           POSITIONS");
        System.out.println("                                                                                                1ST    2ND    3RD");
        System.out.println("_____________________________________________________________________________________________________________________");

        for (int i = 0; i < driver.size(); i++) {
            System.out.println(details[i].getDriverName() + "\t\t\t\t\t\t" + details[i].getDriverTeam() +
                    "\t\t\t\t\t\t" + details[i].getDriverLocation()+"\t\t\t\t\t\t" +details[i].getFullPoints()+"\t\t\t"+details[i].getFirstPositions()+
                    "\t\t"+details[i].getSecondPositions()+"\t\t"+details[i].getThirdPositions());
        }
        System.out.println("_____________________________________________________________________________________________________________________\n");
    }




    @Override
    public void displayVariousStatics(String driverName) {
        boolean hasDriver=false;
        for(Formula1Driver d:driver){
            if(d.getDriverName().equalsIgnoreCase(driverName)){
                hasDriver=true;
                System.out.println("DRIVER'S TEAM             : "+d.getDriverTeam());
                System.out.println("DRIVER'S LOCATION         : "+d.getDriverLocation());
                System.out.println("DRIVER;S FIRST POSITIONS  : "+d.getFirstPositions());
                System.out.println("DRIVER'S SECOND POSITIONS : "+d.getSecondPositions());
                System.out.println("DRIVER'S THIRD POSITIONS  : "+d.getThirdPositions());
                System.out.println("DRIVER'S FULL POINTS      : "+d.getFullPoints());
            }
        }
        if(hasDriver==false){
            System.out.println("DRIVER DOES NOT REGISTERED IN FORMULA 1 RACE");
        }
    }


    @Override
    public void changeDriverTeam(String driverName, String newDriverTeam) {
        boolean hasDriver=false;
        for(Formula1Driver d:driver){
            if(d.getDriverName().equalsIgnoreCase(driverName)){
                hasDriver=true;
                d.setDriverTeam(newDriverTeam);
                System.out.println("SUCCESSFULLY CHANGE DRIVER'S TEAM IN FORMULA1 RACE");
            }
        }
        if(hasDriver==false){
            System.out.println("DRIVER DOES NOT REGISTERED IN FORMULA 1 RACE");
        }
    }



    @Override
    public void deleteDriver(String driverName) {
        boolean hasDriver=false;
        for(Formula1Driver d:driver){
            if(d.getDriverName().equalsIgnoreCase(driverName)){
                hasDriver = true;
                driverTeams.remove(d.getDriverTeam());//TO REMOVE DRIVER TEAM
                driver.remove(d);//TO REMOVE DRIVER ALL DETAILS
                System.out.println("SUCCESSFULLY REMOVED DRIVER FORM FORMULA1 RACE");
                numOfDrivers--;
                break;
            }
        }
        if(hasDriver==false){
            System.out.println("DRIVER DOES NOT REGISTERED IN FORMULA 1 RACE");
        }
    }

    @Override
    public void registerNewDriver(String driverName, String driverTeam, String driverLocation) {
        boolean hasDriver = false;
        for (String t : driverTeams) {
            if (t.equalsIgnoreCase(driverTeam)) {
                System.out.println("SORRY!\nTEAM IS ALREADY EXISTS...");
                hasDriver = true;
                break;
            }
        }

        if(hasDriver==false){
            Formula1Driver fdriver=new Formula1Driver(driverName,driverTeam,driverLocation);
            driver.add(fdriver);
            numOfDrivers=driver.size();
            System.out.println("CONGRATULATIONS!\nDRIVER SUCCESSFULLY REGISTERED TO THE RACE");
        }


    }

    public static void main(String[] args) {


        Formula1ChampionshipManager f1=new Formula1ChampionshipManager();

        f1.restoreData();

        Scanner input=new Scanner(System.in);
         while (true){
             System.out.println("\n---------------------------------------------------\n"
                     + "TO GET THE  NUMBER OF ALL DRIVERS       : 'C001'\n"
                     + "TO ADD NEW DRIVER ENTER CODE            : 'C002'\n"
                     + "TO REMOVE A DRIVER ENTER CODE           : 'C003'\n"
                     + "TO CHANGE DRIVER'S TEAM ENTER CODE      : 'C004'\n"
                     + "TO GET VARIOUS STATISTICS               : 'C005'\n"
                     + "TO GET ALL COMPETING DRIVERS DETAILS    : 'C006'\n"
                     + "TO GET ABOUT COMPLETED RACES            : 'C007'\n"
                     + "To SAVE DATA AND EXIT                   : 'C008'\n"
                     + "TO GET GUI                              : 'C009'\n"
                     + "----------------------------------------------------");
             String code;
             System.out.print("ENTER CODE : ");
             code=input.next();

             if(code.equalsIgnoreCase("c001")){
                 System.out.println("NUMBER OF ALL REGISTERED DRIVER : "+f1.numOfDrivers);
             }
             if(code.equalsIgnoreCase("c002")){
                 System.out.println("WELCOME\nREGISTER DRIVER TO FORMULA 1 RACE");
                 System.out.print("ENTER DRIVER'S NAME : ");
                 String name=input.next();
                 System.out.print("ENTER DRIVER'S TEAM : ");
                 String team=input.next();
                 System.out.print("ENTER DRIVER'S LOCATION : ");
                 String location=input.next();

                 f1.registerNewDriver(name,team,location);
             }
             if(code.equalsIgnoreCase("c003")){
                 System.out.println("DELETE DRIVER FORM FORMULA 1 RACE");
                 System.out.print("ENTER DRIVER'S NAME ");
                 String driverName=input.next();

                 f1.deleteDriver(driverName);
             }
             if (code.equalsIgnoreCase("c004")){
                 System.out.println("CHANGE DRIVER'S TEAM");
                 System.out.print("ENTER DRIVER'S NAME : ");
                 String driverName=input.next();
                 System.out.print("ENTER DRIVER'S NEW TEAM :");
                 String newDriverTeam=input.next();

                 f1.changeDriverTeam(driverName,newDriverTeam);
             }
             if(code.equalsIgnoreCase("c005")){
                 System.out.println("VARIOUS STATISTICS");
                 System.out.print("ENTER DRIVER'S NAME : ");
                 String driverName=input.next();

                 f1.displayVariousStatics(driverName);
             }
             if (code.equalsIgnoreCase("c006")){
                 System.out.println("FORMULA 01 DRIVERS DETAILS");
                 f1.detailsTable();
             }
             if(code.equalsIgnoreCase("c007")){
                 System.out.println("COMPLETED RACE DATA UPDATE");
                 System.out.print("ENTER RACE DATA (yyyy-mm-dd) : ");
                 String date=input.next();

                 Map<String, Integer> positions = new HashMap<>();

                 //GET POSITION TO EXIT AFTER ENTER ALL DRIVER
                 System.out.println("ENTER -1 TO EXIT");

                 for (int i = 0; i < f1.driver.size(); i++) {

                     System.out.print("ENTER DRIVER'S NAME : ");
                     String driverName = input.next();

                     if (driverName.equals("-1"))
                         break;

                     System.out.print("ENTER DRIVER'S POSITION : ");
                     int position = input.nextInt();

                     positions.put(driverName, position);
                 }

                 f1.racesData.add(new Races(date, positions));
                 f1.compliedRaceUpdate(date, positions);
             }

             if (code.equalsIgnoreCase("c008")){
                 f1.writeData();
                 System.out.println("SUCCESSFULLY SAVED DATA\n");
                 break;
             }
             if(code.equalsIgnoreCase("c009")){
                 GUI gui=new GUI(f1.driver,f1.racesData);
             }
         }
    }
}
