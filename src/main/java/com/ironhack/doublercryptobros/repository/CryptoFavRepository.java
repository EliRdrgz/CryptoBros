package com.ironhack.doublercryptobros.repository;

import com.ironhack.doublercryptobros.model.CryptoFav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoFavRepository extends JpaRepository<CryptoFav, String> {
}
