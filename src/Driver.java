public abstract class Driver {
    private String driverName;
    private String driverTeam;
    private String driverLocation;

    //Constructor
    public Driver(String driverName,String driverTeam,String driverLocation){
        this.driverName=driverName;
        this.driverTeam=driverTeam;
        this.driverLocation=driverLocation;
    }
    public Driver(){};

    //Calculate points
    public abstract void claculateDriverPoint(boolean raceFinished,int position);

    public String getDriverName(){
        return driverName;
    }
    public void setDriverName(String driverName){
        this.driverName=driverName;
    }

    public String getDriverTeam(){
        return driverTeam;
    }
    public void setDriverTeam(String driverTeam){
        this.driverTeam=driverTeam;
    }

    public String getDriverLocation(){
        return driverLocation;
    }
    public void setDriverLocation(String driverLocation){
        this.driverLocation=driverLocation;
    }
}
