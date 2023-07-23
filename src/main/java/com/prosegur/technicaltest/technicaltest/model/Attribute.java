package com.prosegur.technicaltest.technicaltest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "_attribute", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})}
)
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String description;

    @Column(nullable = false)
    private int weight;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Value> values;
}
