package entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "experiences")
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
