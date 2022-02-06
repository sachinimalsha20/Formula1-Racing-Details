import java.util.Map;

interface ChampionshipManager {
    void registerNewDriver(String driverName,String driverTeam,String driverLocation);
    void deleteDriver(String driverName);
    void changeDriverTeam(String driverName, String newDriverTeam);
    void displayVariousStatics(String driverName);
    void detailsTable();
    void compliedRaceUpdate(String date, Map<String,Integer>positions);
    void writeData();
    void restoreData();

    
}