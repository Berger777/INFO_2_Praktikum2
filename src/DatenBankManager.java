import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasse die das Interface VokabelnManger implementiert und eine Datenbankschnittstelle bereitstellt
 */
public class DatenBankManager implements VokabelManager{

    private Connection connection;
    private static final String url = "jdbc:hsqldb:hsql://localhost";
    private static final String username = "usr";
    private static final String password = "ooz1ooHi";

    public DatenBankManager() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
            System.err.println("Couldn't find HSQLDB JDBC driver. Have you referenced the library?");
            System.exit(1);
        }

        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Verbindung zur Datenbank fehlerhaft!");
            System.exit(1);
        }
        System.out.println("Verbindung zur Datenbank hergestellt!");
    }

    /**
     * Speichert eine Vokabel auf der Datenbank
     * @param vokabel - die zu speichernde Vokabel
     * @return im erfolgsfall true
     */
    @Override
    public boolean save(Vokabel vokabel) {
        try {
            String sql = "INSERT INTO vokabeln (en, de) VALUES (?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, vokabel.getVokabel());
            statement.setString(2, vokabel.getAntwort());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Loescht eine Vokabel
     * @param vokabelDeutsch vokabel nach der gesucht wird
     * @return true wenn das Loeschen erfolgreich war
     */
    @Override
    public boolean delete(String vokabelDeutsch) {
        try {
            String sql = "DELETE FROM vokabeln WHERE de = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, vokabelDeutsch);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Gibt eine random Vokabel zurueck
     * @return die random Vokabel
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
            System.err.println("Datenbank leer!");
            return null;
        }
        return vok;
    }

    /**
     * Gibt alle Vokabeln als ArrayListe aus der Datenbank zurueck
     * @return die ArrayList
     */
    @Override
    public ArrayList<Vokabel> getAllVokabeln() {
        ArrayList<Vokabel> vokabelListe = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VOKABELN";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet rs  = statement.executeQuery();

            while (rs.next()){
                Vokabel vokabel = new Vokabel(rs.getString("en"),rs.getString("de"));
                vokabelListe.add(vokabel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vokabelListe;
    }

    /**
     * Zu Debug Zwecken eine Ausgabe der Vokabeln
     */
    @Override
    public void debug() {
        for (Vokabel v:getAllVokabeln()) {
            System.out.println(v);
        }
    }
}
