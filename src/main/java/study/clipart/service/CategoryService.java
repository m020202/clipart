package study.clipart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Category;
import study.clipart.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveCategory(Category category) {
        categoryRepository.save(category);
        return category.getId();
    }

    @Transactional
    public void update_name(Long id, String name) {
        Category find = categoryRepository.findOne(id);
        find.setName(name);
    }
    @Transactional
    public void update_info(Long id, String info) {
        Category find = categoryRepository.findOne(id);
        find.setInfo(info);
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
