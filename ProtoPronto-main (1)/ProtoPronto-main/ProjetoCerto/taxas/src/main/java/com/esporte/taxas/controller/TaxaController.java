package com.esporte.taxas.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esporte.taxas.DAO.UITeste;
import com.esporte.taxas.model.TaxaModel;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("taxas")
public class TaxaController {

    @Autowired
    private UITeste dao;

    private static final int MAX_IMAGE_SIZE = 16777215; // 16MB

    // Método para validar o tamanho da imagem
    private boolean validarImagemBase64(String imagemBase64) {
        return imagemBase64.length() <= MAX_IMAGE_SIZE;
    }

    // Endpoint POST para criar uma nova taxa
    @PostMapping
    public ResponseEntity<TaxaModel> criarTaxa(@RequestBody TaxaModel taxa) {
        try {
            // Valida a imagem antes de salvar
            if (!validarImagemBase64(taxa.getImagem())) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Tamanho da imagem excede o limite
            }

            TaxaModel novaTaxa = dao.save(taxa);
            return new ResponseEntity<>(novaTaxa, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log da exceção
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint GET para listar todas as taxas
    @GetMapping
    public List<TaxaModel> listarTaxas() {
        return (List<TaxaModel>) dao.findAll();
    }

    // Endpoint GET para buscar uma taxa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<TaxaModel> buscarTaxaPorId(@PathVariable Long id) {
        Optional<TaxaModel> taxa = dao.findById(id);
        return taxa.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint PUT para atualizar uma taxa existente
    @PutMapping("/{id}")
    public ResponseEntity<TaxaModel> atualizarTaxa(@PathVariable Long id, @RequestBody TaxaModel taxa) {
        if (!dao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Valida a imagem antes de atualizar
        if (!validarImagemBase64(taxa.getImagem())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Tamanho da imagem excede o limite
        }

        taxa.setId(id); // Certifique-se de definir o ID para a atualização
        TaxaModel taxaAtualizada = dao.save(taxa);
        return ResponseEntity.ok(taxaAtualizada);
    }

    // Endpoint DELETE para excluir uma taxa
    @DeleteMapping("/{Id}")
    @Transactional
    public Optional<TaxaModel> ExcluirPartida(@PathVariable Long Id) {
    	Optional<TaxaModel> roundE = dao.findById(Id);
    	dao.deleteById(Id);
    	return roundE;
  }
}
