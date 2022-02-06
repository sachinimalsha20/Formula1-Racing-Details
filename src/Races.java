import java.util.Map;

public class Races {
    private String date;
    private Map<String, Integer> positions;

    Races(String date, Map<String, Integer> positions){
        this.date = date;
        this.positions = positions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Integer> getPositions() {
        return positions;
    }

    public void setPositions(Map<String, Integer> positions) {
        this.positions = positions;
    }
}
