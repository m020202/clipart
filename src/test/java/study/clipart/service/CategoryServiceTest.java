package study.clipart.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Category;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired private CategoryService categoryService;

    @Test
    public void 카테고리등록() throws Exception{
        Category category = Category.create("car", "about car");

        Long id = categoryService.saveCategory(category);

        assertThat(id).isEqualTo(category.getId());
    }

    @Test
    public void 카테고리_수정() throws Exception{
        Category category = Category.create("car", "about car");
        Long id = categoryService.saveCategory(category);

        categoryService.update_name(id, "SuperCar");
        categoryService.update_info(id, "about SuperCar");

        Category find = categoryService.findOne(id);
        assertThat(find.getName()).isEqualTo("SuperCar");
        assertThat(find.getInfo()).isEqualTo("about SuperCar");
    }

    @Test
    public void 카테고리_조회_단건() throws Exception{
        Category category1 = Category.create("car", "about car");
        Category category2 = Category.create("flower", "about flower");
        categoryService.saveCategory(category1);
        categoryService.saveCategory(category2);

        Category find = categoryService.findOne(category1.getId());

        assertThat(find.getName()).isEqualTo(category1.getName());
    }

    @Test
    public void 카테고리_조회_모두() throws Exception{
        Category category1 = Category.create("car", "about car");
        Category category2 = Category.create("flower", "about flower");
        categoryService.saveCategory(category1);
        categoryService.saveCategory(category2);

        List<Category> find = categoryService.findCategories();

        assertThat(find.size()).isEqualTo(2);
    }
}