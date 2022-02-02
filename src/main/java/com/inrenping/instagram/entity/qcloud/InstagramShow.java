package com.inrenping.instagram.entity.qcloud;

import javax.persistence.*;

public class InstagramShow {
    private long id;
    private long insId;
    private Long taken_at_timestamp;
    private long userId;
    private Long fileId;
    private Long locationId;
    private String locationName;
    private String locationCoordinates;
    private String remark;
    private Integer status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "INS_ID")
    public long getInsId() {
        return insId;
    }

    public void setInsId(long insId) {
        this.insId = insId;
    }

    @Basic
    @Column(name = "TAKEN_AT_TIMESTAMP")
    public Long getTaken_at_timestamp() {
        return taken_at_timestamp;
    }

    public void setTaken_at_timestamp(Long taken_at_timestamp) {
        this.taken_at_timestamp = taken_at_timestamp;
    }

    @Basic
    @Column(name = "USER_ID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "FILE_ID")
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "LOCATION_ID")
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "LOCATION_NAME")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Basic
    @Column(name = "LOCATION_COORDINATES")
    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
