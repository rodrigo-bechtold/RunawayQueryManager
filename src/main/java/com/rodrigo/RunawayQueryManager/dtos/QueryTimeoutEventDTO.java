package com.rodrigo.RunawayQueryManager.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class QueryTimeoutEventDTO {

    private String message;
    private String sql;
    private String stacktrace;
    private ZonedDateTime time;
}
