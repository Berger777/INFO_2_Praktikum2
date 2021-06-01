import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Klasse zum Trainieren, Speichern und Erstellen von Vokabeln
 * @author Matthias Groll
 * @since April 2021
 */
public class Vokabeltrainer {

    private final DatenBankManager datenBankManager = new DatenBankManager();
    private final VerketteteListeManager verketteteListeManager = new VerketteteListeManager();
    private VokabelManager vokabelManager = verketteteListeManager;
    private boolean loop = true;
    private final BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Hauptfunktion zum Starten des Vokabeltrainers
     * @throws IOException Buffered-Reader kann Probleme beim Einlesen melden
     */
     public void startTrainer() throws IOException {
        while (loop) {
            String input = buff.readLine();
            switch (input) {
                case "close":
                    loop = false;
                    break;
                case "add":
                    System.out.println("Geben Sie die Vokabel ein:");
                    String eingabe = buff.readLine();
                    addVokabel(eingabe);
                    break;
                case "del":
                    System.out.println("Stichwort zum Löschen eingeben:");
                    delete();
                    break;
                case "save":
                    saveVokabeln();
                    break;
                case "read":
                    vokabelnEinlesen();
                    break;
                case "print":
                    vokabelManager.debug();
                    break;
                case "learn":
                    String x = learn();
                    if (x != null) System.out.println(x);
                    break;
                case "switch":
                    if (vokabelManager instanceof DatenBankManager){
                        vokabelManager = verketteteListeManager;
                    }else if (vokabelManager instanceof VerketteteListeManager){
                        vokabelManager = datenBankManager;
                    }
                    break;
                default:
                    System.out.println("Befehl nicht gefunden!");
                    listeBefehleHilfe();
                    break;
            }
        }
        buff.close();
        System.out.println( "Trainer beendet.");
    }

    /**
     * Methode zum Löschen einer Vokabel mit Konsolen-Dialog
     * @throws IOException BufferedReader kann Fehler beim Lesen haben
     */
    private void delete() throws IOException {
        String name = buff.readLine();
        if(vokabelManager.delete(name)){
            System.out.println("Löschen erfolgreich!");
        }else{
            System.out.println("Fehler beim löschen!");
        }
    }

    /**
     * Methode zum Einlesen einer Vokabel mit Konsolen-Dialog
     * @throws IOException BufferedReader kann Fehler beim Lesen haben
     */
    private void vokabelnEinlesen() throws IOException {
        System.out.println("Bitte Dateinamen eingeben:");
        String dateiname = buff.readLine();
        FileReader fr;
        try {
            fr = new FileReader(dateiname);
        }catch (FileNotFoundException e){
            System.out.println("Datei nicht gefunden!");
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(fr);
        while(bufferedReader.ready()){
            String line = bufferedReader.readLine();
            addVokabel(line);
        }
        fr.close();
    }

    /**
     * Methode zum Speichern der Vokabeln in einer Datei
     * @throws IOException FileWriter kann IO-Probleme haben
     */
    private void saveVokabeln() throws IOException {
        System.out.println("Bitte Dateiname eingeben:");
        String dateiname = buff.readLine()+".txt";
        FileWriter fw = new FileWriter(dateiname);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        ArrayList<Vokabel> vok = vokabelManager.getAllVokabeln();
        for (Vokabel v:vok) {
            bufferedWriter.write(v.toString());
        }
        bufferedWriter.flush();
        fw.flush();
        bufferedWriter.close();
        fw.close();
        System.out.println("Datei wurde geschrieben!");
    }

    /**
     * Methode zum zufälligen Abfragen der Vokabeln
     * @return String mit Fehlermeldung
     * @throws IOException BufferedReader kann Probleme beim Einlesen haben
     */
    private String learn() throws IOException {
        Vokabel vok = vokabelManager.getRandomVokabel();
        System.out.println("Was bedeutet: " + vok.getVokabel() + " ?");
        String antwort = buff.readLine().toLowerCase(Locale.ROOT);
        if (antwort.equals(vok.getAntwort().toLowerCase(Locale.ROOT))) {
            System.out.println("Das ist richtig! Super. :-)");
        } else {
            System.out.println("Das ist leider falsch. :-( | Richtig ist: " + vok.getAntwort());
        }
        return null;
    }

    /**
     * Fügt eine Vokabel hinzu, wird durch RegEx validiert
     * @param eingabe Vokabel durch ';' getrennt
     */
    private void addVokabel(String eingabe) {
        if (eingabe.matches("^[^;\\n]+;[^;\\n]+$")) {
            int semiIndex = eingabe.indexOf(';');
            String vokabel = eingabe.substring(0, semiIndex);
            String antwort = eingabe.substring(semiIndex + 1);
            Vokabel vokabelElement = new Vokabel(vokabel, antwort);
            if (vokabelManager.save(vokabelElement)){
                System.out.println("Vokabel erfolgreich hinzugefuegt!");
            }else {
                System.err.println("Vokabeln speichern nicht erfolgreich!");
            }
        } else {
            System.out.println("Vokabel fehlerhaft! Vokabel und Antwort mit ; trennen! Fehlerhafte Vokabel:" + eingabe);
        }
    }

    /**
     * Listet alle Befehle auf
     */
    private void listeBefehleHilfe() {
        System.out.println("Gültige Befehle:");
        System.out.println("------------------");
        System.out.println("add - Vokabel hinzufügen");
        System.out.println("del - Vokabel löschen");
        System.out.println("read - Vokabeln von Datei lesen");
        System.out.println("save - Vokabeln in Datei speichern");
        System.out.println("learn - Vokabeln lernen");
        System.out.println("close - Programm beenden");
        System.out.println("print - Vokabelliste anzeigen");
    }

}
