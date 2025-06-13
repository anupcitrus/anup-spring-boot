package com.anup.shopapi.repository;

import com.anup.shopapi.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
    // Find shops by name (case-insensitive)
    List<Shop> findByNameContainingIgnoreCase(String name);
    
    // Find shops by address containing specific text
    List<Shop> findByAddressContainingIgnoreCase(String address);
    
    // Find shop by email
    Optional<Shop> findByEmail(String email);
    
    // Find shops by phone
    Optional<Shop> findByPhone(String phone);
    
    // Custom query to find shops by name or address
    @Query("SELECT s FROM Shop s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(s.address) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Shop> findByNameOrAddressContaining(@Param("searchTerm") String searchTerm);
    
    // Count total shops
    @Query("SELECT COUNT(s) FROM Shop s")
    long countAllShops();
}
