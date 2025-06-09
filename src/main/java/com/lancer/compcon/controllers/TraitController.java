package com.lancer.compcon.controllers;

import com.lancer.compcon.models.Trait;
import com.lancer.compcon.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/traits")
public class TraitController {

    private final TraitRepository traitRepository;

    // Injeção de dependência via construtor
    // Sem isso o codigo nao funcionou
    @Autowired
    public TraitController(TraitRepository traitRepository) {
        this.traitRepository = traitRepository;
    }

    // GET /api/traits -> Retorna todos os traits
    @GetMapping
    public List<Trait> obterTodosTraits() {
        return traitRepository.findAll();
    }

    // GET /api/traits/{id} -> Retorna um trait específico pelo seu ID
    @GetMapping("/{id}")
    public ResponseEntity<Trait> obterTraitPorId(@PathVariable Long id) {
        Trait trait = traitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trait não encontrado com id: " + id));
        return ResponseEntity.ok(trait);
    }

    /**
     * GET /api/traits/search?frameId={id_do_frame}
     */
    @GetMapping("/search")
    public List<Trait> obterTraitsPorFrameId(@RequestParam String frameId) {
        return traitRepository.findByFrame_Frames_id(frameId);
    }

    /**
     * POST /api/traits -> Cria um novo trait.
     */
    @PostMapping
    public ResponseEntity<Trait> criarTrait(@RequestBody Trait trait) {
        Trait novoTrait = traitRepository.save(trait);
        return new ResponseEntity<>(novoTrait, HttpStatus.CREATED);
    }

    // PUT /api/traits/{id} -> Atualiza um trait existente
    @PutMapping("/{id}")
    public ResponseEntity<Trait> atualizarTrait(@PathVariable Long id, @RequestBody Trait traitDetalhes) {
        Trait traitExistente = traitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trait não encontrado com id: " + id));

        traitExistente.setTraits_name(traitDetalhes.getTraits_name());
        traitExistente.setDescription(traitDetalhes.getDescription());

        final Trait traitAtualizado = traitRepository.save(traitExistente);
        return ResponseEntity.ok(traitAtualizado);
    }

    // DELETE /api/traits/{id} -> Deleta um trait
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarTrait(@PathVariable Long id) {
        if (!traitRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        traitRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
