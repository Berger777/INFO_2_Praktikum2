import Exceptions.ListEmptyException;
import Exceptions.OutOfBounceException;

/**
 * Wrapper-Klasse für die LinkedList
 */
public class LinkedList {

    private final ListElement rootElement = new ListElement("!ROOT!","!ELEMENT!");

    /**
     * Listengröße ohne ROOT-Element
     * @return Listengröße
     */
    public int listSize(){
        int i = 0;
        ListElement temp = rootElement;
        while (!temp.isFirstElement()){
            temp = temp.getPrev();
        }
        while (!temp.isLastElement()){
            temp = temp.getNext();
            i++;
        }
        return i;
    }

    /**
     * Gibt das letzte Element der Liste zurück
     * @return letztes Element der Liste
     */
    public ListElement getLastElementOfList(){
        ListElement temp = rootElement;
        while (!temp.isLastElement()){
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * Gibt das Root-Element der Liste zurück
     * @return Root-Element der Liste
     */
    public ListElement getRootElement(){
        return rootElement;
    }

    /**
     * Gibt das Element nummer nextInt an
     * @param elementNummer -> X
     * @return Element Nummer-X
     */
    public ListElement getElementNumber(int elementNummer) throws OutOfBounceException, ListEmptyException {
        ListElement temp = getRootElement();
        elementNummer++; //Skip Root
        if (getRootElement().getNext()==null){
            throw new ListEmptyException();
        }
        for (int i = 0; i<elementNummer;i++){
            if (temp.getNext()==null){
                throw new OutOfBounceException();
            }else{
                temp = temp.getNext();
            }
        }
        return temp;
    }

    /**
     * Löscht eine Vokabel aus der Liste
     * @param name Name nach dem gesucht wird
     * @return true wenn das Löschen erfolgreich war
     */
    public boolean deleteByName(String name){
        ListElement temp = getRootElement();
        if (getRootElement().getNext()==null){
            System.out.println("Liste leer!");
            return false;
        }else{
            temp = temp.getNext();
        }
        do {
            if(temp.getAntwort().equals(name) || temp.getVokabel().equals(name)){
                if (temp.getNext()==null){  //Letztes Element der Liste
                    temp.getPrev().setNext(null);
                    return true;
                }else if(temp.getPrev()==null){
                    System.out.println("ROOT-Element kann nicht gelöscht werden!");
                    return false;
                }
                else{
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    return true;
                }
            }else{
                temp = temp.getNext();
            }
        }while (temp.getNext()!=null);
        System.out.println("Vokabel nicht gefunden!");
        return false;
    }

    /**
     * Printe alle Elemente
     */
    public void printList(){
        ListElement temp = rootElement;
        int i = 0;
        while (!temp.isLastElement()){
            System.out.println(i+++":"+temp.vokabel+ ";"+temp.antwort);
            temp = temp.getNext();
        }
        System.out.println(i+":"+ temp.vokabel+";"+temp.antwort);
    }

    /**
     * Gibt die Vokabelliste als String-Array zurück
     * @return Liste als Array
     */
    public String[] getVokabelnAsArray(){
        String[] vok = new String[listSize()];
        ListElement temp = getRootElement();
        int i = 0;
        while (temp.getNext()!=null){
            temp = temp.getNext();
            vok[i++] = temp.getVokabel() + ';' + temp.getAntwort()+System.lineSeparator();
        }
        return vok;
    }
}
