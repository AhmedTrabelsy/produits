package com.iset.produits.entities;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Produit {
  @Id
  private long idProduit;
  private String nomProduit;
  private Double prixProduit;
  private Date dateCreation;
}
