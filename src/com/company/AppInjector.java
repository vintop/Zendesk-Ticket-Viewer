package com.company;

import com.company.datasources.ITicketsDatasource;
import com.company.datasources.ZendeskTicketsDatasource;
import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        //bind the datasource to implementation class
        bind(ITicketsDatasource.class).to(ZendeskTicketsDatasource.class);

    }

}
