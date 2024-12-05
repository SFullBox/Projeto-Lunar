package com.esporte.taxas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe de modelo que representa uma partida e suas estatísticas.
 * Contém informações como o resultado, número de rounds, abates, mortes, headshots (HS), e imagem.
 */
@Entity
@Table(name = "partidas")
public class TaxaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Identificador único da partida

    @Column(name = "resultado", nullable = true)
    private String resultado; // Resultado da partida (ex: vitória, derrota, empate)

    @Column(name = "round", nullable = true)
    private int round; // Número do round da partida

    @Column(name = "abates", nullable = true)
    private int abates; // Quantidade de abates realizados

    @Column(name = "mortes", nullable = true)
    private int mortes; // Quantidade de mortes sofridas

    @Column(name = "hs", nullable = true)
    private int hs; // Quantidade de headshots (HS) realizados

    @Column(name = "imagem", columnDefinition = "LONGTEXT")
    private String imagem; // Imagem vinculada à partida (pode ser Base64)

    // Construtor padrão
    public TaxaModel() {}

    /**
     * Construtor com todos os parâmetros.
     *
     * @param id        Identificador único da partida
     * @param resultado Resultado da partida
     * @param round     Número do round
     * @param abates    Quantidade de abates
     * @param mortes    Quantidade de mortes
     * @param hs        Quantidade de headshots
     * @param imagem    Imagem vinculada à partida
     */
    public TaxaModel(Long id, String resultado, int round, int abates, int mortes, int hs, String imagem) {
        this.id = id;
        this.resultado = resultado;
        this.round = round;
        this.abates = abates;
        this.mortes = mortes;
        this.hs = hs;
        this.imagem = imagem;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getAbates() {
        return abates;
    }

    public void setAbates(int abates) {
        this.abates = abates;
    }

    public int getMortes() {
        return mortes;
    }

    public void setMortes(int mortes) {
        this.mortes = mortes;
    }

    public int getHs() {
        return hs;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
