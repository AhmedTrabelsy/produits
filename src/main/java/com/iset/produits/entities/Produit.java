package com.iset.produits.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Produit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idProduit;
  @NotNull
  @Size(min = 4, max = 15)
  private String nomProduit;
  @Min(value = 10)
  @Max(value = 10000)
  private Double prixProduit;
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent
  private Date dateCreation;
  @ManyToOne
  private Categorie categorie;

  public Categorie getCategorie() {
    return categorie;
  }

  public void setCategorie(Categorie categorie) {
    this.categorie = categorie;
  }

  public Produit() {
  }

  public Produit(String nomProduit, Double prixProduit, Date dateCreation) {
    this.nomProduit = nomProduit;
    this.prixProduit = prixProduit;
    this.dateCreation = dateCreation;
  }

  public long getIdProduit() {
    return idProduit;
  }

  public void setIdProduit(long idProduit) {
    this.idProduit = idProduit;
  }

  public String getNomProduit() {
    return nomProduit;
  }

  public void setNomProduit(String nomProduit) {
    this.nomProduit = nomProduit;
  }

  public Double getPrixProduit() {
    return prixProduit;
  }

  public void setPrixProduit(Double prixProduit) {
    this.prixProduit = prixProduit;
  }

  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  @Override
  public String toString() {
    return "Produit [idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", prixProduit=" + prixProduit
        + ", dateCreation=" + dateCreation + "]";
  }
}
