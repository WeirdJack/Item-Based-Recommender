import java.io.FileWriter;
import java.io.IOException;

public class Results implements IResults{

    String OUTPUT_FILENAME = "output.txt";

    /**
     * processes the resultant matrix and writes it to the
     * output.txt file
     *
     * @param  output  final 2D array result
     * @exception IOException for file input error
     */
    @Override
    public void writeToFile(int[][] output) {

        try {
            FileWriter myWriter = new FileWriter(OUTPUT_FILENAME);
            for (int user = 1; user < 944; user++){
                for (int item = 1; item < 1683; item++){

                    String result = user + " " + item + " " + output[user][item] + "\n";
                    myWriter.write(result);
                }
            }

            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
