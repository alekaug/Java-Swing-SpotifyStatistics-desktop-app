/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.cw1.model;

/**
 *
 * @author narxyz
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
