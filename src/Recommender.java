import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recommender {

    FileProcessor fileProcessor;
    MetricsCalculator metricsCalculator = new MetricsCalculator();

    double[][] itemXItems = new double[1683][1683];

    /**
     * processes the input file
     * calls to create similarity matrix for item-to-item
     * calls predictRating method to predict rating for missing values in userXItems matrix
     *
     * @param  inputFilePath  absolute path of the input file
     * @exception IOException for file input error
     */
    public int[][] processData(String inputFilePath) throws IOException {

        fileProcessor = new FileProcessor(inputFilePath);

        createSimilarityMatrix();

        for (int user = 1; user < 944; user++) {

            for (int item = 1; item < 1683; item++){

                if (fileProcessor.userXItems[user][item] == 0) {

                    fileProcessor.userXItems[user][item] = predictRating(user,item);
                }
            }
        }

        return fileProcessor.userXItems;
    }

    /**
     * creates similarity matrix for item-to-item
     *
     */
    private void createSimilarityMatrix() {

        for (int item_i = 1; item_i < 1683; item_i++){
            for (int item_j = 1; item_j < 1683; item_j++){

                if (item_i == item_j || itemXItems[item_i][item_j] > 0 || itemXItems[item_j][item_i] > 0)
                    continue;

                itemXItems[item_i][item_j] = checkForSimilarity(item_i, item_j, fileProcessor.normalizedMatrix);
                itemXItems[item_j][item_i] = itemXItems[item_i][item_j];
            }
        }
    }

    /**
     * Checks for similarity between two items by returning the
     * Pearson Correlation Coefficient between those items
     *
     * @param  item1  item1 value
     * @param  item2  item2 value
     * @param  normalizedUserXItems  normalized matrix created in the FileProcessor class
     * @return returns the Pearson Correlation Coefficient between two items
     */
    public double checkForSimilarity(int item1, int item2, double[][] normalizedUserXItems){

        double sum_item1_users = 0.0;
        double sum_item2_users = 0;
        double square_sum_item1_users = 0.0;
        double square_sum_item2_users = 0.0;
        double sum_product_item1_item2 = 0.0;

        for (int user = 1; user < 944; user++){

            sum_item1_users += normalizedUserXItems[user][item1];

            sum_item2_users += normalizedUserXItems[user][item2];

            square_sum_item1_users += Math.pow(normalizedUserXItems[user][item1], 2);
            square_sum_item2_users += Math.pow(normalizedUserXItems[user][item2], 2);

            sum_product_item1_item2 += normalizedUserXItems[user][item1] * normalizedUserXItems[user][item2];
        }

        double num = metricsCalculator.pearsonCoefficientSimilarityNumerator(sum_product_item1_item2, sum_item1_users, sum_item2_users);
        double dem = metricsCalculator.pearsonCoefficientSimilarityDenominator(square_sum_item1_users, square_sum_item2_users, sum_item1_users, sum_item2_users);

        double pearsonCorrelationCoefficient = dem > 0 ? num/dem : 0;

        return pearsonCorrelationCoefficient;
    }

    /**
     * predicts the rating for an user-item entry based on the neighborhood of that item
     * which has similarity with other such items. By taking the weighted mean, for each item Y
     * in the neighborhood N we weight y’s rating for item i by the similarity of items x & y and
     * then we normalize it by taking the sum of similarities.
     *
     * @param  currentUser  user value
     * @param  currentItem  item value
     * @return returns the Predicted rating for that user-item entry
     */
    public int predictRating(int currentUser, int currentItem){

        double ratingNum = 0;
        double ratingDen = 0;
        List<Integer> neighborsList = new ArrayList<>();
        for (int item = 1; item < 1683; item++){

            if(item == currentItem)
                continue;

            double similarityIndex = itemXItems[currentItem][item];

            if (similarityIndex > 0){
                neighborsList.add(item);
            }

        }

        for (int neighbor : neighborsList){

            if (currentItem != neighbor && fileProcessor.userXItems[currentUser][neighbor] > 0) {

                ratingNum += fileProcessor.userXItems[currentUser][neighbor] * itemXItems[currentItem][neighbor];
                ratingDen += itemXItems[currentItem][neighbor];
            }
        }

        int predictedRating = Math.toIntExact(Math.round(ratingNum / ratingDen));
        predictedRating = (predictedRating > 5) ? 5 : predictedRating;
        predictedRating = (predictedRating < 1) ? 1 : predictedRating;

        return predictedRating;
    }
}
