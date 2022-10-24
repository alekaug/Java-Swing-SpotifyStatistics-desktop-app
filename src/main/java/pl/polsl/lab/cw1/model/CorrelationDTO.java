package pl.polsl.lab.cw1.model;

/**
 * The object, which holds feature's names of correlation coefficients and value
 * of the coefficient based upon those features.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public class CorrelationDTO {

    private String firstFeatureName;
    private String secondFeatureName;
    private Double correlationValue;

    /**
     *
     * @param firstFeatureName Name of comparable feature.
     * @param secondFeatureName Name of comparable feature
     * @param correlationValue Features' correlation result.
     */
    public CorrelationDTO(String firstFeatureName, String secondFeatureName, Double correlationValue) {
        this.firstFeatureName = firstFeatureName;
        this.secondFeatureName = secondFeatureName;
        this.correlationValue = correlationValue;
    }

    /**
     *
     * @return Name of the first feature.
     */
    public String getFirstFeatureName() {
        return firstFeatureName;
    }

    /**
     *
     * @param firstFeatureName Name of the first feature.
     */
    public void setFirstFeatureName(String firstFeatureName) {
        this.firstFeatureName = firstFeatureName;
    }

    /**
     *
     * @return Name of the second feature.
     */
    public String getSecondFeatureName() {
        return secondFeatureName;
    }

    /**
     *
     * @param secondFeatureName Name of the second feature.
     */
    public void setSecondFeatureName(String secondFeatureName) {
        this.secondFeatureName = secondFeatureName;
    }

    /**
     *
     * @return Spearman's rank correlation coefficient value.
     */
    public Double getCorrelationValue() {
        return correlationValue;
    }

    /**
     *
     * @param correlationValue Spearman's rank correlation coefficient value to
     * be set.
     */
    public void setCorrelationValue(Double correlationValue) {
        this.correlationValue = correlationValue;
    }

}
