package study.clipart.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Category;
import study.clipart.domain.Clipart;
import study.clipart.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ClipartServiceTest {
    @Autowired ClipartService clipartService;
    @Autowired MemberService memberService;
    @Autowired CategoryService categoryService;

    @Test
    public void 클립아트_등록() throws Exception{
        Member member = Member.create("kim","m020202","answjddnr1");
        Long memberId = memberService.join(member);

        Category category = Category.create("car","about Car");
        Long categoryId = categoryService.saveCategory(category);

        Long id = clipartService.save("페라리", memberId, categoryId);

        Clipart clipart = clipartService.findOne(id);

        assertThat(clipart.getName()).isEqualTo("car");
    }

    @Test
    public void 클립아트_수정() throws Exception {
        Member member = Member.create("kim","m020202","answjddnr1");
        Long memberId = memberService.join(member);

        Category category = Category.create("car","about Car");
        Long categoryId = categoryService.saveCategory(category);

        Long id = clipartService.save("페라리", memberId, categoryId);

        clipartService.update(id, "람보르기니");

        Clipart find = clipartService.findOne(id);
        assertThat(find.getName()).isEqualTo("람보르기니");
    }
}