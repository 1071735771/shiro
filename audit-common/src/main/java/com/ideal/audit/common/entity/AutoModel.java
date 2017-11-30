package com.ideal.audit.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 * <p/>
 * 统一定义id的entity基类.
 * <p/>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类
 */
@MappedSuperclass
public class AutoModel implements Serializable{
    private Long id;

    public AutoModel() {
    }

    public AutoModel(Long id) {
        this.setId(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || other.getClass() != this.getClass()) return false;
        Object otherId = ((AutoModel) other).getId();
        if (id == null) return false;
        if (otherId == null) return false;
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }
}
