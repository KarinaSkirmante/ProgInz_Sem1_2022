package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {

}
