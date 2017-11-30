package com.ideal.audit.common.framework;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by jin on 15/11/18.
 */
public class SearchForm {

    private Integer rows = 10;
    private Integer page = 0;
    private String columns ;
    private String sort;
    private String order;
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public Pageable asPageable(){
        if(StringUtils.isNotEmpty(sort)){
            Sort.Direction direction = Sort.Direction.ASC;
            if("desc".equals(order)){
                direction = Sort.Direction.DESC;
            }
            return new PageRequest(page-1, rows,direction,sort);
        }
        return new PageRequest(page-1, rows);
    }

}
