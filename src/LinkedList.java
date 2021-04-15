import Exceptions.ListEmptyException;
import Exceptions.OutOfBounceException;

public class LinkedList {

    private final ListElement rootElement = new ListElement("ROOT","ELEMENT");

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
