package ru.topor.testtask.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraMetadata {

    private Integer id;
    private String sourceDataUrl;
    private String tokenDataUrl;

    //Default constructor
    public CameraMetadata(){}

    //Constructor with all fields
    public CameraMetadata(Integer id, String sourceDataUrl, String tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }

    // -------------  Getters & Setters ---------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }

    // --------  hashCode & equals ----------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraMetadata that = (CameraMetadata) o;
        return getId().equals(that.getId()) &&
                getSourceDataUrl().equals(that.getSourceDataUrl()) &&
                getTokenDataUrl().equals(that.getTokenDataUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSourceDataUrl(), getTokenDataUrl());
    }
}
