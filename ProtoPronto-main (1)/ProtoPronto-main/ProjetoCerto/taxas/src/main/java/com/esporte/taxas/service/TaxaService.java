package com.esporte.taxas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esporte.taxas.model.TaxaModel;
import com.esporte.taxas.repository.TaxaRepositor;

@Service
public class TaxaService {

    @Autowired
    private TaxaRepositor repositor;

    public static final int MAX_IMAGE_SIZE = 16777215; // 16MB, ajustando conforme o limite da coluna

    /**
     * Valida o tamanho da imagem Base64
     *
     * @param imagemBase64 A imagem codificada em Base64.
     * @return true se a imagem estiver dentro do tamanho permitido, false caso contrário.
     */
    public boolean validarImagemBase64(String imagemBase64) {
        return imagemBase64.length() <= MAX_IMAGE_SIZE;
    }

    /**
     * Salva uma nova partida (TaxaModel) no banco de dados.
     * Realiza a validação da imagem antes de salvar.
     *
     * @param taxa A partida (taxa) a ser salva.
     * @return A partida salva, ou uma exceção se a imagem for inválida.
     */
    public TaxaModel salvar(TaxaModel taxa) {
        if (!validarImagemBase64(taxa.getImagem())) {
            throw new IllegalArgumentException("Imagem Base64 excede o limite de tamanho de 16MB.");
        }
        return repositor.save(taxa);
    }

    /**
     * Retorna todas as partidas (taxas) salvas.
     *
     * @return Lista de todas as partidas.
     */
    public List<TaxaModel> listarTodos() {
        return repositor.findAll();
    }

    /**
     * Atualiza uma partida existente no banco de dados.
     *
     * @param id   O ID da partida a ser atualizada.
     * @param taxa A nova partida com os dados atualizados.
     * @return A partida atualizada, ou null se não encontrada.
     */
    public TaxaModel atualizar(Long id, TaxaModel taxa) {
        Optional<TaxaModel> existingTaxa = repositor.findById(id);
        if (existingTaxa.isPresent()) {
            if (!validarImagemBase64(taxa.getImagem())) {
                throw new IllegalArgumentException("Imagem Base64 excede o limite de tamanho de 16MB.");
            }
            taxa.setId(id);
            return repositor.save(taxa);
        }
        return null;
    }

    /**
     * Deleta uma partida pelo seu ID.
     *
     * @param id O ID da partida a ser deletada.
     */
    public void deletar(Long id) {
        repositor.deleteById(id);
    }

    /**
     * Busca uma partida pelo seu ID.
     *
     * @param id O ID da partida a ser buscada.
     * @return A partida encontrada ou null se não encontrada.
     */
    public TaxaModel buscarPorId(Long id) {
        return repositor.findById(id).orElse(null); // Retorna a partida ou null se não encontrada
    }
}
