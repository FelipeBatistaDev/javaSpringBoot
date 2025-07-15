package medi.voli.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import medi.voli.api.domain.consultas.AgendaDeConsultas;
import medi.voli.api.domain.consultas.dto.DadosAtualizarConsulta;
import medi.voli.api.domain.consultas.dto.DadosCadastroConsulta;
import medi.voli.api.domain.consultas.dto.DetalhesConsulta;
import medi.voli.api.domain.consultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesConsulta> agendar(@RequestBody DadosCadastroConsulta novaConsulta, UriComponentsBuilder uriComponentsBuilder){
        var consultaAgendada= agendaDeConsultas.agendar(novaConsulta);
        var uri = uriComponentsBuilder.path("/consultas/{id}").buildAndExpand(consultaAgendada.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesConsulta(consultaAgendada));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesConsulta>> listarConsultas(Pageable pageable){
        var consultas = this.consultaRepository.findAllByAtivoTrue(pageable);

        return ResponseEntity.ok(consultas.map(DetalhesConsulta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesConsulta> getConsultaById(@PathVariable Long id){
        var consulta = this.consultaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesConsulta(consulta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesConsulta> alteraConsulta(@RequestBody DadosAtualizarConsulta atualizacao){
        var consultaAtualizada = agendaDeConsultas.atualizar(atualizacao);

        return ResponseEntity.ok(new DetalhesConsulta(consultaAtualizada));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelaeConsulta(@PathVariable Long id){
        agendaDeConsultas.cancelarConsulta(id);

        return ResponseEntity.noContent().build();
    }


}
