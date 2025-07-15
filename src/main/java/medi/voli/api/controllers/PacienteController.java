package medi.voli.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voli.api.domain.pacientes.dto.DadosPacienteCadastro;
import medi.voli.api.domain.pacientes.dto.DadosParaAtualizar;
import medi.voli.api.domain.pacientes.dto.DetalhePacienteResponse;
import medi.voli.api.domain.pacientes.dto.GetPacientesResponse;
import medi.voli.api.domain.pacientes.repository.PacienteEntity;
import medi.voli.api.domain.pacientes.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhePacienteResponse> cadastraPaciente(@RequestBody @Valid DadosPacienteCadastro paciente, UriComponentsBuilder uriComponentsBuilder){
        var pacienteCriado = pacienteRepository.save(new PacienteEntity(paciente));
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacienteCriado.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhePacienteResponse(pacienteCriado));

    }

    @GetMapping
    public ResponseEntity<Page<GetPacientesResponse>> getPacientes(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao){
        var listaPacientes = pacienteRepository.findAllByAtivoTrue(paginacao).map(GetPacientesResponse::new);

        return ResponseEntity.ok(listaPacientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhePacienteResponse> getPacientes(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhePacienteResponse(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhePacienteResponse> atualizarPaciente(@RequestBody @Valid DadosParaAtualizar input){
        PacienteEntity paciente = pacienteRepository.getReferenceById(input.id());
        paciente.atualizarDados(input);
        pacienteRepository.save(paciente);

        return ResponseEntity.ok(new DetalhePacienteResponse(paciente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        PacienteEntity paciente = pacienteRepository.getReferenceById(id);
        paciente.deleteLogico();
        pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }
}
