package com.iset.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.iset.produits.entities.Produit;
import com.iset.produits.service.ProduitServiceImpl;

@SpringBootTest
class ProduitsApplicationTests {

  @Autowired
  ProduitServiceImpl produitService;

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

  @Test
  public void testFindByNomProduitContains() {
    Page<Produit> prods = produitService.getAllProduitsParPage(0, 2);
    System.out.println(prods.getSize());
    System.out.println(prods.getTotalElements());
    System.out.println(prods.getTotalPages());
    prods.getContent().forEach(p -> {
      System.out.println(p.toString());
    });
  }
}
