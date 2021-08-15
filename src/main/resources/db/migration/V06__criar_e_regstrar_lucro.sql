CREATE TABLE lucro(
    codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	codigo_produto BIGINT NOT NULL,
	lucro_obtido DECIMAL (10,2) NOT NULL,
	quantidade_vendida BIGINT NOT NULL,
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (1,870,2);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (2,-127,3);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (3,615.36,2);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (4,2099,3);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (5,1550,5);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (6,200,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (7,200,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (8,200,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (9,348.8,3);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (10,46,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (11,144.36,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (12,330.94,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (13,100,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (14,370,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (15,69,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (16,60.5,1);
INSERT INTO lucro (codigo_produto, lucro_obtido, quantidade_vendida) VALUES (17,89.9,1);