package com.lancer.compcon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import com.lancer.compcon.models.Frames;
import com.lancer.compcon.models.Trait;
import com.lancer.compcon.repository.FramesRepository;

// Controller para Frames
@RestController
@RequestMapping("/api/frames")
public class FramesController {

    private final FramesRepository framesRepository;

    @Autowired
    public FramesController(FramesRepository framesRepository) {
        this.framesRepository = framesRepository;
    }

    // CREATE: Criar um novo Frame
    @PostMapping
    public ResponseEntity<Frames> criarFrame(@RequestBody Frames frame) {
        // Validações adicionais podem ser adicionadas aqui (ex: se o frame_id já existe)
        // Se o frames_id for gerado pelo banco, não precisa ser enviado no corpo.
        // Se for definido pelo cliente, pode ser necessário verificar a unicidade.
        Frames novoFrame = framesRepository.save(frame);
        return new ResponseEntity<>(novoFrame, HttpStatus.CREATED);
    }

    // READ: Obter todos os Frames
    @GetMapping
    public ResponseEntity<List<Frames>> obterTodosFrames() {
        List<Frames> frames = framesRepository.findAll();
        if (frames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(frames, HttpStatus.OK);
    }

    // READ: Obter um Frame pelo ID (frames_id)
    @GetMapping("/{framesId}")
    public ResponseEntity<Frames> obterFramePorId(@PathVariable String framesId) {
        Optional<Frames> frameData = framesRepository.findById(framesId);
        return frameData.map(frame -> new ResponseEntity<>(frame, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        // Alternativa com ResponseStatusException:
        // return framesRepository.findById(framesId)
        //        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Frame não encontrado com id: " + framesId));
    }

    // UPDATE: Atualizar um Frame existente
    @PutMapping("/{framesId}")
    public ResponseEntity<Frames> atualizarFrame(@PathVariable String framesId, @RequestBody Frames frameAtualizado) {
        Optional<Frames> frameExistenteData = framesRepository.findById(framesId);

        if (frameExistenteData.isPresent()) {
            Frames frameExistente = frameExistenteData.get();
            // Atualiza os campos do frameExistente com os valores do frameAtualizado
            // É importante definir quais campos podem ser atualizados e como.
            // O frames_id geralmente não é atualizado.
            frameExistente.setLicensed_level(frameAtualizado.getLicensed_level());
            frameExistente.setSource(frameAtualizado.getSource());
            frameExistente.setFrames_name(frameAtualizado.getFrames_name());
            frameExistente.setMech_type(frameAtualizado.getMech_type());
            frameExistente.setY_pos(frameAtualizado.getY_pos());
            frameExistente.setDescription(frameAtualizado.getDescription());
            frameExistente.setMounts(frameAtualizado.getMounts());
            frameExistente.setStats(frameAtualizado.getStats()); // Assume que Stats é atualizado como um todo
            frameExistente.setTraits(frameAtualizado.getTraits()); // Pode precisar de lógica mais complexa para gerenciar coleções
            frameExistente.setCore_systems(frameAtualizado.getCore_systems()); // Assume que CoreSystem é atualizado como um todo
            frameExistente.setImage_url(frameAtualizado.getImage_url());
            frameExistente.setLicensed_id(frameAtualizado.getLicensed_id());

            return new ResponseEntity<>(framesRepository.save(frameExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Deletar um Frame
    @DeleteMapping("/{framesId}")
    public ResponseEntity<HttpStatus> deletarFrame(@PathVariable String framesId) {
        try {
            if (!framesRepository.existsById(framesId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            framesRepository.deleteById(framesId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Logar a exceção e retornar um erro interno do servidor
            // logger.error("Erro ao deletar frame com id: " + framesId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}