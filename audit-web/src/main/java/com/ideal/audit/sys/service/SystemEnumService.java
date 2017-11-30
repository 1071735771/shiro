package com.ideal.audit.sys.service;

import com.ideal.audit.sys.dao.ISystemEnumDao;
import com.ideal.audit.sys.entity.SystemEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class SystemEnumService {

    @Resource
    private ISystemEnumDao iSystemEnumDao;

    public List<SystemEnum> findSystemEnumByGroup(Long group) {
        return iSystemEnumDao.findSystemEnumByGroup(group);
    }
}
