package com.iset.produits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Categorie;
import com.iset.produits.entities.Produit;

@Service
public class ProduitServiceImpl implements ProduitService {

  @Autowired
  ProduitRepository produitRepository;

  @Override
  public Produit saveProduit(Produit p) {
    return produitRepository.save(p);
  }

  @Override
  public Produit updateProduit(Produit p) {
    return produitRepository.save(p);
  }

  @Override
  public void deleteProduit(Produit p) {
    produitRepository.delete(p);
  }

  @Override
  public void deleteProduitById(Long id) {
    produitRepository.deleteById(id);
  }

  @Override
  public Produit getProduit(Long id) {
    return produitRepository.findById(id).get();
  }

  @Override
  public List<Produit> getAllProduits() {
    return produitRepository.findAll();
  }

  @Override
  public Page<Produit> getAllProduitsParPage(int page, int size) {
    return produitRepository.findAll(PageRequest.of(page, size));
  }

  @Override
  public List<Produit> findByNomProduit(String nom) {
    return produitRepository.findByNomProduit(nom);
  }

  @Override
  public List<Produit> findByNomProduitContains(String nom) {
    return produitRepository.findByNomProduitContains(nom);
  }

  @Override
  public List<Produit> findByNomPrix(String nom, Double prix) {
    return produitRepository.findByNomPrix(nom, prix);

  }

  @Override
  public List<Produit> findByCategorie(Categorie categorie) {
    return produitRepository.findByCategorie(categorie);

  }

  @Override
  public List<Produit> findByCategorieIdCat(Long id) {
    return produitRepository.findByCategorieIdCat(id);

  }

  @Override
  public List<Produit> findByOrderByNomProduitAsc() {
    return produitRepository.findByOrderByNomProduitAsc();

  }

  @Override
  public List<Produit> trierProduitsNomsPrix() {
    return produitRepository.trierProduitsNomsPrix();

  }
}
