CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE livros(
id int primary key auto_increment,
nome varchar(200) NOT NULL unique,
autor varchar(250) NOT NULL
status VARCHAR(20) NOT NULL DEFAULT 'DISPONIVEL'
);
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    matricula VARCHAR(20) UNIQUE,
    telefone VARCHAR(15),
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(10) NOT NULL
);
CREATE TABLE emprestimos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_livro INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao_real DATE,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_livro) REFERENCES livros(id)
);