
public class MetricsCalculator {

    /** total number of items */
    final int TOTAL_ITEMS = 1682;

    /**
     * calculates the numerator for pearson coefficient
     *
     * @param  sum_product_user1_user2  summation of the products of user1 and user 2 values
     * @param  sum_user1_items  summation of the item values of user1
     * @param  sum_user2_items  summation of the item values of user2
     *
     * @return numerator for pearson coefficient
     */
    public double pearsonCoefficientSimilarityNumerator(double sum_product_user1_user2, double sum_user1_items, double sum_user2_items) {

        double numerator = sum_product_user1_user2 - (sum_user1_items * sum_user2_items)/TOTAL_ITEMS;
        return numerator;
    }

    /**
     * calculates the denominator for pearson coefficient
     *
     * @param  square_sum_user1_items  summation of the squares of the item values of user1
     * @param  square_sum_user2_items  summation of the squares of the item values of user2
     * @param  sum_user1_items  summation of the item values of user1
     * @param  sum_user2_items  summation of the item values of user2
     *
     * @return denominator for pearson coefficient
     */
    public double pearsonCoefficientSimilarityDenominator(double square_sum_user1_items, double square_sum_user2_items, double sum_user1_items, double sum_user2_items) {

        double denominator = Math.pow((square_sum_user1_items - Math.pow(sum_user1_items, 2)/TOTAL_ITEMS)*(square_sum_user2_items - Math.pow(sum_user2_items, 2)/TOTAL_ITEMS), 1/2);
        return denominator;
    }
}
