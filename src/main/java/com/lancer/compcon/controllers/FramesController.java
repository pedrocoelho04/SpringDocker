package com.lancer.compcon.controllers;

import com.lancer.compcon.models.Frames;
import com.lancer.compcon.repository.FramesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/frames")
public class FramesController {

    private final FramesRepository framesRepository;

    @Autowired
    public FramesController(FramesRepository framesRepository) {
        this.framesRepository = framesRepository;
    }

    @PostMapping
    public ResponseEntity<Frames> criarFrame(@RequestBody Frames frame) {
        Frames novoFrame = framesRepository.save(frame);
        return new ResponseEntity<>(novoFrame, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Frames>> obterTodosFrames() {
        List<Frames> frames = framesRepository.findAll();
        if (frames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(frames, HttpStatus.OK);
    }

    // Endpoint para buscar pelo ID numérico
    // Exemplo de uso: /api/frames/1
    @GetMapping("/{id}")
    public ResponseEntity<Frames> obterFramePorId(@PathVariable Long id) {
        Optional<Frames> frameData = framesRepository.findById(id);
        return frameData.map(frame -> new ResponseEntity<>(frame, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Busca pelo ID de negocio
    // Exemplo de uso: /api/frames/by_id/gpc_genghis
    @GetMapping("/by_id/{frames_id}")
    public ResponseEntity<Frames> obterFramePeloIdString(@PathVariable String frames_id) {
        Optional<Frames> frameData = framesRepository.findByFrames_id(frames_id);
        return frameData.map(frame -> new ResponseEntity<>(frame, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //O endpoint agora usa o 'id' numérico para encontrar o frame a ser atualizado
    @PutMapping("/{id}")
    public ResponseEntity<Frames> atualizarFrame(@PathVariable Long id, @RequestBody Frames frameAtualizado) {
        Optional<Frames> frameExistenteData = framesRepository.findById(id);

        if (frameExistenteData.isPresent()) {
            Frames frameExistente = frameExistenteData.get();
            
            // Atualiza os campos...
            frameExistente.setFrames_id(frameAtualizado.getFrames_id()); // Continua a atualizar o ID de string
            frameExistente.setLicensed_level(frameAtualizado.getLicensed_level());
            frameExistente.setSource(frameAtualizado.getSource());
            frameExistente.setFrames_name(frameAtualizado.getFrames_name());
            frameExistente.setMech_type(frameAtualizado.getMech_type());
            frameExistente.setY_pos(frameAtualizado.getY_pos());
            frameExistente.setDescription(frameAtualizado.getDescription());
            frameExistente.setMounts(frameAtualizado.getMounts());
            frameExistente.setStats(frameAtualizado.getStats());
            frameExistente.setTraits(frameAtualizado.getTraits());
            frameExistente.setCore_systems(frameAtualizado.getCore_systems());
            frameExistente.setImage_url(frameAtualizado.getImage_url());
            frameExistente.setLicensed_id(frameAtualizado.getLicensed_id());

            return new ResponseEntity<>(framesRepository.save(frameExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //O endpoint agora usa o 'id' numérico para deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarFrame(@PathVariable Long id) {
        if (!framesRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        framesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}