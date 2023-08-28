package ma.elaroui.labelvieproduct.repositories;
import ma.elaroui.labelvieproduct.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByNameContains(String kw, Pageable pageable);

}
