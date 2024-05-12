package hu.thesis.baseshop.products.controller;

import hu.thesis.baseshop.products.entity.ImageModel;
import hu.thesis.baseshop.products.entity.Opinions;
import hu.thesis.baseshop.products.entity.Product;
import hu.thesis.baseshop.products.service.OpinionsService;
import hu.thesis.baseshop.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OpinionsService opinionsService;

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('User')")
    @PostMapping(value = {"/addNewOpinion"})
    public Opinions addNewProduct(@RequestPart("opinions") Opinions opinions) {
        try {
            return opinionsService.addNewOpinion(opinions);
        } catch (Exception e) {
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles){
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOpinion"})
    public List<Opinions> getAllOpinion(){
        return opinionsService.getAllOpinion();
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId) {
        productService.deleteProductDetails(productId);
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteOpinion/{opinionId}"})
    public void deleteOpinion(@PathVariable("opinionId") Integer opinionId) {
        opinionsService.deleteOpinion(opinionId);
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
        return productService.getProductDetailsById(productId);
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOpinionById/{id}"})
    public List<Opinions> getOpinionById(@PathVariable("id") String id){
        return opinionsService.getOpinionById(id);
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @GetMapping({"/getOpinionByProd/{productId}"})
    public List<Opinions> getOpinionByProductId(@PathVariable("productId") Integer productId){
        return opinionsService.getOpinionByProductId(productId);
    }

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                  @PathVariable(name = "productId") Integer productId) {
        return productService.getProductDetails(isSingleProductCheckout, productId);
    }

}
