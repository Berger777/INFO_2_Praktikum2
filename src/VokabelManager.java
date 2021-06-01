import java.util.ArrayList;

public interface VokabelManager {
    boolean save(Vokabel vokabel);
    boolean delete(String vokabelDeutsch);
    Vokabel getRandomVokabel();
    ArrayList<Vokabel> getAllVokabeln();
    void debug();
}
