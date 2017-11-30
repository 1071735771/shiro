package com.ideal.audit.common.dao.jpa;

import com.ideal.audit.common.framework.SearchForm;
import com.ideal.audit.common.util.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SearchFilter {
    //eq相等 LIKE模糊查询 neq不相等， gt大于， lt小于 gte大于等于 lte 小于等于 not非 mod求模
    public enum Operator {
        EQ, LIKE, GT, LT, GTE, LTE, IN, NEQ,NULL,NOTNULL
    }

    private String fieldName;
    private Object value;
    private Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

        for (Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                continue;
            }

            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                continue;
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }

    public static <T extends SearchForm> Map<String, SearchFilter> parse(T searchForm) {
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        try {
            Map<String,Object> searchParams = ClassUtils.getFieldValMap(searchForm);
            //将第二个后的所有_替换成.
            Map<String,Object> params = new HashMap<String, Object>();
            for (Map.Entry<String,Object> entry : searchParams.entrySet()) {
                String key = entry.getKey();
                if(key.split("_").length>1) {
                    key = key.substring(0, key.indexOf("_") + 1).concat(key.substring(key.indexOf("_") + 1).replaceAll("_", "."));
                    params.put(key, entry.getValue());
                }
            }
            filters = parse(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filters;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }
}
