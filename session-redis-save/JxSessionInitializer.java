package com.uvlab.cloud.site.service.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by Administrator on 2016/11/15 0015.
 */


public class JxSessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public JxSessionInitializer() {
        super(JxSessionConfig.class);
    }
}
