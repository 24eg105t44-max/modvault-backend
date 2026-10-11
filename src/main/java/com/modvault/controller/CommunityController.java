package com.modvault.controller;

import com.modvault.entity.CommunityPost;
import com.modvault.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<CommunityPost>> getAllPosts() {
        return ResponseEntity.ok(communityService.getAllPosts());
    }

    @PostMapping("/posts")
    public ResponseEntity<CommunityPost> createPost(@RequestBody CommunityPost post) {
        return ResponseEntity.ok(communityService.createPost(post));
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<CommunityPost> likePost(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.toggleLike(id));
    }

    @PostMapping("/posts/{id}/bookmark")
    public ResponseEntity<CommunityPost> bookmarkPost(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.toggleBookmark(id));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        communityService.deletePost(id);
        return ResponseEntity.ok(Map.of("message", "Post deleted", "id", id));
    }
}
