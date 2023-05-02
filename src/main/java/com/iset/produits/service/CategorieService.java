package com.iset.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.iset.produits.entities.Categorie;
import com.iset.produits.entities.Produit;

public interface CategorieService {
  List<Categorie> getAllCategories();

  Categorie saveCategorie(Categorie p);

  // Categorie updateCategorie(Categorie p);

  // void deleteCategorie(Categorie p);

  void deleteCategorieById(Long id);

  // Categorie getCategorie(Long id);

  Page<Categorie> getAllCategoriesParPage(int page, int size);

  // List<Categorie> findByNomCategorie(String nom);

  List<Categorie> findByNomCatContains(String nom);

  // Categorie findByProduit(Produit Produit);

  // List<Categorie> findByCategorieidProduit(Long id);

  // List<Categorie> findByOrderByNomCategorieAsc();

  // List<Categorie> trierCategoriesNoms();
}
