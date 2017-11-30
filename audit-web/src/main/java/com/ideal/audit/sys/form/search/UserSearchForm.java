package com.ideal.audit.sys.form.search;

import com.ideal.audit.common.framework.SearchForm;

/**
 * Created by jin on 2017/9/16.
 */
public class UserSearchForm extends SearchForm {
    private String LIKE_userName;

    public String getLIKE_userName() {
        return LIKE_userName;
    }

    public void setLIKE_userName(String LIKE_userName) {
        this.LIKE_userName = LIKE_userName;
    }
}
