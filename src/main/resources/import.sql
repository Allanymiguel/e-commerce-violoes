-- Mocked data for Madeira
INSERT INTO madeira (id, tipo, densidade, sonoridade) VALUES (1, 'Jacaranda', 'Alta', 'Brilhante');
INSERT INTO madeira (id, tipo, densidade, sonoridade) VALUES (2, 'Cedro', 'Media', 'Aveludada');

-- Mocked data for Marcas
INSERT INTO marcas (id, nome, pais_origem, url_website) VALUES (1, 'Yamaha', 'Japao', 'https://www.yamaha.com');
INSERT INTO marcas (id, nome, pais_origem, url_website) VALUES (2, 'Fender', 'EUA', 'https://www.fender.com');

-- Mocked data for PerfilBraco
INSERT INTO perfilbraco (id, nome, formato, espessura) VALUES (1, 'Slim', 'C', 2.1);
INSERT INTO perfilbraco (id, nome, formato, espessura) VALUES (2, 'Thick', 'D', 2.5);

-- Mocked data for Acessorio
INSERT INTO acessorio (id, nome, descricao, quantidade_estoque, preco_unitario) VALUES (1, 'Capotraste', 'Capotraste para violão', 50, 29.90);
INSERT INTO acessorio (id, nome, descricao, quantidade_estoque, preco_unitario) VALUES (2, 'Correia', 'Correia de couro', 30, 49.90);

-- JOINED inheritance: insert into parent table first, then child table
INSERT INTO violao (id, nome, preco_base, ano_fabricacao, quantidade_estoque, id_madeira, id_marca, id_perfil_braco) VALUES (1, 'Folk Steel', 1200.00, 2022, 10, 1, 1, 1);
INSERT INTO violoes_aco (id, tipo_cordas_aco) VALUES (1, 'LEVE');

INSERT INTO violao (id, nome, preco_base, ano_fabricacao, quantidade_estoque, id_madeira, id_marca, id_perfil_braco) VALUES (2, 'Classico Nylon', 900.00, 2023, 5, 2, 2, 2);
INSERT INTO violoes_nylon (id, tipo_cordas_nylon) VALUES (2, 'MEDIA');

-- Mocked data for Violao_Acessorio (join table)
INSERT INTO violao_acessorio (id_violao, id_acessorio) VALUES (1, 1);
INSERT INTO violao_acessorio (id_violao, id_acessorio) VALUES (2, 2);
