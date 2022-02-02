package com.inrenping.instagram.entity.qcloud;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "T_INSTAGRAM_QUERY")
public class InstagramQuery {

    private long id;
    private long insId;
    private String type_name;
    private String short_code;
    private String display_url;
    private Long taken_at_timestamp;
    private long userId;
    private Long fileId;
    private Long locationId;
    private String locationName;
    private String remark;
    private Integer isChild;
    private Date createtime;
    private String endCursor;
    private Integer likeCount;

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
    @Column(name = "TYPE_NAME")
    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Basic
    @Column(name = "SHORT_CODE")
    public String getShort_code() {
        return short_code;
    }

    public void setShort_code(String short_code) {
        this.short_code = short_code;
    }

    @Basic
    @Column(name = "DISPLAY_URL")
    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
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
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "ISCHILD")
    public Integer getIsChild() {
        return isChild;
    }

    public void setIsChild(Integer isChild) {
        this.isChild = isChild;
    }

    @Basic
    @Column(name = "CREATETIME")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "END_CURSOR")
    public String getEndCursor() {
        return endCursor;
    }

    public void setEndCursor(String endCursor) {
        this.endCursor = endCursor;
    }

    @Basic
    @Column(name = "LIKE_COUNT")
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
