package com.lancer.compcon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancer.compcon.models.Frames;
import com.lancer.compcon.models.Trait;
import com.lancer.compcon.repository.FramesRepository;
import com.lancer.compcon.repository.TraitRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

// --- Controller para a entidade Trait ---
// Relevante se Traits puderem ser gerenciados independentemente.
// Se Traits são sempre parte de um Frame, seu CRUD pode ser gerenciado
// através do FramesController (ex: POST ).
@RestController
@RequestMapping("/api/frames/{framesId}/traits")
public class TraitController {

    private final TraitRepository traitRepository;
    private final FramesRepository framesRepository;

    @Autowired
    public TraitController(TraitRepository traitRepository, FramesRepository framesRepository) {
        this.traitRepository = traitRepository;
        this.framesRepository = framesRepository;
    }

    @PostMapping("/frame/{framesId}")
    public ResponseEntity<Trait> criarTrait(@PathVariable String framesId, @RequestBody Trait trait) {
        Optional<Frames> frameOptional = framesRepository.findById(framesId);
        if (!frameOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Frame não encontrado
        }
        trait.setFrame(frameOptional.get()); // Supondo que Trait tem um campo 'frame'
        Trait novoTrait = traitRepository.save(trait);
        return new ResponseEntity<>(novoTrait, HttpStatus.CREATED);
    }

    // READ: Obter todos os Traits
    @GetMapping
    public ResponseEntity<List<Trait>> obterTodosTraits() {
        List<Trait> traits = traitRepository.findAll();
        if (traits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(traits, HttpStatus.OK);
    }

    // READ: Obter um Trait pelo ID (Long)
    @GetMapping("/{traitId}")
    public ResponseEntity<Trait> obterTraitPorId(@PathVariable Long traitId) {
        return traitRepository.findById(traitId)
                .map(trait -> new ResponseEntity<>(trait, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE: Atualizar um Trait existente
    @PutMapping("/{traitId}")
    public ResponseEntity<Trait> atualizarTrait(@PathVariable Long traitId, @RequestBody Trait traitAtualizado) {
        return traitRepository.findById(traitId)
                .map(traitExistente -> {
                    traitExistente.setTraits_name(traitAtualizado.getTraits_name());
                    traitExistente.setDescription(traitAtualizado.getDescription());
                    // Atualize outros campos se houver
                    return new ResponseEntity<>(traitRepository.save(traitExistente), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Deletar um Trait
    @DeleteMapping("/{traitId}")
    public ResponseEntity<HttpStatus> deletarTrait(@PathVariable Long traitId) {
        try {
            if (!traitRepository.existsById(traitId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            traitRepository.deleteById(traitId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
