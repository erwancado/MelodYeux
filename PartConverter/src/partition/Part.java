package partition;

import java.util.ArrayList;

public class Part {
    public int id;
    private ArrayList<Measure> measures;

    public Part(int id) {
        this.id = id;
        measures=new ArrayList<>();
    }
    public void addMeasure(Measure measure){
        measures.add(measure);
    }
    public Measure getMeasure(int number){
        return measures.get(number);
    }
}
