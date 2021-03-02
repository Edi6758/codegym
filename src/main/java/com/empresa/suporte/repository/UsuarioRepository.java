package com.empresa.suporte.repository;

import com.empresa.suporte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByLogin(String login);
    public Usuario findByCpf(String cpf);
    public Usuario findByTelefone(String telefone);
    public Usuario findByEmail(String email);

    
}
