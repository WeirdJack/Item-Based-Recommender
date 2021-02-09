import java.io.*;

public class FileProcessor {

    int[][] userXItems = new int[944][1683];
    double[][] normalizedMatrix = new double[944][1683];

    /**
     * processes the input file
     *
     * @param  inputFilePath  absolute path of the input file
     * @exception IOException for file input error
     */
    public FileProcessor(String inputFilePath) throws IOException {

        File file = new File(inputFilePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;

        while ((str = br.readLine()) != null) {

            String[] currentRow = str.split(" ");
            userXItems[Integer.parseInt(currentRow[0])][Integer.parseInt(currentRow[1])] = Integer.parseInt(currentRow[2]);
        }

        normalizeMatrix(userXItems);
    }

    //this method normalizes the available ratings by taking the average ratings for the current user. This is done to
    //calculate the similarity between items or users in further work. Otherwise the zero ratings are considered as negative
    //and that gives an incorrect similarity matrix
    /**
     * creates a normalized matrix of the available ratings from the userXItem matrix by taking the average ratings for the current user.
     * This is done to calculate the similarity between items or users in further work. Otherwise the zero ratings are considered as negative
     * and that gives an incorrect similarity matrix
     *
     * @param  userXItems  user-item matrix
     */
    private void normalizeMatrix(int[][] userXItems) {

        for (int i = 1; i < 944; i++){

            int sumRatings = 0;
            int totalItemsRated = 0;
            for (int j = 1; j < 1683; j++){

                if (userXItems[i][j] == 0)
                    continue;

                sumRatings += userXItems[i][j];
                totalItemsRated++;
            }

            for (int k = 1; k < 1683; k++){

                if (userXItems[i][k] == 0)
                    continue;

                normalizedMatrix[i][k] = userXItems[i][k] - sumRatings/totalItemsRated;
            }
        }
    }
}
