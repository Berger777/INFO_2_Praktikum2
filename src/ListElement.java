import java.util.List;

public class ListElement {
    String vokabel;
    ListElement prev;
    ListElement next;

    public String getVokabel() {
        return vokabel;
    }

    public void setVokabel(String vokabel) {
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
}
