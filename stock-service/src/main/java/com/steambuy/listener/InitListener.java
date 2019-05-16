package com.steambuy.listener;

import com.steambuy.request.RequestExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        RequestExecutor instacne = RequestExecutor.INSTACNE;
    }
}
