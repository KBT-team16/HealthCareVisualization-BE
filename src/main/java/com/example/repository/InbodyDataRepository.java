package com.example.repository;

import com.example.domain.InbodyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InbodyDataRepository extends JpaRepository<InbodyData , Long> {

    @Query("SELECT InbodyData FROM Member as m WHERE m.id = :memberId")
    Page<InbodyData> findAll(Pageable pageable , Long memberId);
}
