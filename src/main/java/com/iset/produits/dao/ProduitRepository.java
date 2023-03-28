package com.iset.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iset.produits.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
