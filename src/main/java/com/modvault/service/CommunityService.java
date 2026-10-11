package com.modvault.service;

import com.modvault.entity.CommunityPost;
import com.modvault.repository.CommunityPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {
    private final CommunityPostRepository communityPostRepository;

    public CommunityService(CommunityPostRepository communityPostRepository) {
        this.communityPostRepository = communityPostRepository;
    }

    public List<CommunityPost> getAllPosts() {
        return communityPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public CommunityPost createPost(CommunityPost post) {
        return communityPostRepository.save(post);
    }

    public CommunityPost toggleLike(Long id) {
        CommunityPost post = communityPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        post.setLikesCount((post.getLikesCount() == null ? 0 : post.getLikesCount()) + 1);
        return communityPostRepository.save(post);
    }

    public CommunityPost toggleBookmark(Long id) {
        CommunityPost post = communityPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        post.setIsBookmarked(!Boolean.TRUE.equals(post.getIsBookmarked()));
        return communityPostRepository.save(post);
    }

    public void deletePost(Long id) {
        communityPostRepository.deleteById(id);
    }
}
