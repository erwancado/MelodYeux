package partition;

public class Note {
    public String pitch;
    public int duration;
    public String rythm;
    public String articulation;
    public String dynamic;
    public int group;

    public Note() {

    }

    @Override
    public String toString() {
        String affichage = pitch+", "+rythm+", ";
        if(!articulation.equals(""))
            affichage+=articulation+", ";
        if(dynamic!=null)
            affichage+=dynamic+", ";
        return affichage;
    }
}
