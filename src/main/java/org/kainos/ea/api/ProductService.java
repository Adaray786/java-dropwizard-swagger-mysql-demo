package org.kainos.ea.api;

import javassist.bytecode.SourceFileAttribute;
import org.kainos.ea.client.FailedToDeleteProductException;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNotExistException, FailedToUpdateProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if (productToUpdate == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }

    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }
            int id = productDao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }
    public List<Product> getAllProducts() throws FailedToGetProductException, InvalidProductException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        try {

        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductException();
        }

         */

        /*
        List<Product> cheapProducts =
                productList.stream().filter(product -> product.getPrice() <10).collect(Collectors.toList());

        cheapProducts.forEach(System.out::println);
        */
        //productList.stream().filter(product -> product.getPrice() > 10).forEach(System.out::println);

        //System.out.println(Collections.min(productList));

        /*
        List<Integer> intList = Arrays.asList(1,2,2,4,5);

        Set<Integer> intSet = new HashSet<>(intList);

        intSet.stream().forEach(System.out::println);
        intList.stream().forEach(System.out::println);
         */

        /*
        for(Product product : productList) {
            switch (product.getName()) {
                case ("Heater"):
                    System.out.println("This is the heater price: £" + product.getPrice());
                    break;
                case ("Cooler"):
                    System.out.println("This is the cooler price: £" + product.getPrice());
                    break;
                case ("Water Bottle"):
                    System.out.println("This is the water bottle price: £" + product.getPrice());
                    break;
                default:
                    System.out.println("This is the other price: £" + product.getPrice());
            }
        }

         */

        /*
        double totalPriceOfProduct = 0;
        double totalPriceOfCheapProduct = 0;
        double totalPriceOfExpensiveProduct = 0;

        totalPriceOfProduct = productList.stream().mapToDouble(product -> product.getPrice()).sum();

        for (Product product: productList) {
            if (product.getPrice() < 100) {
                totalPriceOfCheapProduct += product.getPrice();
            } else {
                totalPriceOfExpensiveProduct += product.getPrice();
            }
        }
        */

        /*
        for(int i = 0; i < productList.size(); i++) {
            totalPriceOfProduct += productList.get(i).getPrice();
        }
        */

        /*
        for (Product product: productList) {
            totalPriceOfProduct += product.getPrice();
        }
        */

        //Iterator<Product> productIterator = productList.iterator();

        /*
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            totalPriceOfProduct += product.getPrice();
        }
         */

        /*
        do {
            Product product = productIterator.next();
            totalPriceOfProduct += product.getPrice();
        } while (productIterator.hasNext());
        */

        /*
        System.out.println("Total price of all produts: £" + totalPriceOfProduct);
        System.out.println("TOtal price of all cheap products: £" + totalPriceOfCheapProduct);
        System.out.println("TOtal price of all expensive products: £" + totalPriceOfExpensiveProduct);
        */
        return productList;
    }

    public Product getProductById(int id) throws FailedToGetProductException, ProductDoesNotExistException {
        try {
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }

            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductException();
        }
    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException {
        try {
            Product productToDelete = productDao.getProductById(id);

            if (productToDelete == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.deleteProduct(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteProductException();
        }
    }
}
