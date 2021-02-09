
public class Driver {

    /**
     * Starts the recommender system
     *
     *
     * @param  args  input file location
     */
    public static void main(String[] args) throws Exception {

        final int REQUIRED_NUMBER_OF_ARGS = 1;
        if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                (args[0].equals("${inputFile}"))) {

            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
            System.exit(0);
        }

        Recommender recommender = new Recommender();
        int[][] finalOutput = recommender.processData(args[0]);

        IResults results = new Results();
        results.writeToFile(finalOutput);
    }
}
