CREATE DATABASE IF NOT EXISTS rpg_db;
USE rpg_db;

Create TABLE jogador (
	id_jogador VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE personagem (
    id_personagem VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_maxima DOUBLE NOT NULL,
    xp DOUBLE DEFAULT 0.0,
    vida INT NOT NULL,
    forca INT NOT NULL,
    destreza INT NOT NULL,
    constituicao INT NOT NULL,
    inteligencia INT NOT NULL,
    sabedoria INT NOT NULL,
    carisma INT NOT NULL,
    id_jogador VARCHAR(36),
    
    CONSTRAINT fk_personagem_jogador 
        FOREIGN KEY (id_jogador) 
        REFERENCES jogador(id_jogador)
        ON DELETE CASCADE 
);

CREATE TABLE campanha (
    id_campanha VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE campanha_personagem (
    id_campanha VARCHAR(36),
    id_personagem VARCHAR(36),
    
    PRIMARY KEY (id_campanha, id_personagem),
    
    FOREIGN KEY (id_campanha) REFERENCES campanha(id_campanha) ON DELETE CASCADE,
    FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE
);