package io.owuor91.controllers;

import io.owuor91.models.Blog;
import io.owuor91.models.BlogMockedData;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {
  BlogMockedData blogMockedData = BlogMockedData.getInstance();

  @GetMapping("/blog")
  public List<Blog> index() {
    return blogMockedData.fetchBlogs();
  }

  @GetMapping("/blog/{id}")
  public Blog show(@PathVariable String id) {
    int blogId = Integer.parseInt(id);
    return blogMockedData.getBlogById(blogId);
  }

  @PostMapping("/blog/search")
  public List<Blog> search(@RequestBody Map<String, String> body) {
    String searchTerm = body.get("text");
    return blogMockedData.searchBlogs(searchTerm);
  }

  @PostMapping("/blog")
  public Blog create(@RequestBody Map<String, String> body) {
    int id = Integer.parseInt(body.get("id"));
    String title = body.get("title");
    String content = body.get("content");
    return blogMockedData.createBlog(id, title, content);
  }

  @PutMapping("/blog/{id}")
  public Blog update(@RequestBody Map<String, String> body) {
    int blogId = Integer.parseInt(body.get("id"));
    String title = body.get("title");
    String content = body.get("content");
    return blogMockedData.updateBlog(blogId, title, content);
  }

  @DeleteMapping("/blog/{id}")
  public boolean delete(@PathVariable String id) {
    int blogId = Integer.parseInt(id);
    return blogMockedData.delete(blogId);
  }
}
