CREATE  TABLE  categoria(
  codigo BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  CONSTRAINT PK_CATEGORIA PRIMARY KEY (codigo),
  CONSTRAINT UC_NOME UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO algamoneyapi.categoria (nome) VALUES('Lazer');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Alimentação');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Supermercado');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Farmácia');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Outros');