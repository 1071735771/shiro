package com.ideal.audit.framework;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jin on 2017/3/16.
 */
public class DataGridDto<T> {
    private long total;
    private List<T> rows;

    public DataGridDto(Page<T> page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }

    public DataGridDto(List<T> rows) {
        this.rows = rows;
        this.total = rows.size();
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
