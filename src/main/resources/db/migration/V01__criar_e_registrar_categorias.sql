CREATE  TABLE  categoria(
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO algamoneyapi.categoria (nome) VALUES('Lazer');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Alimentação');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Supermercado');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Farmácia');
INSERT INTO algamoneyapi.categoria (nome) VALUES('Outros');