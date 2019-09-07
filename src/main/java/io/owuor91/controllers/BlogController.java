package io.owuor91.controllers;

import io.owuor91.models.Blog;
import io.owuor91.repositories.BlogRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
  @Autowired BlogRepository blogRepository;

  @GetMapping("/blog")
  public List<Blog> index() {
    return blogRepository.findAll();
  }

  @GetMapping("/blog/{id}")
  public Blog show(@PathVariable String id) {
    int blogId = Integer.parseInt(id);
    return blogRepository.findOne(blogId);
  }

  @PostMapping("/blog/search")
  public List<Blog> search(@RequestParam("search_term") String searchTerm) {
    return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
  }

  @PostMapping("/blog")
  public Blog create(@RequestParam("title") String title, @RequestParam("content") String content) {
    return blogRepository.save(new Blog(title, content));
  }

  @PutMapping("/blog/{id}")
  public Blog update(@PathVariable String id, @RequestParam("title") String title,
      @RequestParam("content") String content) {
    int blogId = Integer.parseInt(id);
    Blog blog = blogRepository.findOne(blogId);
    blog.setTitle(title);
    blog.setContent(content);
    return blogRepository.save(blog);
  }

  @DeleteMapping("/blog/{id}")
  public Map<String, String> delete(@PathVariable String id) {
    int blogId = Integer.parseInt(id);
    blogRepository.delete(blogId);
    HashMap<String, String> responseMap = new HashMap<String, String>();
    responseMap.put("message", "blog deleted successfully");
    responseMap.put("code", "204");
    return responseMap;
  }
}
