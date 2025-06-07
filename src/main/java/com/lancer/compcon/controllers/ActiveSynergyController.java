package com.lancer.compcon.controllers;

import java.util.List;

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

import com.lancer.compcon.models.ActiveSynergy;
import com.lancer.compcon.repository.ActiveSynergyRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/active-synergies")
public class ActiveSynergyController {

    private final ActiveSynergyRepository activeSynergyRepository;

    @Autowired
    public ActiveSynergyController(ActiveSynergyRepository activeSynergyRepository) {
        this.activeSynergyRepository = activeSynergyRepository;
    }

    // CREATE: Criar uma nova ActiveSynergy
    @PostMapping
    public ResponseEntity<ActiveSynergy> criarActiveSynergy(@RequestBody ActiveSynergy activeSynergy) {
        ActiveSynergy novaActiveSynergy = activeSynergyRepository.save(activeSynergy);
        return new ResponseEntity<>(novaActiveSynergy, HttpStatus.CREATED);
    }

    // READ: Obter todas as ActiveSynergies
    @GetMapping
    public ResponseEntity<List<ActiveSynergy>> obterTodasActiveSynergies() {
        List<ActiveSynergy> activeSynergies = activeSynergyRepository.findAll();
        if (activeSynergies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(activeSynergies, HttpStatus.OK);
    }

    // READ: Obter uma ActiveSynergy pelo ID (Long)
    @GetMapping("/{activeSynergyId}")
    public ResponseEntity<ActiveSynergy> obterActiveSynergyPorId(@PathVariable Long activeSynergyId) {
        return activeSynergyRepository.findById(activeSynergyId)
                .map(activeSynergy -> new ResponseEntity<>(activeSynergy, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE: Atualizar uma ActiveSynergy existente
    @PutMapping("/{activeSynergyId}")
    public ResponseEntity<ActiveSynergy> atualizarActiveSynergy(@PathVariable Long activeSynergyId, @RequestBody ActiveSynergy activeSynergyAtualizada) {
        return activeSynergyRepository.findById(activeSynergyId)
                .map(activeSynergyExistente -> {
                    activeSynergyExistente.setLocations(activeSynergyAtualizada.getLocations());
                    activeSynergyExistente.setDetail(activeSynergyAtualizada.getDetail());
                    // Atualize outros campos se houver
                    return new ResponseEntity<>(activeSynergyRepository.save(activeSynergyExistente), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Deletar uma ActiveSynergy
    @DeleteMapping("/{activeSynergyId}")
    public ResponseEntity<HttpStatus> deletarActiveSynergy(@PathVariable Long activeSynergyId) {
        try {
            if (!activeSynergyRepository.existsById(activeSynergyId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            activeSynergyRepository.deleteById(activeSynergyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}