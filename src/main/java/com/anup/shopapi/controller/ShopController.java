package com.anup.shopapi.controller;

import com.anup.shopapi.entity.Shop;
import com.anup.shopapi.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin(origins = "*")
@Tag(name = "Shop Management", description = "APIs for managing shops with full CRUD operations")
public class ShopController {
    
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    
    private final ShopService shopService;
    
    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }
    
    /**
     * GET /api/shops - Retrieve all shops
     * @return ResponseEntity with list of all shops
     */
    @Operation(
            summary = "Get all shops",
            description = "Retrieve a list of all shops from the database with their complete details including ID, name, address, phone, email, and timestamps."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all shops",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    name = "Successful Response",
                                    value = """
                                            {
                                              "success": true,
                                              "message": "Shops retrieved successfully",
                                              "data": [
                                                {
                                                  "id": 1,
                                                  "name": "Tech World Electronics",
                                                  "address": "123 Main Street, Downtown, City",
                                                  "phone": "+1-555-0101",
                                                  "email": "info@techworld.com",
                                                  "createdDate": "2025-06-13T21:46:19",
                                                  "updatedDate": "2025-06-13T21:46:19"
                                                }
                                              ],
                                              "count": 10
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error Response",
                                    value = """
                                            {
                                              "success": false,
                                              "message": "Error retrieving shops: Database connection failed",
                                              "data": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllShops() {
        try {
            logger.info("Received request to get all shops");
            List<Shop> shops = shopService.getAllShops();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Shops retrieved successfully");
            response.put("data", shops);
            response.put("count", shops.size());
            
            logger.info("Successfully retrieved {} shops", shops.size());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error retrieving shops: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error retrieving shops: " + e.getMessage());
            errorResponse.put("data", null);
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * GET /api/shops/{id} - Retrieve a specific shop by ID
     * @param id Shop ID
     * @return ResponseEntity with shop details
     */
    @Operation(
            summary = "Get shop by ID",
            description = "Retrieve a specific shop by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Shop found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Shop Found",
                                    value = """
                                            {
                                              "success": true,
                                              "message": "Shop found successfully",
                                              "data": {
                                                "id": 1,
                                                "name": "Tech World Electronics",
                                                "address": "123 Main Street, Downtown, City",
                                                "phone": "+1-555-0101",
                                                "email": "info@techworld.com",
                                                "createdDate": "2025-06-13T21:46:19",
                                                "updatedDate": "2025-06-13T21:46:19"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Shop not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Shop Not Found",
                                    value = """
                                            {
                                              "success": false,
                                              "message": "Shop not found with ID: 999",
                                              "data": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getShopById(
            @Parameter(description = "Unique identifier of the shop", example = "1")
            @PathVariable Long id) {
        try {
            logger.info("Received request to get shop with ID: {}", id);
            Optional<Shop> shop = shopService.getShopById(id);
            
            Map<String, Object> response = new HashMap<>();
            
            if (shop.isPresent()) {
                response.put("success", true);
                response.put("message", "Shop found successfully");
                response.put("data", shop.get());
                logger.info("Successfully retrieved shop with ID: {}", id);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Shop not found with ID: " + id);
                response.put("data", null);
                logger.warn("Shop not found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
        } catch (Exception e) {
            logger.error("Error retrieving shop with ID {}: {}", id, e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error retrieving shop: " + e.getMessage());
            errorResponse.put("data", null);
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * POST /api/shops - Create a new shop
     * @param shop Shop details
     * @return ResponseEntity with created shop
     */
    @Operation(
            summary = "Create a new shop",
            description = "Create a new shop with the provided details. All fields except phone and email are required."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Shop created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Shop Created",
                                    value = """
                                            {
                                              "success": true,
                                              "message": "Shop created successfully",
                                              "data": {
                                                "id": 13,
                                                "name": "New Electronics Store",
                                                "address": "456 New Street, City",
                                                "phone": "+1-555-0123",
                                                "email": "info@newstore.com",
                                                "createdDate": "2025-06-13T22:00:00",
                                                "updatedDate": "2025-06-13T22:00:00"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Validation Error",
                                    value = """
                                            {
                                              "success": false,
                                              "message": "Validation failed: Shop name is required",
                                              "data": null
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> createShop(
            @Parameter(description = "Shop details to create", required = true)
            @Valid @RequestBody Shop shop) {
        try {
            logger.info("Received request to create new shop: {}", shop.getName());
            Shop createdShop = shopService.createShop(shop);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Shop created successfully");
            response.put("data", createdShop);

            logger.info("Successfully created shop with ID: {}", createdShop.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            logger.error("Error creating shop: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error creating shop: " + e.getMessage());
            errorResponse.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * PUT /api/shops/{id} - Update an existing shop
     * @param id Shop ID
     * @param shop Updated shop details
     * @return ResponseEntity with updated shop
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateShop(@PathVariable Long id, @Valid @RequestBody Shop shop) {
        try {
            logger.info("Received request to update shop with ID: {}", id);
            Optional<Shop> updatedShop = shopService.updateShop(id, shop);

            Map<String, Object> response = new HashMap<>();

            if (updatedShop.isPresent()) {
                response.put("success", true);
                response.put("message", "Shop updated successfully");
                response.put("data", updatedShop.get());
                logger.info("Successfully updated shop with ID: {}", id);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Shop not found with ID: " + id);
                response.put("data", null);
                logger.warn("Shop not found for update with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            logger.error("Error updating shop with ID {}: {}", id, e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error updating shop: " + e.getMessage());
            errorResponse.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * DELETE /api/shops/{id} - Delete a shop
     * @param id Shop ID
     * @return ResponseEntity with deletion status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteShop(@PathVariable Long id) {
        try {
            logger.info("Received request to delete shop with ID: {}", id);
            boolean deleted = shopService.deleteShop(id);

            Map<String, Object> response = new HashMap<>();

            if (deleted) {
                response.put("success", true);
                response.put("message", "Shop deleted successfully");
                response.put("data", null);
                logger.info("Successfully deleted shop with ID: {}", id);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Shop not found with ID: " + id);
                response.put("data", null);
                logger.warn("Shop not found for deletion with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            logger.error("Error deleting shop with ID {}: {}", id, e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error deleting shop: " + e.getMessage());
            errorResponse.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * GET /api/shops/search?q={searchTerm} - Search shops by name or address
     * @param searchTerm Search term
     * @return ResponseEntity with matching shops
     */
    @Operation(
            summary = "Search shops",
            description = "Search for shops by name or address using a case-insensitive partial match."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Search completed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Search Results",
                                    value = """
                                            {
                                              "success": true,
                                              "message": "Search completed successfully",
                                              "data": [
                                                {
                                                  "id": 7,
                                                  "name": "Cozy Coffee Corner",
                                                  "address": "147 Cafe Street, Arts District, City",
                                                  "phone": "+1-555-0107",
                                                  "email": "hello@cozycoffee.com",
                                                  "createdDate": "2025-06-13T21:46:19",
                                                  "updatedDate": "2025-06-13T21:46:19"
                                                }
                                              ],
                                              "count": 1,
                                              "searchTerm": "coffee"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchShops(
            @Parameter(description = "Search term to find shops by name or address", example = "coffee", required = true)
            @RequestParam("q") String searchTerm) {
        try {
            logger.info("Received request to search shops with term: {}", searchTerm);
            List<Shop> shops = shopService.searchShops(searchTerm);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Search completed successfully");
            response.put("data", shops);
            response.put("count", shops.size());
            response.put("searchTerm", searchTerm);

            logger.info("Successfully found {} shops matching search term", shops.size());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error searching shops: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error searching shops: " + e.getMessage());
            errorResponse.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * GET /api/shops/count - Get total count of shops
     * @return ResponseEntity with shop count
     */
    @Operation(
            summary = "Get shop count",
            description = "Get the total number of shops in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Count retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Shop Count",
                                    value = """
                                            {
                                              "success": true,
                                              "message": "Count retrieved successfully",
                                              "count": 12
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getShopsCount() {
        try {
            logger.info("Received request to get shops count");
            long count = shopService.getTotalShopsCount();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Count retrieved successfully");
            response.put("count", count);

            logger.info("Successfully retrieved shops count: {}", count);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error getting shops count: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error getting shops count: " + e.getMessage());
            errorResponse.put("count", 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
