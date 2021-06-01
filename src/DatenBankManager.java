import java.util.ArrayList;

public class DatenBankManager implements VokabelManager
{
    @Override
    public boolean save(Vokabel vokabel) {
        return false;
    }

    @Override
    public boolean delete(String vokabelDeutsch) {
        return false;
    }

    @Override
    public Vokabel getRandomVokabel() {
        return null;
    }

    @Override
    public ArrayList<Vokabel> getAllVokabeln() {
        return null;
    }

    @Override
    public void debug() {

    }
}
