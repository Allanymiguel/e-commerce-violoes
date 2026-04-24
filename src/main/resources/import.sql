-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
INSERT INTO marcas (nome, pais_origem, url_website) VALUES ('Takamine', 'Japão', 'www.Takamine.com');
INSERT INTO marcas (nome, pais_origem, url_website) VALUES ('Yamaha', 'Japão', 'www.Yamaha.com');
-- alter sequence myentity_seq restart with 4;

-- Mocked data for Madeira
INSERT INTO madeira (id, tipo, densidade, sonoridade) VALUES (1, 'Jacaranda', 'Alta', 'Brilhante');
INSERT INTO madeira (id, tipo, densidade, sonoridade) VALUES (2, 'Cedro', 'Média', 'Aveludada');

-- Mocked data for Marcas
INSERT INTO marcas (id, nome, pais_origem, url_website) VALUES (1, 'Yamaha', 'Japão', 'https://www.yamaha.com');
INSERT INTO marcas (id, nome, pais_origem, url_website) VALUES (2, 'Fender', 'EUA', 'https://www.fender.com');

-- Mocked data for PerfilBraco
INSERT INTO perfilbraco (id, nome, formato, espessura) VALUES (1, 'Slim', 'C', 2.1);
INSERT INTO perfilbraco (id, nome, formato, espessura) VALUES (2, 'Thick', 'D', 2.5);

-- Mocked data for Acessorio
INSERT INTO acessorio (id, nome, descricao, quantidade_estoque, preco_unitario) VALUES (1, 'Capotraste', 'Capotraste para violão', 50, 29.90);
INSERT INTO acessorio (id, nome, descricao, quantidade_estoque, preco_unitario) VALUES (2, 'Correia', 'Correia de couro', 30, 49.90);

-- Mocked data for ViolaoAco
INSERT INTO violoes_aco (id, nome, preco_base, ano_fabricacao, id_madeira, id_marca, id_perfil_braco, tipo_cordas_aco) VALUES (1, 'Folk Steel', 1200.00, 2022, 1, 1, 1, 'LEVE');

-- Mocked data for ViolaoNylon
INSERT INTO violoes_nylon (id, nome, preco_base, ano_fabricacao, id_madeira, id_marca, id_perfil_braco, tipo_cordas_nylon) VALUES (2, 'Clássico Nylon', 900.00, 2023, 2, 2, 2, 'MEDIA');

-- Mocked data for Violao_Acessorio (join table)
INSERT INTO violao_acessorio (id_violao, id_acessorio) VALUES (1, 1);
INSERT INTO violao_acessorio (id_violao, id_acessorio) VALUES (2, 2);
