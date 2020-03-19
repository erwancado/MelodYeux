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
    public boolean changeRythm=false;

    public Measure(int number) {
        this.number = number;
        notes=new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder affichage= new StringBuilder("Mesure " + number);
        if(changeRythm)
            affichage.append(", chiffrage : "+timeBeats+" sur "+beatType);
        if(mode!=null)
            affichage.append(", "+mode).append("\n");
        else
            affichage.append("\n");
        for (Note n:notes) {
            affichage.append(n.toString());
        }
        affichage.append("\n");
        return affichage.toString();
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
