package pl.polsl.lab.cw1.model;

/**
 *
 * @author Aleksander Augustyniak
 */
public class CorrelationDTO {
    private String firstFeatureName;
    private String secondFeatureName;
    private Double correlationValue;

    
    public CorrelationDTO(String firstFeatureName, String secondFeatureName, Double correlationValue) {
        this.firstFeatureName = firstFeatureName;
        this.secondFeatureName = secondFeatureName;
        this.correlationValue = correlationValue;
    }
    
    public String getFirstFeatureName() {
        return firstFeatureName;
    }

    public void setFirstFeatureName(String firstFeatureName) {
        this.firstFeatureName = firstFeatureName;
    }

    public String getSecondFeatureName() {
        return secondFeatureName;
    }

    public void setSecondFeatureName(String secondFeatureName) {
        this.secondFeatureName = secondFeatureName;
    }

    public Double getCorrelationValue() {
        return correlationValue;
    }

    public void setCorrelationValue(Double correlationValue) {
        this.correlationValue = correlationValue;
    }
    
    
}
