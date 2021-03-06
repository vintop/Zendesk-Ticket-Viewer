package com.company.usecases;

import com.company.repository.TicketsRepository;
import com.google.inject.Inject;
import org.json.JSONObject;

public class GetTicketsUsecase {

    private TicketsRepository repository;

    @Inject
    public GetTicketsUsecase(TicketsRepository repository){
        this.repository = repository;
    }

    public JSONObject getAllTickets() {
        return repository.getAllTickets();
    }

}
