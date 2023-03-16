<p align="center"><img src="https://user-images.githubusercontent.com/56155903/225719193-4602ac56-181e-4f1f-b47c-e0394055bec7.png" width="20%"/></p>
<h1 align="center">SpotifyStatistics</h1>

## Table of Contents
  * [Description](#description)
  * [How to use](#how-to-use)
    * [CSV file structure](#csv-file-structure)
  * [Releases](#releases)


## Description
A desktop application made in Java. Its purpose is to display data collected in .csv file and generating Spearman's rank correlation coefficients based on the features. Separation of concerns is maintained by implementing **MVC** architecture, where each layers are supposed to receive its own data type and process it.
The project uses the [Spearman's Rank Correlation Coefficient](#spearmans-rank-correlation-coefficient) (described below) to calculate correlations.


## How to use
Simply, perform a drag&drop operation of the *.csv* containing artists' data. The file structure is described in [CSV file structure](#csv-file-structure).
After data is loaded, a button below the view area should be active and after clicking it you are able to see the effect.

### CSV file structure
The table below represents an example of the *.csv* file to be imported into the application. It is available in the repository.
| **ID** | **Artist Name** | **Lead Streams** | **Feats**        | **Tracks** | **One Billion** | **100 Million** | **Last Updated** |
| ---    | ---             | ---              | ---              | ---        | ---             | ---             | ---              |
| 1      | Drake           | "50,162,292,808" | "19,246,513,666" | 262        | 6               | 130             | 19.09.22         |
| 2      | Bad Bunny       | "44,369,032,140" | "5,391,990,975"  | 163        | 5               | 118             | 20.09.22         |
| ...    | ...             | ...              | ...              | ...        | ...             | ...             | ...              |
| 1005   | Gotye           | "1,424,591,618"  | "10,576,378"     | 46         | 1               | 1               | 31.07.22         |

  - **ID** is an identifier of the record.
  - **Artist Name** represents an artist's name.
  - **Lead Streams** is the combined number of artist's plays.
  - **Feats** is the number of plays from feats.
  - **Track** is the number of artist's tracks available. 
  - **One Billion** is the number representing how many artist's tracks exceeded one billion streams.
  - **100 Million** is the number representing how many artist's tracks exceeded 100 million streams.
  - **Last Updated** is a date of the latest data update.


## Spearman's Rank Correlation Coefficient

The project uses the following formula:
$$r_s= \frac{12}{n(n^2-1)} \sum_{i=1}^{n} R_{x_i}R_{y_i} - \frac{3(n+1)}{n-1}$$
where:
$$r_s \in [-1, 1]$$
The equation is being used, when features' values are repeatable. 


## Releases
The latest pre-release version (v1.0.0) is already deployed and available [here](https://github.com/alekaug/SpotifyStatistics/releases/tag/1.0.0).
