import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vokabeltrainer {

    boolean loop = true;
    String input;

    public String startTrainer() throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        while(loop){
            input = buff.readLine();
            if (input.equals("close")){
                loop = false;
            }else if (input.equals("add")){

            }else if (input.equals("del")){

            }else if (input.equals("learn")){

            }

        }
        return "Trainer beendet.";
    }

}
