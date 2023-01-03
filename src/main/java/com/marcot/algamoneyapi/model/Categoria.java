package com.marcot.algamoneyapi.model;



import jakarta.persistence.*;

import jakarta.validation.constraints.*;

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

    @NotNull @NotBlank
    @Size(min = 3, max = 20)
    private String nome;
}
