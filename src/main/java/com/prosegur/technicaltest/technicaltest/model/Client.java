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
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String firstSurname;

    @Column(length = 50)
    private String secondSurname;

    @Column(nullable = false)
    private String oriEntity;

    @Column(nullable = false, length = 9)
    private String dni;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_value",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id")
    )
    private Set<Value> values;
}
