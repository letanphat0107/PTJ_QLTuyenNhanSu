package entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@lombok.Data
@Table(name = "certificates")
public class Certificate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "certificate_id")
    private String id;

    private String name;
    private String organization;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
