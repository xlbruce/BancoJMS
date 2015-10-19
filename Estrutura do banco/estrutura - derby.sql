CREATE TABLE cliente (
    nro_conta INT NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    saldo DOUBLE NOT NULL,
    senha VARCHAR(15) NOT NULL
);

INSERT INTO cliente (nro_conta, nome, saldo, senha) 
    VALUES (1010, 'Gilson', 1000, 'flash05');

INSERT INTO cliente (nro_conta, nome, saldo, senha) 
    VALUES (1015, 'Bia Buani', 1000, 'bia123');