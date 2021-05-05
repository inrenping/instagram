package com.inrenping.instagram.entity.qcloud;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="T_LOG")
public class Log {
    private long id;
    private String httpHeader;
    private String ip;
    private Date createtime;
    private String remark;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name="HTTP_HEADER")
    public String getHttpHeader() {
        return httpHeader;
    }

    public void setHttpHeader(String httpHeader) {
        this.httpHeader = httpHeader;
    }

    @Basic
    @Column(name="IP")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name="CREATETIME")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name="REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
