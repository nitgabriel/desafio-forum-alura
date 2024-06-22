CREATE TABLE Topico (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensagem TEXT NOT NULL,
                        dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(255),
                        autor INT,
                        curso INT,
                        FOREIGN KEY (autor) REFERENCES Usuario(id),
                        FOREIGN KEY (curso) REFERENCES Curso(id)
);