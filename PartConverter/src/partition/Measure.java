package partition;

/**
 * Représente une mesure de la partition.
 * number correspond au numéro de la mesure
 * mode correspond à la tonalité du morceau (majeur ou mineur)
 * timeBeats correspond au nombre de temps par mesure
 * beatType correspond à l'unité de temps
 * key correspond à la note de la clé
 * keyLine correspond à la ligne sur laquelle est la clé
 */
public class Measure {
    private int number;
    private String mode;
    private int timeBeats;
    private int beatType;
    private char key;
    private int keyLine;

    public Measure(int number, String mode, int timeBeats, int beatType, char key, int keyLine) {
        this.number = number;
        this.mode = mode;
        this.timeBeats = timeBeats;
        this.beatType = beatType;
        this.key = key;
        this.keyLine = keyLine;
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

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public int getKeyLine() {
        return keyLine;
    }

    public void setKeyLine(int keyLine) {
        this.keyLine = keyLine;
    }
}
