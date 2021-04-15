import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Vokabeltrainer vokabeltrainer = new Vokabeltrainer();
        String ret = vokabeltrainer.startTrainer();
        System.out.println(ret);
    }
}
