package br.com.wsp.votems.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ruling")
public class Ruling implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "created_by")
    @NonNull
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @NonNull
    private Timestamp createAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "validated")
    private Timestamp validated;
}
