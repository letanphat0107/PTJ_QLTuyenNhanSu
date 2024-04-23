package entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "certificates")
public class Certificate {

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
