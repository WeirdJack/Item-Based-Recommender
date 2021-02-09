
Recommendation System Implementation using Pearson Correlation Coefficient with Nearest Neighbor

### Steps

    Environment Requirement : Java 1.8 or higher is installed in the system

    1. Unzip the Recommender System project and navigate to the /src folder

    2. to create .class files for all .java files, run below command:

    ➜  make

    3. to run the Main class with required argument -> the train.txt file, run the below command:

    ➜ java Driver train.txt
    
    Note: please specify the exact location of the train.txt file in case the above command doesn't work

    output.txt file will be generated after the completion of this command in the /src folder.

    4. to clear all the .class files and the output.txt file run the below command:

    ➜  make clean
    
### Citations

    [1] Building an Effective Recommender System Using Machine Learning Based Framework
    
    [2] Calculating the User-item Similarity using Pearson’s and Cosine Correlation
        
    [3] http://mines.humanoriented.com/classes/2010/fall/csci568/portfolio_exports/sphilip/pear.html
    
    [4] https://blog.statsbot.co/recommendation-system-algorithms-ba67f39ac9a3
    
    [5] Lecture 43 — Collaborative Filtering | Stanford University https://www.youtube.com/watch?v=h9gpufJFF-0&t=621s
    
    [6] for files FileProcessor.java, Results.java, I have taken from my personal repository which were done as assignments in CS542
    
        https://github.com/WeirdJack/Java-Programming/blob/master/file-metrics-calculator/wordPlay/src/wordPlay/util/FileProcessor.java
        https://github.com/WeirdJack/Java-Programming/blob/master/file-metrics-calculator/wordPlay/src/wordPlay/util/Results.java