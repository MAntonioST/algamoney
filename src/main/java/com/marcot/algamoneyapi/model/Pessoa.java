package com.marcot.algamoneyapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "pessoa")
public class Pessoa {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;
    @Embedded
    private Endereco endereco;
    @NotNull
    private Boolean ativo;

    @JsonIgnore
    @Transient
    public boolean isInativo(){
        return !this.ativo;
    }

}
