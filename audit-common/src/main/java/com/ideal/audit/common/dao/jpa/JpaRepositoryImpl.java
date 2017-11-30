package com.ideal.audit.common.dao.jpa;

import com.ideal.audit.common.dao.mapper.BeanMapper;
import com.ideal.audit.common.framework.SearchForm;
import com.ideal.audit.common.util.ClassUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.repository.query.QueryUtils.DELETE_ALL_QUERY_STRING;
import static org.springframework.data.jpa.repository.query.QueryUtils.getQueryString;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 */
public class JpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements JpaRepository<T, ID> {
    private  JpaEntityInformation<T, ?> entityInformation = null;
    private  EntityManager em = null;
    protected Logger logger = LoggerFactory.getLogger(JpaRepositoryImpl.class);
    public static final String READ_ALL_QUERY = "select x from %s x";

    public JpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    public JpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass,em), em);

    }

    public int deleteInId(List<ID> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        String ql = getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName());
        logger.debug(ql);
        return applyAndBind(ql, ids, em).executeUpdate();
    }

    public List<T> findAllById(List<ID> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<T>();
        }
        String ql = getQueryString(READ_ALL_QUERY, entityInformation.getEntityName());
        logger.debug(ql);
        return applyAndBind(ql, ids, em).getResultList();
    }

    public Query applyAndBind(String queryString, List<ID> ids, EntityManager entityManager) {
        Assert.notNull(queryString);
        Assert.notNull(ids);
        Assert.notNull(entityManager);

        String alias = QueryUtils.detectAlias(queryString);
        StringBuilder builder = new StringBuilder(queryString);
        builder.append(" where ");
        builder.append(String.format(" %s.id IN(?1)", alias));

        Query query = entityManager.createQuery(builder.toString());
        query.setParameter(1, ids);
        return query;
    }

    /**
     * SQL查询 自定义绑定,分页
     * @param sql
     * @param pageable
     * @param c
     * @param fields
     * @return
     */
    @Override
    public Page<T> findAllBySql(String sql,Pageable pageable,Class<T> c,String[] fields){
        try {
            Query query = em.createNativeQuery(sql);
            int total = getCount(sql);
            query.setMaxResults(pageable.getPageSize());
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            List<Object[]> content = query.getResultList();
            List<T> list = new ArrayList<T>();
            for (Object[] result : content) {
                T obj = c.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    ClassUtils.setFieldValue(obj, fields[i], result[i]);
                }
                list.add(obj);
            }
            return new PageImpl<T>(list, pageable, total);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private int getCount(String sql){
        sql = String.format("select count(1) from (%s)",sql);
        Query query = em.createNativeQuery(sql);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public int getCount(String sql, List<Object> params) {
        sql = String.format("select count(1) from (%s) _t",sql);
        Query query = em.createNativeQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (int i=0; i < params.size(); i++) {
                query.setParameter(i + 1, params.get(i));
            }
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public Page<T> findAllBySql(String sql,Pageable pageable,Class<T> c){
        Query query = em.createNativeQuery(sql,c);
        int total = getCount(sql);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
        List<T> content = query.getResultList();
        return new PageImpl<T>(content,pageable,total);
    }

    /**
     * SQL查询 自定义绑定,不分页
     * @param sql
     * @param c
     * @param fields
     * @return
     */
    @Override
    public List<T> findAllBySql(String sql,Class<T> c,String[] fields) {
        try {
            Query query = em.createNativeQuery(sql);
            List<Object[]> content = query.getResultList();
            List<T> list = new ArrayList<T>();
            for (Object[] result : content) {
                T obj = c.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    ClassUtils.setFieldValue(obj, fields[i], result[i]);
                }
                list.add(obj);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * sql查询，带参数的--分页
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @param pageable
     * @return
     */
    @Override
    public <T> Page<T> selectAllBySql(String sql, List<Object> params, Class<T> cls,Pageable pageable) {
        Query query= em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (params != null && !params.isEmpty()) {
            for (int i=0; i < params.size(); i++) {
                query.setParameter(i + 1, params.get(i));
            }
        }
        int total = getCount(sql,params);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
        List<Map> content = query.getResultList();
        return new PageImpl<T>(BeanMapper.mapList(content, cls),pageable,total);
    }

    /**
     * sql查询，带参数的--不分页
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> selectAllBySql(String sql, List<Object> params, Class<T> cls) {
        Query query= em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (params != null && !params.isEmpty()) {
            for (int i=0; i < params.size(); i++) {
                query.setParameter(i + 1, params.get(i));
            }
        }
        List<Map> list =  query.getResultList();
        return BeanMapper.mapList(list, cls);
    }

    /**
     * 执行DML/DDL语句（无参数）
     * @param sql
     * @return
     */
    @Override
    public int executeUpdateBySql(String sql){
        Query query = em.createNativeQuery(sql);
        return query.executeUpdate();
    }

    /**
     * 执行DML/DDL语句（有参数）
     * @param sql
     * @param params
     * @return
     */
    @Override
    public int executeUpdateBySql(String sql,List<Object> params){
        Query query = em.createNativeQuery(sql);
        for(int i = 1 ;i<=params.size();i++){
            query.setParameter(i,params.get(i-1));
        }
        return query.executeUpdate();
    }

    public <I extends SearchForm> Page<T> findPageBySearchForm(I searchForm){
        Map<String,SearchFilter> filters = SearchFilter.parse(searchForm);
        Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return findAll(spec,searchForm.asPageable());
    }

    public <I extends SearchForm> List<T> findListBySearchForm(I searchForm){
        Map<String,SearchFilter> filters = SearchFilter.parse(searchForm);
        Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return findAll(spec);
    }
}
