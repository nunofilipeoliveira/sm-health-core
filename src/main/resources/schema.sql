-- Script SQL para criar a tabela 'run' no MariaDB
-- Para executar: conecte-se ao servidor MariaDB e execute este script

-- Criação do banco de dados (opcional, se não existir)
CREATE DATABASE IF NOT EXISTS sm_health_core CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Selecionar o banco de dados
USE sm_health_core;

-- Criação da tabela 'run'
CREATE TABLE IF NOT EXISTS run (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    date VARCHAR(10) NOT NULL,
    time INT NOT NULL,
    distance DOUBLE NOT NULL,
    notes VARCHAR(500),
    created_at DATETIME NOT NULL,
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Comentários sobre as colunas
-- id: UUID gerado automaticamente (36 caracteres)
-- date: Data da corrida no formato 'YYYY-MM-DD'
-- time: Tempo da corrida em segundos
-- distance: Distância percorrida em km
-- notes: Notas adicionais (máximo 500 caracteres)
-- created_at: Data e hora de criação do registro

-- Exemplo de inserção de dados (opcional)
-- INSERT INTO run (id, date, time, distance, notes, created_at)
-- VALUES (UUID(), '2024-03-21', 3600, 10.5, 'Corrida matutina no parque', NOW());