package medi.voli.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voli.api.domain.medicos.dto.RetornoDetalhadoMedico;
import medi.voli.api.domain.medicos.dto.DadosCadastroMedico;
import medi.voli.api.domain.medicos.dto.DadosEditaveis;
import medi.voli.api.domain.medicos.dto.GetMedicosResponse;
import medi.voli.api.domain.medicos.repository.MedicoEntity;
import medi.voli.api.domain.medicos.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RetornoDetalhadoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico medico, UriComponentsBuilder uriBUilder){
        var cadastro = medicoRepository.save(new MedicoEntity(medico));
        var uri = uriBUilder.path("/medicos/{id}").buildAndExpand(cadastro.getId()).toUri();

        return ResponseEntity.created(uri).body(new RetornoDetalhadoMedico(cadastro));
    }

    @GetMapping
    public ResponseEntity<Page<GetMedicosResponse>> listar(@PageableDefault(size = 10, sort = {"nome"} ) Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(GetMedicosResponse::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new RetornoDetalhadoMedico(medico));
    }

    @PutMapping
    public ResponseEntity<RetornoDetalhadoMedico> editar(@RequestBody @Valid DadosEditaveis input){
       MedicoEntity medico = medicoRepository.getReferenceById(input.id());
       medico.atualizarInfos(input);
       medicoRepository.save(medico);


       return ResponseEntity.ok(new RetornoDetalhadoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        medicoRepository.save(medico);

        return ResponseEntity.noContent().build();
    }
}
