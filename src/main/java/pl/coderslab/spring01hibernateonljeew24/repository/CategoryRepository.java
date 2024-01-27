package pl.coderslab.spring01hibernateonljeew24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernateonljeew24.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
