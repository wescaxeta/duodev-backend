package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
    }

    public Usuario updateUsuario(int id, Usuario usuarioDetails) {
        Usuario usuario = getUsuarioById(id);
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setCpf(usuarioDetails.getCpf());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setSexo(usuarioDetails.getSexo());
        usuario.setDataNascimento(usuarioDetails.getDataNascimento());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }


    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario realizarLogin(Usuario usuarioLogin) {
        Usuario usuarioTestado = procuraUsuarioPorEmail(usuarioLogin.getEmail());
        if (usuarioTestado != null) {
            if (usuarioTestado.getSenha().equals(usuarioLogin.getSenha())) {
                return usuarioTestado;
            }
        }
        return null;
    }


    public Usuario procuraUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void resetPassword(String senha, String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);
    }
}