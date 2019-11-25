package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.Model.Tester;

public interface testRepositor  extends JpaRepository<Tester, Long> {

}
