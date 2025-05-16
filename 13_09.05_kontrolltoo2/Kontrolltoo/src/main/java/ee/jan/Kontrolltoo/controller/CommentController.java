package ee.jan.Kontrolltoo.controller;

import ee.jan.Kontrolltoo.entity.Comment;
import ee.jan.Kontrolltoo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("comments")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @PostMapping("comments")
    public List<Comment> addComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return commentRepository.findAll();
    }

    @DeleteMapping("comments/{id}")
    public List<Comment> deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return commentRepository.findAll();
    }

    @PutMapping("comments")
    public List<Comment> updateComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return commentRepository.findAll();
    }

    @GetMapping("comments/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentRepository.findById(id).orElse(null);
    }
}
