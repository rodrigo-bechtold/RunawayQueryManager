package com.rodrigo.RunawayQueryManager.services;

import com.rodrigo.RunawayQueryManager.dtos.ResultDTO;
import org.hibernate.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;

@Service
public class QueryRunnerService {

    @PersistenceContext
    private EntityManager entityManager;


    public ResultDTO runQuery(Integer t) {
        String queryStr = "SELECT wait(:delay) FROM dual";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("delay", t);
        //query.setHint("javax.persistence.query.timeout", 20000);
        ResultDTO result = new ResultDTO((BigDecimal) query.getSingleResult());
        return result;
    }
}
