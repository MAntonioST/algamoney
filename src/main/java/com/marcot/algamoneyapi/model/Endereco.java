package com.marcot.algamoneyapi.model;


import javax.persistence.*;

import lombok.*;


@Data
@Embeddable
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
