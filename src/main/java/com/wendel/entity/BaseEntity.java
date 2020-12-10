package com.wendel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wendel.utils.UuidUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@SuppressWarnings("all")
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 858294042419172707L;
    private static final String[] ignores = {"insertUser", "insertTime", "updateUser", "updateTime", "deleteFlag"};

    /**
     * 全局唯一编号
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(name = "INSERT_TIME")
    private LocalDateTime insertTime;
    /**
     * 创建人
     */
    @JsonIgnore
    @Column(name = "INSERT_USER")
    private String insertUser;

    /**
     * 更新时间
     */
    @JsonIgnore
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @JsonIgnore
    @Column(name = "UPDATE_USER")
    private String updateUser;
    /**
     * 删除状态（0-未删除，1-已删除）
     */
    @JsonIgnore
    @Column(name = "DELETE_FLAG")
    private Boolean deleteFlag;

    public BaseEntity buildForInsert() {
        if (StringUtils.isBlank(this.id)) {
            this.setId(UuidUtils.base58Uuid());
        }

        if (Objects.isNull(this.insertUser)) {
//            this.setInsertUser(this.getCurrUser());
        }

        if (Objects.isNull(this.insertTime)) {
            this.setInsertTime(LocalDateTime.now());
        }

        if (Objects.isNull(this.updateTime)) {
            this.setUpdateTime(LocalDateTime.now());
        }

        if (this.deleteFlag == null) {
            this.setDeleteFlag(false);
        }

        return this;
    }
//
//    public BaseEntity buildForInsert(BaseEntityInfo info) {
//        if (StringUtils.isBlank(this.id)) {
//            this.setId(UuidUtils.base58Uuid());
//        }
//
//        if (Objects.isNull(this.insertUser)) {
//            this.setInsertUser(info.getUserId());
//        }
//
//        if (Objects.isNull(this.insertTime)) {
//            this.setInsertTime(LocalDateTime.now());
//        }
//
//        if (Objects.isNull(this.updateTime)) {
//            this.setUpdateTime(LocalDateTime.now());
//        }
//
//        if (this.deleteFlag == null) {
//            this.setDeleteFlag(false);
//        }
//
//        return this;
//    }
//
//    public BaseEntity buildForInsertEver() {
//        this.setId(UuidUtils.base58Uuid());
//        this.setInsertUser(this.getCurrUser());
//        this.setInsertTime(LocalDateTime.now());
//        this.setUpdateTime(LocalDateTime.now());
//        this.setDeleteFlag(false);
//        return this;
//    }
//
//    public BaseEntity buildForInsertEver(BaseEntityInfo info) {
//        this.setId(UuidUtils.base58Uuid());
//        this.setInsertUser(info.getUserId());
//        this.setInsertTime(LocalDateTime.now());
//        this.setUpdateTime(LocalDateTime.now());
//        this.setDeleteFlag(false);
//        return this;
//    }
//
//    public BaseEntity buildForUpdate() {
//        String currId = this.getCurrUser();
//        if (StringUtils.isNotEmpty(currId))
//            this.setUpdateUser(currId);
//        this.setUpdateTime(LocalDateTime.now());
//        return this;
//    }
//
//    public BaseEntity buildForUpdate(BaseEntityInfo info) {
//        if (StringUtils.isNotEmpty(info.getUserId()))
//            this.setUpdateUser(info.getUserId());
//        this.setUpdateTime(LocalDateTime.now());
//        return this;
//    }

//    public BaseEntity buildForDel() {
//        String currId = this.getCurrUser();
//        if (StringUtils.isNotEmpty(currId))
//            this.setUpdateUser(currId);
//        this.setUpdateTime(LocalDateTime.now());
//        this.setDeleteFlag(true);
//        return this;
//    }
//
//    public BaseEntity buildForDel(BaseEntityInfo info) {
//        if (StringUtils.isNotEmpty(info.getUserId()))
//            this.setUpdateUser(info.getUserId());
//        this.setUpdateTime(LocalDateTime.now());
//        this.setDeleteFlag(true);
//        return this;
//    }

    public void build(Object vo) {
        BeanUtils.copyProperties(vo, this);
    }

    public void buildIgnore(Object vo) {
        BeanUtils.copyProperties(vo, this, ignores);
    }
}