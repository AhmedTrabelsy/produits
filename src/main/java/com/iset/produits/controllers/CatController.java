package com.iset.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iset.produits.entities.Categorie;
import com.iset.produits.entities.Produit;
import com.iset.produits.service.CategorieService;
import com.iset.produits.service.ProduitService;

import jakarta.validation.Valid;

@Controller
public class CatController {
  @Autowired
  ProduitService produitService;
  @Autowired
  CategorieService categorieService;

  @GetMapping("/showCreateProduit")
  public String showCreateProduit(ModelMap modelMap) {
    List<Categorie> categories = categorieService.getAllCategories();

    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("produit", new Produit());
    return "modifierProduit";
  }

  @PostMapping("/saveProduit")
  public String saveProduit(@Valid Produit produit, @RequestParam("categorieProduit") Long categorieProduit,
      BindingResult bindingResult, ModelMap modelMap)
      throws ParseException {

    if (bindingResult.hasErrors()) {
      return "modifierProduit";
    }

    List<Categorie> categories = categorieService.getAllCategories();
    Categorie categorie = categorieService.getCategorie(categorieProduit);
    produit.setCategorie(categorie);

    // conversion de la date
    // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    // Date dateCreation =
    // dateformat.parse(String.valueOf(produit.getDateCreation()));
    // produit.setDateCreation(dateCreation);
    Produit saveProduit = produitService.saveProduit(produit);
    String msg = "Produit enregistré avec Id " + saveProduit.getIdProduit();

    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("msg", msg);
    return "modifierProduit";
  }

  @GetMapping("/ListeProduits")
  public String listeProduits(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size) {
    Page<Produit> produits = produitService.getAllProduitsParPage(page, size);
    modelMap.addAttribute("produits", produits);
    modelMap.addAttribute("pages", new int[produits.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    return "listeProduits";
  }

  @GetMapping("/ListeProduitsByName")
  public String listeProduitsByName(ModelMap modelMap, @RequestParam("nom") String name) {
    List<Produit> produits = produitService.findByNomProduitContains(name);
    List<Produit> all_produits = produitService.getAllProduits();

    if (produits.isEmpty()) {
      modelMap.addAttribute("msg", "Pas de produits avec se clé !");
      modelMap.addAttribute("produits", all_produits);
    } else {
      modelMap.addAttribute("produits", produits);
      modelMap.addAttribute("searchTerm", name);
    }

    return "listeProduits";
  }

  // @GetMapping("/ListeProduitsByName")
  // public String listeProduitsByName(ModelMap modelMap, @RequestParam(name =
  // "nom", defaultValue = "") String nom) {
  // List<Produit> produits;
  // produits = produitService.getAllProduits();
  // // if (nom.isEmpty()) {
  // // } else {
  // // produits = produitService.findByNomProduitContains(nom);
  // // }
  // modelMap.addAttribute("produits", produits);
  // modelMap.addAttribute("searchTerm", nom); // add the search term to the model
  // return "listeProduits";
  // }

  @RequestMapping("/supprimerProduit")
  public String supprimerProduit(@RequestParam("id") Long produit_id, ModelMap modelMap,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size) {
    produitService.deleteProduitById(produit_id);

    Page<Produit> produits = produitService.getAllProduitsParPage(page, size);
    modelMap.addAttribute("produits", produits);
    modelMap.addAttribute("pages", new int[produits.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);

    String msg = "Produit supprimé ";
    modelMap.addAttribute("msg", msg);
    return "listeProduits";
  }

  @RequestMapping("/modifierProduit")
  public String modifierProduit(@RequestParam("id") Long produit_id, ModelMap modelMap) {
    List<Categorie> categories = categorieService.getAllCategories();
    Produit prod = produitService.getProduit(produit_id);

    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("produit", prod);
    return "modifierProduit";
  }

  @RequestMapping("/updateProduit")
  public String updateProduit(@ModelAttribute("produit") Produit new_produit, @RequestParam("date") String date,
      @RequestParam("categorieProduit") Long categorieProduit,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size,
      ModelMap modelMap) throws ParseException {
    Produit old_produit = produitService.getProduit(new_produit.getIdProduit());
    if (date != "" && old_produit.getDateCreation() != new_produit.getDateCreation()) {
      // conversion de la date
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      Date dateCreation = dateformat.parse(String.valueOf(date));
      old_produit.setDateCreation(dateCreation);
    }

    if (old_produit.getNomProduit() != new_produit.getNomProduit() && new_produit.getNomProduit().length() > 0) {
      old_produit.setNomProduit(new_produit.getNomProduit());
    }
    if (old_produit.getPrixProduit() != new_produit.getPrixProduit() && new_produit.getPrixProduit() > 0D) {
      old_produit.setPrixProduit(new_produit.getPrixProduit());
    }
    if (old_produit.getCategorie().getIdCat() != categorieProduit && categorieProduit > 0D) {
      System.out.println("hello world !");
      Categorie categorie = categorieService.getCategorie(categorieProduit);
      System.out.println("qsdfqsdfqsdfq " + categorie.getNomCat());
      old_produit.setCategorie(categorie);
    }
    produitService.updateProduit(old_produit);

    Page<Produit> produits = produitService.getAllProduitsParPage(page, size);
    modelMap.addAttribute("produits", produits);
    modelMap.addAttribute("pages", new int[produits.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);

    String msg = "Produit modifié";
    modelMap.addAttribute("msg", msg);
    return "listeProduits";
  }
}
