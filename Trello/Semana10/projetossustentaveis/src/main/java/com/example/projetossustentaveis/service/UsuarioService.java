package com.example.projetossustentaveis.service;

import com.example.projetossustentaveis.entity.Usuario;
import com.example.projetossustentaveis.enums.Perfil;
import com.example.projetossustentaveis.exception.ResourceNotFoundException;
import com.example.projetossustentaveis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Username já existe: " + usuario.getUsername());
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getPerfil() == null) {
            usuario.setPerfil(Perfil.USER);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = findById(id);

        usuario.setNome(usuarioAtualizado.getNome());

        if (!usuario.getUsername().equals(usuarioAtualizado.getUsername())) {
            if (usuarioRepository.existsByUsername(usuarioAtualizado.getUsername())) {
                throw new RuntimeException("Username já existe: " + usuarioAtualizado.getUsername());
            }
            usuario.setUsername(usuarioAtualizado.getUsername());
        }

        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        if (usuarioAtualizado.getPerfil() != null) {
            usuario.setPerfil(usuarioAtualizado.getPerfil());
        }

        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }
}