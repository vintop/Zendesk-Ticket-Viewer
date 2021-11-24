package com.company.tests;

import com.company.datasources.ZendeskTicketsDatasource;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ZendeskTicketsDatasourceTest {

    @Test
    public void testExceptionFetchFalseTicketID(){
        String id = "-152353abfsb";
        ZendeskTicketsDatasource datasource = new ZendeskTicketsDatasource();

        try{
            datasource.getTicketByID(id);
        }catch(Exception e){
            Assert.assertNotNull(e);
            Assert.assertEquals(e.getClass(), Exception.class);
        }
    }

    @Test
    public void testFetchTicketByID(){
        String id = "1";
        ZendeskTicketsDatasource datasource = new ZendeskTicketsDatasource();
        JSONObject ticket = new JSONObject();

        ticket = datasource.getTicketByID(id);
        Assert.assertEquals(1, ticket.get("id"));
    }

    @Test
    public void testFetchAllTickets(){
        ZendeskTicketsDatasource datasource = new ZendeskTicketsDatasource();
        JSONObject tickets = new JSONObject();

        tickets = datasource.getAllTickets();
        Assert.assertTrue(tickets != null && !tickets.isEmpty());
    }
}
