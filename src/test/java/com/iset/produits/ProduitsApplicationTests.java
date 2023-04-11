package com.iset.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entities.Categorie;
import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitServiceImpl;

@SpringBootTest
class ProduitsApplicationTests {

  @Autowired
  ProduitServiceImpl produitService;
  @Autowired
  ProduitRepository produitRepository;

  @Test
  public void testCreateProduit() {
    Produit prod = new Produit("PC Asus", 1500.500, new Date());
    produitService.saveProduit(prod);
  }

  @Test
  public void testFindProduit() {
    Produit prod = produitService.getProduit(1L);
    System.out.println(prod);
  }

  @Test
  public void testUpdateProduit() {
    Produit prod = produitService.getProduit(1L);
    prod.setPrixProduit(2000.0);
    produitService.updateProduit(prod);
  }

  @Test
  public void testDeleteProduit() {
    produitService.deleteProduitById(1L);
  }

  @Test
  public void testFindAllProduits() {
    List<Produit> prods = produitService.getAllProduits();
    for (Produit prod : prods)
      System.out.println(prod);
  }

  // @Test
  // public void testFindByNomProduitContains() {
  // Page<Produit> prods = produitService.getAllProduitsParPage(0, 2);
  // System.out.println(prods.getSize());
  // System.out.println(prods.getTotalElements());
  // System.out.println(prods.getTotalPages());
  // prods.getContent().forEach(p -> {
  // System.out.println(p.toString());
  // });
  // }

  @Test
  public void testFindByNomProduit() {
    List<Produit> prods = produitRepository.findByNomProduit("iphoneX");
    for (Produit p : prods) {
      System.out.println("testFindByNomProduit " + p.getNomProduit());
    }
  }

  @Test
  public void testFindByNomProduitContains() {
    List<Produit> prods = produitRepository.findByNomProduitContains("Asus");
    for (Produit p : prods) {
      System.out.println(p.getNomProduit());
    }
  }

  @Test
  public void testfindByNomPrix() {
    List<Produit> prods = produitRepository.findByNomPrix("iphone X",
        1000.0);
    for (Produit p : prods) {
      System.out.println(p);
    }
  }

  @Test
  public void testfindByCategorie() {
    Categorie cat = new Categorie();
    cat.setIdCat(1L);
    List<Produit> prods = produitRepository.findByCategorie(cat);
    for (Produit p : prods) {
      System.out.println(p);
    }
  }

  @Test
  public void findByCategorieIdCat() {
    List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
    for (Produit p : prods) {
      System.out.println(p);
    }
  }

  @Test
  public void testfindByOrderByNomProduitAsc() {
    List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();
    for (Produit p : prods) {
      System.out.println(p);
    }
  }

  @Test
  public void testTrierProduitsNomsPrix() {
    List<Produit> prods = produitRepository.trierProduitsNomsPrix();
    for (Produit p : prods) {
      System.out.println(p);
    }
  }
}
