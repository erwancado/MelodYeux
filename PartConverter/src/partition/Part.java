package partition;

import java.util.ArrayList;

public class Part {
    public int id;
    private ArrayList<Measure> measures;
    // Nombre de temps dans une mesure
    private int timeBeats;
    // Type de temps (binaire,ternaire...)
    private int beatType;
    private ArrayList<String> keys;

    public Part(int id) {
        this.id = id;
        measures=new ArrayList<>();
    }
    public void addMeasure(Measure measure){
        if(measures.isEmpty()){
            keys=measure.getKeys();
            timeBeats=measure.getTimeBeats();
            beatType=measure.getBeatType();
        }
        else {
            if(measure.getTimeBeats()==0){
                measure.setTimeBeats(timeBeats);
                measure.setBeatType(beatType);
            }
            else {
                measure.changeRythm=true;
            }
        }
        measures.add(measure);
        measure.setPart(this);
    }
    public Measure getMeasure(int number){
        return measures.get(number);
    }

    @Override
    public String toString() {
        StringBuilder affichage= new StringBuilder("Partition, "+"nombre de mesures "+measures.size());
        if(keys.size()>1){
            for (int i=0;i<keys.size();i++){
                affichage.append(", clé ").append(i+1).append(" :").append(keys.get(i));
            }
        }
        else{
            affichage.append(", clé :").append(keys.get(0));
        }
        affichage.append(", chiffrage : ").append(timeBeats).append(" sur ").append(beatType).append("\n");
        for (Measure m:
             measures) {
            affichage.append(m.toString());
        }
        affichage.append("Fin de la partition");
        return affichage.toString();
    }
    public String GetRythm(int noteDuration)
    {
        int denominator = timeBeats;
        int numerator = noteDuration;
        int divisor = gcd(denominator,numerator);
        if(divisor!=1){
            numerator=euclidianDivision(numerator,divisor);
            denominator=euclidianDivision(denominator,divisor);
        }
        return denominator+" sur "+numerator;
    }
    public String GetRythm(int noteDuration, int beats)
    {
        int denominator = beats;
        int numerator = noteDuration;
        int divisor = gcd(denominator,numerator);
        if(divisor!=1){
            numerator=euclidianDivision(numerator,divisor);
            denominator=euclidianDivision(denominator,divisor);
        }
        if(denominator==numerator)
            return 1+"";
        else
            return denominator+" sur "+numerator;
    }
    private int gcd(int a,int b){
        while (b > 0)
        {
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
    private int euclidianDivision(int a, int b)
    {
        int remainder=a, quotient=0;
        while (remainder >= b)
        {
            remainder = remainder - b;
            quotient++;
        }
        return (quotient);
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
}
