package com.rodrigo.RunawayQueryManager.services;

import com.rodrigo.RunawayQueryManager.models.QueryTimeoutEventModel;
import com.rodrigo.RunawayQueryManager.repositories.QueryTimeoutEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryTimeoutEventService {

    private QueryTimeoutEventRepository queryTimeoutEventRepository;

    public QueryTimeoutEventService(QueryTimeoutEventRepository queryTimeoutEventRepository) {
        this.queryTimeoutEventRepository = queryTimeoutEventRepository;
    }

    public void save(QueryTimeoutEventModel queryTimeoutEventModel) {
        queryTimeoutEventRepository.save(queryTimeoutEventModel);
    }

    public List<QueryTimeoutEventModel> findAll() {
        return queryTimeoutEventRepository.findAll();
    }

}
