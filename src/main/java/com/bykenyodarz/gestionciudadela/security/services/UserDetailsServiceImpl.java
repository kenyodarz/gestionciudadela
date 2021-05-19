package com.bykenyodarz.gestionciudadela.security.services;

import com.bykenyodarz.gestionciudadela.security.models.Usuario;
import com.bykenyodarz.gestionciudadela.security.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("No se entro el nombre de Usuario: " + nombreUsuario));
        return UserDetailsImpl.build(usuario);
    }
}
