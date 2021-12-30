package com.hk.logging.config.eventlog;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventlog")
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String eventName;

    @Column(name = "user_id")
    long userId;

    @Column(name = "event_time")
    private java.sql.Timestamp eventTime;

    String route;

    String body;
}