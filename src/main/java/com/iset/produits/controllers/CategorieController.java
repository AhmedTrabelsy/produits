package com.iset.produits.controllers;

import java.text.ParseException;
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
import com.iset.produits.service.CategorieService;

import jakarta.validation.Valid;

@Controller
public class CategorieController {
  @Autowired
  CategorieService categorieService;

  @GetMapping("/ListeCategories")
  public String listeCategories(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size) {
    Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
        size);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    return "listeCategories";
  }

  @GetMapping("/showCreateCategorie")
  public String showCreateCategorie(ModelMap modelMap) {
    modelMap.addAttribute("categorie", new Categorie());
    return "createCategorie";
  }

  @PostMapping("/saveCategorie")
  public String saveCategorie(@Valid Categorie categorie, BindingResult bindingResult, ModelMap modelMap)
      throws ParseException {

    if (bindingResult.hasErrors()) {
      return "createCategorie";
    }
    Categorie saveCategorie = categorieService.saveCategorie(categorie);
    String msg = "Categorie enregistré avec Id " + saveCategorie.getIdCat();
    modelMap.addAttribute("msg", msg);
    return "createCategorie";
  }

  @GetMapping("/ListeCategoriesByName")
  public String listeCategoriesByName(ModelMap modelMap, @RequestParam("nom") String name) {
    List<Categorie> categories = categorieService.findByNomCatContains(name);
    List<Categorie> all_categories = categorieService.getAllCategories();

    if (categories.isEmpty()) {
      modelMap.addAttribute("msg", "Pas de categories avec se clé !");
      modelMap.addAttribute("categories", all_categories);
    } else {
      modelMap.addAttribute("categories", categories);
      modelMap.addAttribute("searchTerm", name);
    }

    return "listeCategories";
  }

  @RequestMapping("/supprimerCategorie")
  public String supprimerCategorie(@RequestParam("id") Long categorie_id,
      ModelMap modelMap,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size) {
    categorieService.deleteCategorieById(categorie_id);

    Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
        size);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);

    String msg = "Categorie supprimé ";
    modelMap.addAttribute("msg", msg);
    return "listeCategories";
  }

  @RequestMapping("/modifierCategorie")
  public String modifierCategories(@RequestParam("id") Long categorie_id,
      ModelMap modelMap) {
    Categorie categorie = categorieService.getCategorie(categorie_id);
    modelMap.addAttribute("categorie", categorie);
    return "modifierCategorie";
  }

  @RequestMapping("/updateCategorie")
  public String updateProduit(@ModelAttribute("categorie") Categorie new_categorie,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "7") int size,
      ModelMap modelMap) throws ParseException {
    Categorie old_categorie = categorieService.getCategorie(new_categorie.getIdCat());

    if (old_categorie.getNomCat() != new_categorie.getNomCat() &&
        new_categorie.getNomCat().length() > 0) {
      old_categorie.setNomCat(new_categorie.getNomCat());
    }
    if (old_categorie.getDescriptionCat() != new_categorie.getDescriptionCat()
        && new_categorie.getDescriptionCat().length() > 0D) {
      old_categorie.setDescriptionCat(new_categorie.getDescriptionCat());
    }
    categorieService.updateCategorie(old_categorie);

    Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
        size);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    modelMap.addAttribute("size", size);

    String msg = "Categorie modifié";
    modelMap.addAttribute("msg", msg);
    return "listeCategories";
  }

}
