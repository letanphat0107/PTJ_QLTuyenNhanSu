package entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    private String gender;
    private String email;
    private String phone;
    private String description;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth;
    }
}
