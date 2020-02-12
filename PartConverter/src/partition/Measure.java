package partition;

import java.util.ArrayList;

/**
 * Représente une mesure de la partition.
 * number correspond au numéro de la mesure
 * mode correspond à la tonalité du morceau (majeur ou mineur)
 * timeBeats correspond au nombre de temps par mesure
 * beatType correspond à l'unité de temps
 * key correspond à la note de la clé
 */
public class Measure {
    private int number;
    private String mode;
    // Nombre de temps dans une mesure
    private int timeBeats;
    // Type de temps (binaire,ternaire...)
    private int beatType;
    private String key;
    private ArrayList<Note> notes;

    public Measure(int number) {
        this.number = number;
        notes=new ArrayList<>();
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
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getTimeBeats() {
        return timeBeats;
    }

    public void setTimeBeats(int timeBeats) {
        this.timeBeats = timeBeats;
    }

    public int getBeatType() {
        return beatType;
    }

    public void setBeatType(int beatType) {
        this.beatType = beatType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public void addNote(Note note){this.notes.add(note);}
}
