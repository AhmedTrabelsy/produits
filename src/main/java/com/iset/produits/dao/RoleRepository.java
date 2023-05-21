package com.iset.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.produits.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
