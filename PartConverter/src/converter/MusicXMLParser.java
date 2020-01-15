package converter;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MusicXMLParser extends DefaultHandler{
    private static boolean getChars = false;
    private static boolean isArticulation = false;
    private static boolean isDivisions = false;
    private static boolean isBeats = false;
    private static int isPitchText = 0;
    private static int isPitch = 0;
    private static int isDur = 0;
    private static boolean isStep = false;
    private static boolean isOct = false;
    private static boolean isAlter = false;
    public boolean isRunning = true;
    public static boolean isDynamic;
    public int row = 0;
    public int whichTagIsBeingParsed;
    private static int pitch = 0;
    public static String[] tags;
    public String step = "";
    public String dur = "";
    public String oct = "";
    public String alter = "";
    private int articulation;
    private int dynamic;
    public static int ctr = 0;
    //static ShowXML play;
    public static List<MusicXMLdatastored> dataList;
    public static MusicXMLdatastored store;
    public MusicXMLParser() {
        super();
    }
    public void startElement( String namespaceURI, String localName,
                              String qName, Attributes atts ) {
        if( localName.equals( "divisions" ) ) isDivisions = true;
        if( localName.equals( "beats" ) ) isBeats = true;
        if( MusicXMLParser.isDynamic ) {
            if( localName.equals( "p" ) ) this.dynamic = 1;
            if( localName.equals( "pp" ) ) this.dynamic = 2;
            if( localName.equals( "ppp" ) ) this.dynamic = 3;
            if( localName.equals( "f" ) ) this.dynamic = 4;
            if( localName.equals( "ff" ) ) this.dynamic = 5;
            if( localName.equals( "fff" ) ) this.dynamic = 6;
            if( localName.equals( "mp" ) ) this.dynamic = 7;
            if( localName.equals( "mf" ) ) this.dynamic = 8;
            if( localName.equals( "sf" ) ) this.dynamic = 9;
            if( localName.equals( "sfz" ) ) this.dynamic = 9;
            if( localName.equals( "sfp" ) ) this.dynamic = 10;
            if( localName.equals( "sz" ) ) this.dynamic = 11;
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
        if( isPitch == 1 || isPitchText == 1 ) {
            if( localName.equals( "step" ) ) isStep = true;
            if( localName.equals( "alter" ) ) isAlter = true;
            if( localName.equals( "octave" ) ) isOct = true;
        }
    }
    public void endElement( String namespaceURI, String localName,
                            String qName ) {
        if( getChars ) getChars = false;
        if( localName.equals( "duration" ) && isDur == 2 ) isDur = 1;
        if( localName.equals( "step" ) ) isStep = false;
        if( localName.equals( "alter" ) ) isAlter = false;
        if( localName.equals( "octave" ) ) isOct = false;
        if( localName.equals( "divisions" ) ) isDivisions = false;
        if( localName.equals( "beats" ) ) isBeats = false;
        if( localName.equals( "note" ) ) {
            store = new MusicXMLdatastored();
            store.setDynamic( this.dynamic );
            store.setArticulation( this.articulation );
            if( this.dur.length() > 0 ) {
                store.setDur( Integer.parseInt( this.dur ) );
            }
            if( isPitch == 1 ) {
                store.setPitch( findPitch( step, alter, oct ) );
            }
            if( isPitchText == 1 ) {
                if( alter.length() > 0 ) {
                    if( Integer.parseInt( alter ) == 1 )
                        store.setPitchText( step + "#" + oct );
                    else store.setPitchText( step + "b" + oct );
                    alter = "";
                } else store.setPitchText( step + oct );
            }
            dataList.add( store );
            this.dynamic = 0;
            this.articulation = 0;
        }
    }
    public void startDocument() {}
    public void endDocument() {
        //ShowXML.post( "<- PARSING COMPLETE " );
        //ShowXML.isParsing = false;
    }
    public void characters ( char[] ch, int start, int len ) {
        String trim = new String(ch, start, len).trim();
        if( isDur == 2 )
            this.dur = trim;
        if(isStep) this.step = trim;
        if(isAlter) this.alter = trim;
        if(isOct) this.oct = trim;
        //if( isDivisions ) ShowXML.divisions = Integer.parseInt(trim);
        //if( isBeats ) ShowXML.beats = Integer.parseInt(trim);
    }
    private static int toMIDI(String step) {
        switch (step) {
            case "C":
                return 0;
            case "D":
                return 2;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 7;
            case "A":
                return 9;
            default:
                return 11;
        }
    }
    private static int findPitch(String step, String alter, String oct) {
        pitch = toMIDI( step );
        if( alter.length() > 0 ) {
            pitch =+ Integer.parseInt( alter );
            isAlter = false;
        }
        if( oct.length() > 0 ) {
            pitch += ( Integer.parseInt( oct ) + 1 ) * 12;
            isOct = false;
        }
        return pitch;
    }
    public static void ParseThis( String fName ) {
        MusicXMLParser s = new MusicXMLParser();

        for( int i=0; i<tags.length; i++ ) {
            if( tags[i].toString().equalsIgnoreCase( "pitchInText" ) )
            {
                isPitchText = 1;
            }
            if( tags[i].toString().equalsIgnoreCase( "pitch" ) ) {
                isPitch = 1;
            }
            if( tags[i].toString().equalsIgnoreCase( "duration" ) ) {
                isDur = 1;
            }
            if( tags[i].toString().equals("dynamics") ) {
                isDynamic = true;
            }
            if( tags[i].toString().equals("articulations") ) {
                isArticulation = true;
            }
        }

        try {
            dataList = new ArrayList<MusicXMLdatastored>();
            SAXParser p = SAXParserFactory.newInstance().newSAXParser();
            p.parse( fName, new MusicXMLParser());
        } catch ( Exception e ) {
            System.out.println( "Error: " + e.getMessage() + "\n" +
                    e.toString() + "\n" + e.getCause() );
        }
        ctr = 0;
    }
}
