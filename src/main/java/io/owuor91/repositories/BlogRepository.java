package io.owuor91.repositories;

import io.owuor91.models.Blog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
  List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);
}
