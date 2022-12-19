package com.marcot.algamoneyapi.model;



import jakarta.persistence.*;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "categoria")
public class Categoria {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
}
