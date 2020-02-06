package converter;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import partition.Measure;
import partition.Note;
import partition.Part;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MusicXMLParser extends DefaultHandler{
    private static boolean getChars = false;
    // Manière dont est jouée la note
    private static boolean isArticulation = false;
    // Mode de la mesure (majeur ou mineur)
    private static boolean isMode= false;
    // Clé de la partition
    private static boolean isKey = false;
    // Temps
    private static boolean isBeats = false;
    // Unité de temps
    private static boolean isBeatsType = false;
    // Durée de la note
    private static int isDur = 0;
    // Nom de la note
    private static boolean isStep = false;
    // Octave de la note
    private static boolean isOct = false;
    // Altération de la note
    private static boolean isAlter = false;
    // Nuance de la note
    private static boolean isDynamic;
    private String step = "";
    private String dur = "";
    private String oct = "";
    private String alter = "";
    private int articulation;
    private String dynamic;
    private int nbMeasures=0;
    private Part part;
    private static Measure measure;
    private MusicXMLParser(int partId) {
        super();
        this.part=new Part(partId);
    }
    public void startElement( String namespaceURI, String localName,
                              String qName, Attributes atts ) {
        if( localName.equals( "attributes" ) ) {
            nbMeasures++;
            measure=new Measure(nbMeasures);
        }
        if(localName.equals("sign")) isKey=true;
        if(localName.equals("mode")) isMode=true;
        if( localName.equals( "beats" ) ) isBeats = true;
        if( localName.equals( "beat-type" ) ) isBeatsType = true;
        if( MusicXMLParser.isDynamic ) {
            if( localName.equals( "p" ) ) this.dynamic = "piano";
            if( localName.equals( "pp" ) ) this.dynamic = "pianissimo";
            if( localName.equals( "ppp" ) ) this.dynamic = "pianississimo";
            if( localName.equals( "f" ) ) this.dynamic = "forte";
            if( localName.equals( "ff" ) ) this.dynamic = "fortissimo";
            if( localName.equals( "fff" ) ) this.dynamic = "fortississimo";
            if( localName.equals( "mp" ) ) this.dynamic = "mezzo piano";
            if( localName.equals( "mf" ) ) this.dynamic = "mezzo forte";
            if( localName.equals( "sf" ) ) this.dynamic = "subito forte";
            if( localName.equals( "sfz" ) ) this.dynamic = "sforzando";
            if( localName.equals( "sfp" ) ) this.dynamic = "sforzando piano";
            if( localName.equals( "sz" ) ) this.dynamic = "forzando";
        }
        if( MusicXMLParser.isArticulation ) {
            if( localName.equals( "accent" ) ) this.articulation = 1;
            if( localName.equals( "strong-accent" ) ) this.articulation
                    = 2;
            if( localName.equals( "staccato" ) ) this.articulation = 3;
            if( localName.equals( "staccatissimo" ) )
                this.articulation = 4;
        }
        if( isDur == 1 ) {
            if( localName.equals( "duration" ) ) {
                isDur = 2;
            }
        }
            if( localName.equals( "step" ) ) isStep = true;
            if( localName.equals( "alter" ) ) isAlter = true;
            if( localName.equals( "octave" ) ) isOct = true;

    }
    public void endElement( String namespaceURI, String localName,
                            String qName ) {
        if( getChars ) getChars = false;
        if(localName.equals("measure")){
            part.addMeasure(measure);
        }
        if(localName.equals("sign")) isKey=false;
        if(localName.equals("mode")) isMode=false;
        if( localName.equals( "beats" ) ) isBeats = false;
        if( localName.equals( "beat-type" ) ) isBeatsType = false;
        if( localName.equals( "duration" ) && isDur == 2 ) isDur = 1;
        if( localName.equals( "step" ) ) isStep = false;
        if( localName.equals( "alter" ) ) isAlter = false;
        if( localName.equals( "octave" ) ) isOct = false;
        if( localName.equals( "note" ) ) {
            Note store = new Note();
            store.dynamic=this.dynamic;
            store.articulation=this.articulation;
            if( this.dur.length() > 0 ) {
                store.duration=Integer.parseInt( this.dur );
            }
                if( alter.length() > 0 ) {
                    if( Integer.parseInt( alter ) == 1 )
                        store.pitch=step + "dièse" + oct ;
                    else store.pitch=( step + "bémol" + oct );
                    alter = "";
                } else store.pitch=( step + oct );
            store.rythm=measure.GetRythm(store.duration);
            measure.addNote(store);
            this.articulation = 0;
        }
    }
    public void startDocument() {}
    public void endDocument() {
    }
    public void characters ( char[] ch, int start, int len ) {
        String trim = new String(ch, start, len).trim();
        if( isDur == 2 )
            this.dur = trim;
        if(isStep) this.step = trim;
        if(isAlter) this.alter = trim;
        if(isOct) this.oct = trim;
        if(isMode) measure.setMode(trim);
        if(isBeats ) measure.setTimeBeats(Integer.parseInt(trim));
        if(isBeatsType) measure.setBeatType(Integer.parseInt(trim));
        if(isKey) measure.setKey(trim);
    }
    public static Part ParseThis( String fName,int partId ) {
        try {
            MusicXMLParser xmlParser=new MusicXMLParser(partId);
            SAXParser p = SAXParserFactory.newInstance().newSAXParser();
            p.parse( fName, xmlParser);
            return xmlParser.part;
        } catch ( Exception e ) {
            System.out.println( "Error: " + e.getMessage() + "\n" +
                    e.toString() + "\n" + e.getCause() );
            return new Part(-1);
        }
    }
}
