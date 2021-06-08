import java.util.ArrayList;
import java.util.Random;

/**
 * Der verkette ListeManager verwaltet die Verkettete Liste
 * Implementiert das Interface VokabelManager
 */
public class VerketteteListeManager implements VokabelManager{

    private final LinkedList vokabelListe = new LinkedList();

    /**
     * Speicher eine Vokabel in die Liste
     * @param vokabel die gespeicherte Vokabel
     * @return true wenn der Vorgang erfolgreich war
     */
    @Override
    public boolean save(Vokabel vokabel) {
        ListElement listElement = new ListElement(vokabel);
        ListElement lastElement = vokabelListe.getLastElementOfList();
        lastElement.setNext(listElement);
        listElement.setPrev(lastElement);
        return true;
    }

    /**
     * Loescht eine Vokabel aus der Liste
     * @param vokabelDeutsch die Vokabel wonach gesucht wird
     * @return true wenn der Vorgang erfolgreich war
     */
    @Override
    public boolean delete(String vokabelDeutsch) {
        return vokabelListe.deleteByName(vokabelDeutsch);
    }

    /**
     * Gibt eine zufaellige Vokabel zurueck
     * @return die Vokabel
     */
    @Override
    public Vokabel getRandomVokabel() {
        Vokabel vok;
        int listSize = getAllVokabeln().size();
        if (listSize == 0) {
            System.out.println("Liste leer!");
            return null;
        }
        Random random = new Random();
        int rand = random.nextInt(listSize);
        try {
            vok = getAllVokabeln().get(rand);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Außerhalb des Listenbereichs.");
            return null;
        } catch (NullPointerException e) {
            System.err.println("Liste leer!");
            return null;
        }
        return vok;
    }

    /**
     * Gibt alle Vokabeln in Form einer ArrayList zurueck
     * @return die Liste
     */
    @Override
    public ArrayList<Vokabel> getAllVokabeln() {
        return vokabelListe.getElementsAsArrayList();
    }

    /**
     * Debug Funktion
     */
    @Override
    public void debug() {
        vokabelListe.debug();
    }
}
