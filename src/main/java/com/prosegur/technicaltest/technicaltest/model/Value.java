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
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToMany(mappedBy = "values", fetch = FetchType.LAZY)
    private Set<Client> clients;
}
