public class Formula1Driver extends Driver {
    private int firstPositions=0;
    private int secondPositions=0;
    private int thirdPositions=0;
    private int fullPoints=0;
    private int raceCount;

    //CONSTRUCTOR
    public Formula1Driver(String driverName,String driverTeam,String driverLocation){
        super(driverName,driverTeam,driverLocation);
    }


    @Override
    public void claculateDriverPoint(boolean raceFinished,int position){
        if(raceFinished){
            switch (position){
                case 1:
                    fullPoints+=25;
                    firstPositions++;
                    break;
                case 2:
                    fullPoints+=18;
                    secondPositions++;
                    break;
                case 3:
                    fullPoints+=15;
                    thirdPositions++;
                    break;
                case 4:
                    fullPoints+=12;
                    break;
                case 5:
                    fullPoints+=10;
                    break;
                case 6:
                    fullPoints+=8;
                    break;
                case 7:
                    fullPoints+=6;
                    break;
                case 8:
                    fullPoints+=4;
                    break;
                case 9:
                    fullPoints+=2;
                    break;
                case 10:
                    fullPoints+=1;
                    break;
                default:
                    System.out.println("POINTS ARE ONLY AWARDED TO FIRST 10 POSITIONS ");
            }
        }
        else {
            System.out.println("RACE NOT END YET");
        }
    }

    public int getFirstPositions(){
        return firstPositions;
    }
    public void setFirstPositions(int firstPositions){
        this.firstPositions=firstPositions;
    }

    public int getSecondPositions(){
        return secondPositions;
    }
    public void setSecondPositions(int secondPositions){
        this.secondPositions=secondPositions;
    }

    public int getThirdPositions(){
        return thirdPositions;
    }
    public void setThirdPositions(int thirdPositions){
        this.thirdPositions=thirdPositions;
    }

    public int getFullPoints(){
        return fullPoints;
    }
    public void setFullPoints(int fullPoints){
        this.fullPoints=fullPoints;
    }

    public int getRaceCount(){
        return raceCount;
    }
    public void setRaceCount(int raceCount){
        this.raceCount=raceCount;
    }
    
}
