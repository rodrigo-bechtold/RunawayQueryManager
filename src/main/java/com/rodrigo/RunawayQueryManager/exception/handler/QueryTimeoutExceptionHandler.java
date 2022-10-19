package com.rodrigo.RunawayQueryManager.exception.handler;

import com.rodrigo.RunawayQueryManager.dtos.QueryTimeoutEventDTO;
import com.rodrigo.RunawayQueryManager.sender.QueueSender;
import com.rodrigo.RunawayQueryManager.services.QueryTimeoutEventService;
import oracle.jdbc.OracleDatabaseException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;


@RestControllerAdvice
public class QueryTimeoutExceptionHandler {

    @Autowired
    private QueryTimeoutEventService queryTimeoutEventService;

    @Autowired
    private QueueSender sender;

    @ExceptionHandler({QueryTimeoutException.class})
    public ResponseEntity handleQueryTimeout(Throwable ex) {
        sendTimeoutEvent(ex);
        return new ResponseEntity<Object>("Query was cancelled due to timeout", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendTimeoutEvent(Throwable ex) {
        OracleDatabaseException oex = getOracleException(ex);

        if (oex != null) {
            QueryTimeoutEventDTO queryTimeoutEvent = new QueryTimeoutEventDTO();
            queryTimeoutEvent.setSql(oex.getSql());
            queryTimeoutEvent.setTime(ZonedDateTime.now());
            queryTimeoutEvent.setMessage(oex.getMessage());
            queryTimeoutEvent.setStacktrace(ExceptionUtils.getStackTrace(oex));
            sender.send(queryTimeoutEvent);
        }
    }

    private OracleDatabaseException getOracleException(Throwable ex) {
        if (ex == null) return null;

        if (ex instanceof OracleDatabaseException) {
            return (OracleDatabaseException) ex;
        } else {
            return getOracleException(ex.getCause());
        }
    }

}
