package converter;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import partition.Measure;
import partition.Note;
import partition.Part;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.PrintStream;

public class MusicXMLParser extends DefaultHandler{
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
    private static int isDur = 1;
    // Nom de la note
    private static boolean isStep = false;
    // Octave de la note
    private static boolean isOct = false;
    // Altération de la note
    private static boolean isAlter = false;
    // Nuance de la note
    private static boolean isDynamic;
    // Clé à laquelle appartient la note
    private static boolean isNoteKeyGroup = false;
    private String step = "";
    private String dur = "";
    private String oct = "";
    private String alter = "";
    private String articulation = "";
    private String dynamic="";
    private int noteGroup;
    private int nbMeasures=0;
    private Part part;
    private Measure measure;
    private MusicXMLParser(int partId) {
        super();
        this.part=new Part(partId);
    }
    public void startElement( String namespaceURI, String localName,
                              String qName, Attributes atts ) throws SAXException{
        if( qName.equals( "measure" ) ) {
            nbMeasures++;
            measure=new Measure(nbMeasures);
        }
        if(qName.equals("sign")) isKey=true;
        if( qName.equals( "beat-type" ) ) isBeatsType = true;
        if(qName.equals("mode")) isMode=true;
        if( qName.equals( "beats" ) ) isBeats = true;

        if(qName.equals("dynamics")) isDynamic=true;
        if(qName.equals("staff")) isNoteKeyGroup=true;
        if( MusicXMLParser.isDynamic ) {
            if( qName.equals( "p" ) ) this.dynamic = "piano";
            if( qName.equals( "pp" ) ) this.dynamic = "pianissimo";
            if( qName.equals( "ppp" ) ) this.dynamic = "pianississimo";
            if( qName.equals( "f" ) ) this.dynamic = "forte";
            if( qName.equals( "ff" ) ) this.dynamic = "fortissimo";
            if( qName.equals( "fff" ) ) this.dynamic = "fortississimo";
            if( qName.equals( "mp" ) ) this.dynamic = "mezzo piano";
            if( qName.equals( "mf" ) ) this.dynamic = "mezzo forte";
            if( qName.equals( "sf" ) ) this.dynamic = "subito forte";
            if( qName.equals( "sfz" ) ) this.dynamic = "sforzando";
            if( qName.equals( "sfp" ) ) this.dynamic = "sforzando piano";
            if( qName.equals( "sz" ) ) this.dynamic = "forzando";
        }
        if(qName.equals("articulations"))isArticulation=true;
        if( MusicXMLParser.isArticulation ) {
            if( qName.equals( "accent" ) ) this.articulation = "accentué";
            if( qName.equals( "strong-accent" ) ) this.articulation="très accentué";
            if( qName.equals( "staccato" ) ) this.articulation = "staccato";
            if( qName.equals( "staccatissimo" ) ) this.articulation="staccatissimo";
        }
        if( isDur == 1 ) {
            if( qName.equals( "duration" ) ) {
                isDur = 2;
            }
        }
            if( qName.equals( "step" ) ) isStep = true;
            if( qName.equals( "alter" ) ) isAlter = true;
            if( qName.equals( "octave" ) ) isOct = true;
    }
    public void endElement( String namespaceURI, String localName,
                            String qName ) throws SAXException{
        if(qName.equals("measure")){
            part.addMeasure(measure);
        }
        if(qName.equals("sign")) isKey=false;
        if(qName.equals("staff")) isNoteKeyGroup=false;
        if(qName.equals("dynamic")) isDynamic=false;
        if( qName.equals( "beat-type" ) ) isBeatsType = false;
        if(qName.equals("mode")) isMode=false;
        if( qName.equals( "beats" ) ) isBeats = false;
        if(qName.equals("articulations"))isArticulation=false;
        if( qName.equals( "duration" ) && isDur == 2 ) isDur = 1;
        if( qName.equals( "step" ) ) isStep = false;
        if( qName.equals( "alter" ) ) isAlter = false;
        if( qName.equals( "octave" ) ) isOct = false;
        if( qName.equals( "note" ) ) {
            Note store = new Note();
            store.dynamic=this.dynamic;
            System.out.println(dynamic);
            store.articulation=this.articulation;

            if( this.dur.length() > 0 ) {
                store.duration=Integer.parseInt( this.dur );
            }
                if( alter.length() > 0 ) {
                    if( Integer.parseInt( alter ) == 1 )
                        store.pitch=step + " dièse " + oct ;
                    else store.pitch=( step + " bémol " + oct );
                    alter = "";
                }
                else
                    store.pitch=( step + oct );
            if(measure.getNumber()==1)
                store.rythm=part.GetRythm(store.duration,measure.getTimeBeats());
            else
                store.rythm=part.GetRythm(store.duration);
            store.group=noteGroup;
            this.articulation = "";
            measure.addNote(store);
        }
    }
    public void startDocument() throws SAXException{
        System.out.println("Début de la conversion");
    }
    public void endDocument() throws SAXException{
        System.out.println("Fin de la conversion. Nb mesures : "+nbMeasures);
    }
    public void characters ( char[] ch, int start, int len ) throws SAXException{
        String trim = new String(ch, start, len).trim();
        if(!trim.equals("")) {
            System.out.println(trim);
            if (isDur == 2)
                this.dur = trim;
            if (isStep) this.step = trim;
            if (isAlter) this.alter = trim;
            if (isOct) this.oct = trim;
            if(isNoteKeyGroup) this.noteGroup=Integer.parseInt(trim);
            if (isMode) measure.setMode(trim);
            if(isBeats) measure.setTimeBeats(Integer.parseInt(trim));
            if (isBeatsType) measure.setBeatType(Integer.parseInt(trim));
            if (isKey) measure.addKey(trim);
        }
    }
    public static Part ParseThis( String fName,int partId ) {
        try {
            MusicXMLParser xmlParser=new MusicXMLParser(partId);
            SAXParser p = SAXParserFactory.newInstance().newSAXParser();
            XMLReader xmlReader = p.getXMLReader();
            xmlReader.setContentHandler(xmlParser);
            xmlReader.setErrorHandler(new MyErrorHandler(System.err));
            xmlReader.parse(fName);
            return xmlParser.part;
        } catch ( Exception e ) {
            System.out.println( "Error: " + e.getMessage() + "\n" +
                    e.toString() + "\n" + e.getCause() );
            return new Part(-1);
        }
    }
    private static class MyErrorHandler implements ErrorHandler {
        private PrintStream out;

        MyErrorHandler(PrintStream out) {
            this.out = out;
        }

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();

            if (systemId == null) {
                systemId = "null";
            }

            String info = "URI=" + systemId + " Line="
                    + spe.getLineNumber() + ": " + spe.getMessage();

            return info;
        }

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
