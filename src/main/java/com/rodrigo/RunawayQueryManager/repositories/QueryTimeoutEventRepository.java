package com.rodrigo.RunawayQueryManager.repositories;

import com.rodrigo.RunawayQueryManager.models.QueryTimeoutEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface QueryTimeoutEventRepository extends JpaRepository<QueryTimeoutEventModel, Long> {

    @QueryHints(value = { @QueryHint(name = "javax.persistence.query.timeout", value = "3000")}, forCounting = false)
    public List<QueryTimeoutEventModel> findBySql(String sql);
}
