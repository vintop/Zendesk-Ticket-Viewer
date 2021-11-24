package com.company.repository;

import com.company.datasources.ITicketsDatasource;
import com.google.inject.Inject;
import org.json.JSONObject;

public class TicketsRepository {

    private ITicketsDatasource datasource;

    @Inject
    public TicketsRepository(ITicketsDatasource datasource){
        this.datasource = datasource;
    }

    public JSONObject getAllTickets() {
        return datasource.getAllTickets();
    }

    public JSONObject getTicketByID(String ticketID) {
        return datasource.getTicketByID(ticketID);
    }
}
