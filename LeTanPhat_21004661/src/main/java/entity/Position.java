package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "positions")
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "position_id")
    private String id;

    private String name;
    private String description;
    private double salary;
    private int number;

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", number=" + number +
                '}';
    }
}
