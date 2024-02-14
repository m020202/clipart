package study.clipart.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.clipart.domain.Category;
import study.clipart.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/new-category")
    public CreateCategoryResponse createCategory (@RequestBody @Valid CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setInfo(request.getInfo());
        categoryService.saveCategory(category);
        return new CreateCategoryResponse(category);
    }

    @GetMapping("/edit-category/{id}")
    public CategoryDTO category (@PathVariable("id") Long id) {
        Category findCategory = categoryService.findOne(id);
        return new CategoryDTO(findCategory);
    }

    @PostMapping("/edit-category/{id}")
    public EditCategoryResponse EditCategory (@PathVariable("id") Long id, @RequestBody @Valid EditCategoryRequest request) {
        categoryService.update_name(id, request.getName());
        categoryService.update_info(id, request.getInfo());
        Category updateCategory = categoryService.findOne(id);
        return new EditCategoryResponse(updateCategory);
    }

    @GetMapping("/category")
    public Result categories() {
        List<Category> categories = categoryService.findCategories();
        List<CategoryDTO> result = categories.stream()
                .map(c -> new CategoryDTO(c))
                .collect(Collectors.toList());
        return new Result(result);
    }

    @Data
    static class Result<T> {
        private T data;

        Result(T data) {
            this.data = data;
        }
    }

    @Data
    static class EditCategoryResponse {
        private String name;
        private String info;
        public EditCategoryResponse(Category category) {
            name = category.getName();
            info = category.getInfo();
        }
    }

    @Data
    static class EditCategoryRequest {
        @NotEmpty
        private String name;
        private String info;
    }

    @Data
    static class CategoryDTO {
        private String name;
        private String info;
        public CategoryDTO(Category category) {
            name = category.getName();
            info = category.getInfo();
        }
    }

    @Data
    static class CreateCategoryResponse {
        private Long id;
        private String name;

        public CreateCategoryResponse(Category category) {
            id = category.getId();
            name = category.getName();
        }
    }
    @Data
    static class CreateCategoryRequest {
        @NotEmpty
        private String name;
        private String info;
    }
}
