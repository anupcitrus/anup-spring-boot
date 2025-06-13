package com.anup.shopapi.service;

import com.anup.shopapi.entity.Shop;
import com.anup.shopapi.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShopService {
    
    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);
    
    private final ShopRepository shopRepository;
    
    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    
    /**
     * Retrieve all shops from the database
     * @return List of all shops
     */
    @Transactional(readOnly = true)
    public List<Shop> getAllShops() {
        logger.info("Fetching all shops from database");
        List<Shop> shops = shopRepository.findAll();
        logger.info("Found {} shops", shops.size());
        return shops;
    }
    
    /**
     * Find a shop by ID
     * @param id Shop ID
     * @return Optional containing the shop if found
     */
    @Transactional(readOnly = true)
    public Optional<Shop> getShopById(Long id) {
        logger.info("Fetching shop with ID: {}", id);
        return shopRepository.findById(id);
    }
    
    /**
     * Create a new shop
     * @param shop Shop to create
     * @return Created shop
     */
    public Shop createShop(Shop shop) {
        logger.info("Creating new shop: {}", shop.getName());
        Shop savedShop = shopRepository.save(shop);
        logger.info("Shop created successfully with ID: {}", savedShop.getId());
        return savedShop;
    }
    
    /**
     * Update an existing shop
     * @param id Shop ID
     * @param shopDetails Updated shop details
     * @return Updated shop
     */
    public Optional<Shop> updateShop(Long id, Shop shopDetails) {
        logger.info("Updating shop with ID: {}", id);
        return shopRepository.findById(id)
                .map(shop -> {
                    shop.setName(shopDetails.getName());
                    shop.setAddress(shopDetails.getAddress());
                    shop.setPhone(shopDetails.getPhone());
                    shop.setEmail(shopDetails.getEmail());
                    Shop updatedShop = shopRepository.save(shop);
                    logger.info("Shop updated successfully: {}", updatedShop.getId());
                    return updatedShop;
                });
    }
    
    /**
     * Delete a shop by ID
     * @param id Shop ID
     * @return true if deleted, false if not found
     */
    public boolean deleteShop(Long id) {
        logger.info("Deleting shop with ID: {}", id);
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
            logger.info("Shop deleted successfully: {}", id);
            return true;
        }
        logger.warn("Shop not found for deletion: {}", id);
        return false;
    }
    
    /**
     * Search shops by name or address
     * @param searchTerm Search term
     * @return List of matching shops
     */
    @Transactional(readOnly = true)
    public List<Shop> searchShops(String searchTerm) {
        logger.info("Searching shops with term: {}", searchTerm);
        List<Shop> shops = shopRepository.findByNameOrAddressContaining(searchTerm);
        logger.info("Found {} shops matching search term", shops.size());
        return shops;
    }
    
    /**
     * Get total count of shops
     * @return Total number of shops
     */
    @Transactional(readOnly = true)
    public long getTotalShopsCount() {
        long count = shopRepository.countAllShops();
        logger.info("Total shops count: {}", count);
        return count;
    }
}
