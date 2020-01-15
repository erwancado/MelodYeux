package converter;

public class MusicXMLdatastored {
    public String pitchText;
    public int dur;
    public int articulation;
    public int dynamic;
    public int pitch;
    public String getPitchText() {
        return pitchText;
    }
    public void setPitchText(String pitchText) {
        this.pitchText = pitchText;
    }
    public int getDur() {
        return dur;
    }
    public void setDur(int dur) {
        this.dur = dur;
    }
    public int getPitch() {
        return pitch;
    }
    public void setPitch(int pitch) {
        this.pitch = pitch;
    }
    public MusicXMLdatastored() {}
    public int getArticulation() {
        return articulation;
    }
    public void setArticulation(int articulation) {
        this.articulation = articulation;
    }
    public int getDynamic() {
        return dynamic;
    }
    public void setDynamic(int dynamic) {
        this.dynamic = dynamic;
    }

}
