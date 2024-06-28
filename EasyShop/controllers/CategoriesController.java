package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// Add the annotations to make this a REST controller
@RestController
// Add the annotation to make this controller the endpoint for the following URL
// http://localhost:8080/categories
@RequestMapping("/categories")
// Add annotation to allow cross-site origin requests
@CrossOrigin
public class CategoriesController {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    // Create an Autowired constructor to inject the CategoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // Add the appropriate annotation for a GET action
    @GetMapping("")
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        // Find and return all categories

            List<Category> categories = categoryDao.getAllCategories();
            if (categories.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return categories;

    }

    // Add the appropriate annotation for a GET action
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        // Get the category by id

            Category category = categoryDao.findById(id);
            if (category == null) {

            }
            return category;

    }

    // The URL to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        // Get a list of products by categoryId

            List<Product> products = productDao.findByCategoryId(categoryId);
            if (products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return products;


    }

    // Add annotation to call this method for a POST action
    // Add annotation to ensure that only an ADMIN can call this function
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        // Insert the category

            categoryDao.addCategory(category);
            return category;


    }

    // Add annotation to call this method for a PUT (update) action - the URL path must include the categoryId
    // Add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        // Update the category by id
    categoryDao.updateCategory(id,category);


            }






    // Add annotation to call this method for a DELETE action - the URL path must include the categoryId
    // Add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable int id) {
        // Delete the category by id
        try {
            if (categoryDao.deleteCategory(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (ResponseStatusException e) {
            throw new RuntimeException(e);
        }
    }
    }

