import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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

    @Override
    public boolean delete(String vokabelDeutsch) {
        return false;
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
            System.err.println("Datenbank leer!");
            return null;
        }
        return vok;
    }

    @Override
    public ArrayList<Vokabel> getAllVokabeln() {
        return null;
    }

    @Override
    public void debug() {
    }
}
