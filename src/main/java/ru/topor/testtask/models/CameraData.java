package ru.topor.testtask.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraData {

    private Integer id;
    private String urlType;
    private String videoUrl;
    private String value;
    private Integer ttl;

    //Default constructor
    public CameraData(){}

    // ------ Getters & Setters ------


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    // ------ hashCode & equals ------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraData that = (CameraData) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUrlType(), that.getUrlType()) &&
                Objects.equals(getVideoUrl(), that.getVideoUrl()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getTtl(), that.getTtl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrlType(), getVideoUrl(), getValue(), getTtl());
    }
}
