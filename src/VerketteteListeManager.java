import java.util.ArrayList;
import java.util.Random;

public class VerketteteListeManager implements VokabelManager{

    private final LinkedList vokabelListe = new LinkedList();

    @Override
    public boolean save(Vokabel vokabel) {
        ListElement listElement = new ListElement(vokabel.getVokabel(),vokabel.getAntwort());
        ListElement lastElement = vokabelListe.getLastElementOfList();
        lastElement.setNext(listElement);
        listElement.setPrev(lastElement);
        return true;
    }

    @Override
    public boolean delete(String vokabelDeutsch) {
        return vokabelListe.deleteByName(vokabelDeutsch);
    }

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

    @Override
    public ArrayList<Vokabel> getAllVokabeln() {
        return vokabelListe.getElementsAsArrayList();
    }

    @Override
    public void debug() {
        vokabelListe.debug();
    }
}
