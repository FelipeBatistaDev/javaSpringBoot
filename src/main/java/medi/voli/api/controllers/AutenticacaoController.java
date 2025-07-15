package medi.voli.api.controllers;

import jakarta.validation.Valid;
import medi.voli.api.domain.usuarios.dto.DadosToken;
import medi.voli.api.domain.usuarios.dto.FormLoginDto;
import medi.voli.api.domain.usuarios.repository.UsuariosEntity;
import medi.voli.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosToken> login(@RequestBody @Valid FormLoginDto login){
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.login(), login.password());
        var autentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuariosEntity) autentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }
}
