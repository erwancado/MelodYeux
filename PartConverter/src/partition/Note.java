package partition;

public class Note {
    private char name;
    private int alteration;
    private int octave;
    private int duration;
    private String type;

    public Note(char name, int alteration, int octave, int duration, String type) {
        this.name = name;
        this.alteration = alteration;
        this.octave = octave;
        this.duration = duration;
        this.type = type;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getAlteration() {
        return alteration;
    }

    public void setAlteration(int alteration) {
        this.alteration = alteration;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
