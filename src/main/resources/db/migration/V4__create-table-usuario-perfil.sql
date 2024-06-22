CREATE TABLE Usuario_Perfil (
                                usuario_id INT,
                                perfil_id INT,
                                PRIMARY KEY (usuario_id, perfil_id),
                                FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
                                FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);