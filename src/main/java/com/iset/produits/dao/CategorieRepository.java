package com.iset.produits.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.produits.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
  // List<Categorie> findByNomCategorie(String nom);

  List<Categorie> findByNomCatContains(String nom);

  // @Query("select p from Categorie p where p.produit = ?1")
  // Categorie findByProduit(Produit produit);

  // List<Categorie> findByCategorieIdCat(Long id);

  // List<Categorie> findByOrderByNomCategorieAsc();

  // @Query("select p from Categorie p order by p.nomCategorie ASC")
  // List<Categorie> trierProduitsNomsPrix();
}
