package com.company.tests;

import com.company.datasources.ITicketsDatasource;
import com.company.datasources.ZendeskTicketsDatasource;
import com.company.repository.TicketsRepository;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TicketsRepositoryTest {

    public TicketsRepository getRepository(){
        ITicketsDatasource dataSource = new ZendeskTicketsDatasource();
        TicketsRepository ticketsRepository = new TicketsRepository(dataSource);
        return ticketsRepository;
    }

    @Test
    public void testExceptionFetchFalseTicketID(){
        String id = "152353abfsb";

        try{
            getRepository().getTicketByID(id);
        }catch(Exception e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testFetchTicketByID(){
        String id = "1";
        JSONObject ticket = new JSONObject();

        ticket = getRepository().getTicketByID(id);
        Assert.assertEquals(1, ticket.get("id"));
    }

    @Test
    public void testFetchAllTickets(){
        JSONObject tickets = new JSONObject();

        tickets = getRepository().getAllTickets();
        Assert.assertTrue(tickets != null && !tickets.isEmpty());
    }

}
