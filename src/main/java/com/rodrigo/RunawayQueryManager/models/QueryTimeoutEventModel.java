package com.rodrigo.RunawayQueryManager.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Entity()
@Table(name = "QUERY_TIMEOUT_EVENT")
public class QueryTimeoutEventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 4000)
    private String message;

    @Column(length = 4000)
    private String sql;

    @Lob
    private String stacktrace;

    private ZonedDateTime time;
}
