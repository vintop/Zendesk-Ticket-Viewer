package com.company.datasources;

import org.json.JSONObject;

public interface ITicketsDatasource {
    public JSONObject getAllTickets();

    public JSONObject getTicketByID(String ticketID);
}
