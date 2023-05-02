package com.iset.produits.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Categorie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCat;
  @NotNull
  @Size(min = 4, max = 15)
  private String nomCat;
  @NotNull
  @Size(min = 7, max = 40)
  private String descriptionCat;
  @OneToMany(mappedBy = "categorie")
  private List<Produit> produits;

  public Categorie() {
  }

  public Categorie(String nomCat, String descriptionCat, List<Produit> produits) {
    super();
    this.nomCat = nomCat;
    this.descriptionCat = descriptionCat;
    this.produits = produits;
  }

  public Long getIdCat() {
    return idCat;
  }

  public void setIdCat(Long idCat) {
    this.idCat = idCat;
  }

  public String getNomCat() {
    return nomCat;
  }

  public void setNomCat(String nomCat) {
    this.nomCat = nomCat;
  }

  public String getDescriptionCat() {
    return descriptionCat;
  }

  public void setDescriptionCat(String descriptionCat) {
    this.descriptionCat = descriptionCat;
  }

  public List<Produit> getProduits() {
    return produits;
  }

  public void setProduits(List<Produit> produits) {
    this.produits = produits;
  }
}