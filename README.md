# SpotifyStatistics
A desktop application made in Java. Its purpose is to display data collected in .csv file, generating Spearman's rank correlation coefficients based on features. The structure is based on MVC.


The app is focused on statistical analysis of the provided data. The _.csv_ file contains, e.g., records of artists' listeners. Other functionalities:
* Displaying top 1 artist's name
* Sorting artists according to their popularity
* Calculating features' minimum and maximum values
* Calculating features' arithmetic mean
* Calculating Spearman's Rank Correlation Coefficients for different features
The project assigns listeners' continents, where they reside randomly. 

## Spearman's Rank Correlation Coefficient

The project uses the following formula:
$$ r_s= \frac{12}{n(n^2-1)} \sum_{i=1}^{n} R_{x_i}R_{y_i} - \frac{3(n+1)}{n-1} $$
Where:
$$ r_s \in [-1, 1] $$
It is being used, when features' values are repeatable. 