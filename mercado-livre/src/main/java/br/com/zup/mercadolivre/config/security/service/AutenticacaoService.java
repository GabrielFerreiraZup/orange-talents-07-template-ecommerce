package br.com.zup.mercadolivre.config.security.service;

import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(username);
        if(usuarioOpt.isPresent()) {
        return usuarioOpt.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
