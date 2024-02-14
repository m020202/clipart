package study.clipart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Category;
import study.clipart.domain.Member;
import study.clipart.domain.Post;
import study.clipart.repository.CategoryRepository;
import study.clipart.repository.PostRepository;
import study.clipart.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(String name, Long memberId, Long categoryId, String originalFileName, String saveFileName) {
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);

        Post post = Post.create(name,member,category,originalFileName,saveFileName);
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public void update(Long id, String name) {
        Post post = postRepository.findOne(id);
        post.setName(name);
        post.setUpdateDate(LocalDateTime.now());
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findOne(Long id) {
        return postRepository.findOne(id);
    }
}
