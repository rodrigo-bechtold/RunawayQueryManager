package com.rodrigo.RunawayQueryManager.controllers;

import com.rodrigo.RunawayQueryManager.dtos.ResultDTO;
import com.rodrigo.RunawayQueryManager.models.QueryTimeoutEventModel;
import com.rodrigo.RunawayQueryManager.services.QueryRunnerService;
import com.rodrigo.RunawayQueryManager.services.QueryTimeoutEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    public QueryRunnerService queryRunner;

    @Autowired
    public QueryTimeoutEventService queryTimeoutEventService;

    @GetMapping()
    public ResultDTO runQuery(Integer t) {
        return queryRunner.runQuery(t);
    }

    @GetMapping("/timeouts")
    public List<QueryTimeoutEventModel> getTimeouts() {
        return queryTimeoutEventService.findAll();
    }
}
