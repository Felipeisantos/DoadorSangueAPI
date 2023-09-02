CREATE DATABASE IF NOT EXISTS BancoDeSangue;

use BancoDeSangue;

CREATE TABLE `contato` (
  `id` BIGINT  NOT NULL,
  `email` varchar(255) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `numero` int NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` char(2) NOT NULL,
  `telefone_fixo` varchar(15) DEFAULT NULL,
  `celular` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `informacoesfisicas` (
  `id` BIGINT  NOT NULL,
  `altura` decimal(4,2) NOT NULL,
  `peso` decimal(5,2) NOT NULL,
  `tipo_sanguineo` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pessoa` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `data_nasc` date NOT NULL,
  `sexo` enum('Masculino','Feminino','Outro') NOT NULL,
  `mae` varchar(255) DEFAULT NULL,
  `pai` varchar(255) DEFAULT NULL,
  `contato_id` BIGINT  DEFAULT NULL,
  `informacoes_fisicas_id` BIGINT  DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contato_id` (`contato_id`),
  KEY `informacoes_fisicas_id` (`informacoes_fisicas_id`),
  CONSTRAINT `pessoa_ibfk_1` FOREIGN KEY (`contato_id`) REFERENCES `contato` (`id`),
  CONSTRAINT `pessoa_ibfk_2` FOREIGN KEY (`informacoes_fisicas_id`) REFERENCES `informacoesfisicas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(255)
);
CREATE TABLE analise_candidato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_requisicao TIMESTAMP NOT NULL,
    caminho_json VARCHAR(255) NOT NULL,
    nome_arquivo VARCHAR(255),
    solicitacao_usuario_id BIGINT,
    FOREIGN KEY (solicitacao_usuario_id) REFERENCES usuario (id)
);