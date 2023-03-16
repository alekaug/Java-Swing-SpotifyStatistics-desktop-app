<p align="center"><img src="https://user-images.githubusercontent.com/56155903/225719193-4602ac56-181e-4f1f-b47c-e0394055bec7.png" width="20%"/></p>
<h1 align="center">SpotifyStatistics</h1>

## Table of Contents
  * [Description](#description)
  * [How to use](#how-to-use)
    * [CSV file structure](#csv-file-structure)


## Description
A desktop application made in Java. Its purpose is to display data collected in .csv file and generating Spearman's rank correlation coefficients based on the features. Separation of concerns is maintained by implementing **MVC** architecture, where each layers are supposed to receive its own data type and process it.
The project uses the [Spearman's Rank Correlation Coefficient](#spearmans-rank-correlation-coefficient) (described below) to calculate correlations.


## How to use
Simply, perform a drag&drop operation of the *.csv* containing artists' data. The file structure is described in [CSV file structure](#csv-file-structure).
After data is loaded, a button below the view area should be active and after clicking it you are able to see the effect.

### CSV file structure


## Spearman's Rank Correlation Coefficient

The project uses the following formula:
$$r_s= \frac{12}{n(n^2-1)} \sum_{i=1}^{n} R_{x_i}R_{y_i} - \frac{3(n+1)}{n-1}$$
where:
$$r_s \in [-1, 1]$$
The equation is being used, when features' values are repeatable. 
