package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductsController
{
    private final ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao)
    {
        this.productDao = productDao;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Product>> search(@RequestParam(name="cat", required = false) Integer categoryId,
                                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
                                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                                @RequestParam(name="color", required = false) String color)
    {
        try
        {
            List<Product> products = productDao.search(categoryId, minPrice, maxPrice, color);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.", ex);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Product> getById(@PathVariable int id)
    {
        try
        {
            Product product = productDao.getById(id);

            if(product == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.", ex);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        try
        {
            Product createdProduct = productDao.create(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.", ex);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product product)
    {
        try
        {
            productDao.update(id, product);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.", ex);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id)
    {
        try
        {
            Product product = productDao.getById(id);

            if(product == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            productDao.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.", ex);
        }
    }
}
