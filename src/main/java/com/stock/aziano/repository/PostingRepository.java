package com.stock.aziano.repository;

import com.stock.aziano.models.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {

}
