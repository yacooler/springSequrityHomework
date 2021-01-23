package ru.vyazankin.homework.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "value")
    private Integer value;
}
