package com.esporte.taxas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.esporte.taxas.model.TaxaModel;

public interface TaxaRepositor extends JpaRepository<TaxaModel, Long> {

}
