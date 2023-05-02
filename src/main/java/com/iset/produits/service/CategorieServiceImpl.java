package com.iset.produits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iset.produits.dao.CategorieRepository;
import com.iset.produits.entities.Categorie;

@Service
public class CategorieServiceImpl implements CategorieService {
  @Autowired
  CategorieRepository categorieRepository;

  @Override
  public List<Categorie> getAllCategories() {
    return categorieRepository.findAll();
  }

  @Override
  public Categorie saveCategorie(Categorie p) {
    return categorieRepository.save(p);
  }

  @Override
  public Categorie updateCategorie(Categorie p) {
    return categorieRepository.save(p);
  }

  // @Override
  // public void deleteCategorie(Categorie p) {
  // categorieRepository.delete(p);
  // }

  @Override
  public void deleteCategorieById(Long id) {
    categorieRepository.deleteById(id);
  }

  @Override
  public Categorie getCategorie(Long id) {
    return categorieRepository.findById(id).get();
  }

  @Override
  public Page<Categorie> getAllCategoriesParPage(int page, int size) {
    return categorieRepository.findAll(PageRequest.of(page, size));
  }

  // @Override
  // public List<Categorie> findByNomCategorie(String nom) {
  // return categorieRepository.findByNomCategorie(nom);
  // }

  @Override
  public List<Categorie> findByNomCatContains(String nom) {
    return categorieRepository.findByNomCatContains(nom);
  }

  // @Override
  // public Categorie findByProduit(Produit produit) {
  // return categorieRepository.findByProduit(produit);
  // }

  // @Override
  // public List<Categorie> findByCategorieidProduit(Long id) {
  // throw new UnsupportedOperationException("Unimplemented method
  // 'findByCategorieidProduit'");
  // }

  // @Override
  // public List<Categorie> findByOrderByNomCategorieAsc() {
  // throw new UnsupportedOperationException("Unimplemented method
  // 'findByOrderByNomCategorieAsc'");
  // }

  // @Override
  // public List<Categorie> trierCategoriesNoms() {
  // throw new UnsupportedOperationException("Unimplemented method
  // 'trierCategoriesNoms'");
  // }
}
