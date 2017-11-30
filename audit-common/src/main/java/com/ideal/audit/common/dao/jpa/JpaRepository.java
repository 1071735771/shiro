package com.ideal.audit.common.dao.jpa;

import com.ideal.audit.common.framework.SearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 */
public interface JpaRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

    int deleteInId(List<ID> ids);

    List<T> findAllById(List<ID> ids);

    /**
     * 原生sql查询，返回分页集合--自定义绑定,可以映射对象中非数据库的字段
     * @param sql
     * @param pageable
     * @param c
     * @param fields
     * @return
     */
    Page<T> findAllBySql(String sql, Pageable pageable, Class<T> c, String[] fields);

    /**
     * 原生sql查询，返回分页集合
     * @param sql
     * @param pageable
     * @param c
     * @return
     */
    Page<T> findAllBySql(String sql, Pageable pageable, Class<T> c);

    /**
     * sql查询，带参数的--不分页
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     */
    <T> List<T> selectAllBySql(String sql, List<Object> params, Class<T> cls);

    /**
     * sql查询，带参数的--分页
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @param pageable
     * @return
     */
    <T> Page<T> selectAllBySql(String sql, List<Object> params, Class<T> cls,Pageable pageable);

    /**
     * SQL查询 自定义绑定,可以映射对象中非数据库的字段
     * @param sql
     * @param c
     * @param fields
     * @return
     */
    List<T> findAllBySql(String sql, Class<T> c, String[] fields);

    /**
     * 执行DML/DDL语句（无参数）
     * @param sql
     * @return
     */
    int executeUpdateBySql(String sql);

    /**
     * 执行DML/DDL语句（有参数）
     * @param sql
     * @param params
     * @return
     */
    int executeUpdateBySql(String sql, List<Object> params);

    <I extends SearchForm> List<T> findListBySearchForm(I searchForm);

    <I extends SearchForm> Page<T> findPageBySearchForm(I searchForm);
}
