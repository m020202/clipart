package study.clipart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Category;
import study.clipart.domain.Clipart;
import study.clipart.domain.Member;
import study.clipart.repository.CategoryRepository;
import study.clipart.repository.ClipartRepository;
import study.clipart.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClipartService {
    private final ClipartRepository clipartRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(String name, Long memberId, Long categoryId, String originalFileName, String saveFileName) {
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);

        Clipart clipart = Clipart.create(name,member,category,originalFileName,saveFileName);
        clipartRepository.save(clipart);

        return clipart.getId();
    }

    @Transactional
    public void update(Long id, String name) {
        Clipart clipart = clipartRepository.findOne(id);
        clipart.setName(name);
        clipart.setUpdateDate(LocalDateTime.now());
    }

    public List<Clipart> findAll() {
        return clipartRepository.findAll();
    }

    public Clipart findOne(Long id) {
        return clipartRepository.findOne(id);
    }
}
