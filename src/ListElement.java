public class ListElement {

    private Vokabel vokabel;
    private ListElement prev = null;
    private ListElement next = null;

    public ListElement(String vokabel, String antwort) {
         this.vokabel = new Vokabel(vokabel,antwort);
    }

    public Vokabel getVokabel() {
        return vokabel;
    }

    public void setVokabel(Vokabel vokabel) {
        this.vokabel = vokabel;
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
     * @param newElement das neue Element
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

    @Override
    public String toString() {
        return "ListElement{" +
                "vokabel=" + vokabel +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
