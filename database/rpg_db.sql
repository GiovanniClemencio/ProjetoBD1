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
    nome VARCHAR(100) NOT NULL,
    id_mestre VARCHAR(36),

    CONSTRAINT fk_campanha_mestre
        FOREIGN KEY (id_mestre)
        REFERENCES jogador(id_jogador)
        ON DELETE SET NULL
);

CREATE TABLE campanha_personagem (
    id_campanha VARCHAR(36),
    id_personagem VARCHAR(36),
    
    PRIMARY KEY (id_campanha, id_personagem),
    
    FOREIGN KEY (id_campanha) REFERENCES campanha(id_campanha) ON DELETE CASCADE,
    FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE
);

CREATE TABLE item (
    id_item VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    raridade VARCHAR(50) NOT NULL,
    custo DOUBLE NOT NULL DEFAULT 0.0,
    peso DOUBLE NOT NULL DEFAULT 0.0
);

CREATE TABLE inventario (
    id_personagem VARCHAR(36),
    id_item VARCHAR(36),
    quantidade INT NOT NULL DEFAULT 1,
    
    PRIMARY KEY (id_personagem, id_item),
    
    FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES item(id_item) ON DELETE CASCADE
);

CREATE TABLE classe (
    id_classe VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE classe_personagem (
    id_personagem VARCHAR(36),
    id_classe VARCHAR(36),
    nivel_classe INT NOT NULL DEFAULT 1,
    
    PRIMARY KEY (id_personagem, id_classe),
    FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE,
    FOREIGN KEY (id_classe) REFERENCES classe(id_classe) ON DELETE CASCADE
);

CREATE TABLE monstro (
    id_monstro VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    tipo VARCHAR(50),
    vida INT NOT NULL,
    forca INT NOT NULL,
    destreza INT NOT NULL,
    constituicao INT NOT NULL,
    inteligencia INT NOT NULL,
    sabedoria INT NOT NULL,
    carisma INT NOT NULL,
    cr INT NOT NULL
);

CREATE TABLE item (
    id_item VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    raridade VARCHAR(50),
    custo DOUBLE DEFAULT 0.0,
    peso DOUBLE DEFAULT 0.0,
    descricao TEXT
);

CREATE TABLE monstro_drop (
    id_monstro VARCHAR(36),
    id_item VARCHAR(36),
    quantidade INT NOT NULL DEFAULT 1,
    
    PRIMARY KEY (id_monstro, id_item),
    FOREIGN KEY (id_monstro) REFERENCES monstro(id_monstro) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES item(id_item) ON DELETE CASCADE
);

CREATE TABLE missao (
    id_missao VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    id_mestre VARCHAR(36),
    xp_bonus INT DEFAULT 0,
    FOREIGN KEY (id_mestre) REFERENCES jogador(id_jogador) ON DELETE SET NULL
);

CREATE TABLE campanha_missao_personagem (
    id_campanha VARCHAR(36),
    id_missao VARCHAR(36),
    id_personagem VARCHAR(36),
    
    PRIMARY KEY (id_campanha, id_missao, id_personagem),
    FOREIGN KEY (id_campanha) REFERENCES campanha(id_campanha) ON DELETE CASCADE,
    FOREIGN KEY (id_missao) REFERENCES missao(id_missao) ON DELETE CASCADE,
    FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE
);

CREATE TABLE missao_monstro (
    id_missao VARCHAR(36),
    id_monstro VARCHAR(36),
    quantidade INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_missao, id_monstro),
    FOREIGN KEY (id_missao) REFERENCES missao(id_missao) ON DELETE CASCADE,
    FOREIGN KEY (id_monstro) REFERENCES monstro(id_monstro) ON DELETE CASCADE
);

CREATE TABLE missao_item (
    id_missao VARCHAR(36),
    id_item VARCHAR(36),
    quantidade INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_missao, id_item),
    FOREIGN KEY (id_missao) REFERENCES missao(id_missao) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES item(id_item) ON DELETE CASCADE
);

CREATE TABLE campanha_missao (
    id_campanha VARCHAR(36),
    id_missao VARCHAR(36),
    concluido BOOLEAN NOT NULL DEFAULT FALSE,
    
    PRIMARY KEY (id_campanha, id_missao),
    FOREIGN KEY (id_campanha) REFERENCES campanha(id_campanha) ON DELETE CASCADE,
    FOREIGN KEY (id_missao) REFERENCES missao(id_missao) ON DELETE CASCADE
);