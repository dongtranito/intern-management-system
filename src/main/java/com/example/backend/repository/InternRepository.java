package com.example.backend.repository;

import com.example.backend.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    long countByProjectId(Long projectId);
    List<Intern> findByMentorId(Long mentorId);
    List<Intern> findByProjectId(Long projectId);
    List<Intern> findByFullNameContainingIgnoreCase(String keyword);
    
    @Query("SELECT i FROM Intern i WHERE " +
           "(:keyword IS NULL OR LOWER(i.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:projectId IS NULL OR i.project.id = :projectId)")
    List<Intern> searchInterns(@Param("keyword") String keyword, @Param("projectId") Long projectId);
}