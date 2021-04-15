public class ListElement {
    String vokabel;
    String antwort;
    ListElement prev = null;
    ListElement next = null;

    public ListElement(String vokabel, String antwort) {
        this.vokabel = vokabel;
        this.antwort = antwort;
    }

    public ListElement(){
    }

    public String getVokabel() {
        return vokabel;
    }

    public void setVokabel(String vokabel) {
        this.vokabel = vokabel;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    public ListElement getPrev() {
        return prev;
    }

    public void setPrev(ListElement prev) {
        this.prev = prev;
    }

    public ListElement getNext() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    /**
     * Fügt an das übergebene Element ein weiteres an
     * @param newElement
     */
    public void addElementAfterThis(ListElement newElement){
        this.setNext(newElement);
        newElement.setPrev(this);
    }

    /**
     * Testet ob das aktuelle Element das letzte der Liste ist
     * @return true wenn letztes Element
     */
    public boolean isLastElement(){
        return this.next==null;
    }

    /**
     * Testet ob das aktuelle Element das erste der Liste ist
     * @return true wenn erstes Element
     */
    public boolean isFirstElement(){
        return this.prev==null;
    }
}
