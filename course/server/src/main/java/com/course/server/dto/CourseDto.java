package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDto {
    private String id;
    private String name;
    private String summary;
    private Integer time;
    private BigDecimal price;
    private String image;
    private String level;
    private String charge;
    private String status;
    private Integer enroll;
    private Integer sort;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+2")
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+2")
    private Date updatedAt;
    private List<CategoryDto> categorys = new ArrayList<>();
    private String teacherId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CategoryDto> getCategorys() {
        return categorys;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", level='" + level + '\'' +
                ", charge='" + charge + '\'' +
                ", status='" + status + '\'' +
                ", enroll=" + enroll +
                ", sort=" + sort +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", categorys=" + categorys +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }

    public void setCategorys(List<CategoryDto> categorys) {
        this.categorys = categorys;
    }

}
