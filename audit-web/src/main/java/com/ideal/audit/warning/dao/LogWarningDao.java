package com.ideal.audit.warning.dao;

import com.ideal.audit.warning.entity.log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ybb on 2017/10/11.
 */
@Repository
/*public interface LogWarningDao extends JpaRepository<LogWarning,Long>  {
    @Query(value = "select * from log_warning_infor order by id desc ", nativeQuery = true)
    public List<LogWarning> getLogWarningInfo() ;
}*/
public class LogWarningDao {

    @PersistenceContext
    EntityManager entityManager = null;

    @PersistenceContext
    private EntityManager em;

    public Page<log> getInfo(log log, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        /**
         * 这里从数据只能转换成number类型的变量
         */
        Number length =  (Number)countQuery.getResultList().get(0);
        List<com.ideal.audit.warning.entity.log> data = dataQuery.getResultList();
        long totalSize = length.longValue();
        Page<com.ideal.audit.warning.entity.log> page = new PageImpl<com.ideal.audit.warning.entity.log>(data, pager,totalSize);
        return page;
    }
}
