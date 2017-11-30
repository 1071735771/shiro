package com.ideal.audit.sys.controller;

import com.ideal.audit.common.controller.BaseController;

/**
 * Created by jin on 2016/8/12.
 */
public class SysBaseController extends BaseController {
    public final static String PORTAL_PREFIX = "/sys";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }

}
