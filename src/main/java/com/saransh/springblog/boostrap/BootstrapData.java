package com.saransh.springblog.boostrap;

import com.saransh.springblog.domain.Category;
import com.saransh.springblog.domain.Comment;
import com.saransh.springblog.domain.Post;
import com.saransh.springblog.repository.CategoryRepository;
import com.saransh.springblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPosts();
    }

    private void loadPosts() {
        if (postRepository.count() == 0 && categoryRepository.count() == 0) {
            Category fashion = Category.builder()
                    .name("Fashion")
                    .build();

            Category food = Category.builder()
                    .name("Food")
                    .build();

            Category travel = Category.builder()
                    .name("Travel")
                    .build();

            Category music = Category.builder()
                    .name("Music")
                    .build();

            Category lifestyle = Category.builder()
                    .name("Lifestyle")
                    .build();

            Category fitness = Category.builder()
                    .name("Fitness")
                    .build();

            Category diy = Category.builder()
                    .name("DIY")
                    .build();

            Category sports = Category.builder()
                    .name("Sports")
                    .build();

            Category finance = Category.builder()
                    .name("Finance")
                    .build();

            Category political = Category.builder()
                    .name("Political")
                    .build();

            Category business = Category.builder()
                    .name("Business")
                    .build();

            Category personal = Category.builder()
                    .name("Personal")
                    .build();

            Category movie = Category.builder()
                    .name("Movie")
                    .build();

            Category news = Category.builder()
                    .name("News")
                    .build();

            Category gaming = Category.builder()
                    .name("Gaming")
                    .build();

            Category technology = Category.builder()
                    .name("Technology")
                    .build();

            categoryRepository.save(fashion);
            categoryRepository.save(food);
            categoryRepository.save(travel);
            categoryRepository.save(music);
            categoryRepository.save(lifestyle);
            categoryRepository.save(fitness);
            categoryRepository.save(diy);
            categoryRepository.save(sports);
            categoryRepository.save(finance);
            categoryRepository.save(political);
            categoryRepository.save(business);
            categoryRepository.save(personal);
            categoryRepository.save(movie);
            categoryRepository.save(news);
            categoryRepository.save(gaming);
            categoryRepository.save(technology);

            Post post_1 = Post.builder()
                    .title("What is Lorem Ipsum?")
                    .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem " +
                            "book. It has survived not only five centuries")
                    .username("John")
                    .createdAt(LocalDateTime.now())
                    .views(15865L)
                    .build();

            post_1.addComment(Comment.builder()
                    .username("John Doe")
                    .body("Contrary to popular belief, Lorem Ipsum is not " +
                            "simply random text. It has roots in a piece " +
                            "of classical Latin literature from 45 BC, making " +
                            "it over 2000 years old. Richard McClintock, a" +
                            " Latin professor at Hampden-Sydney College in Virginia" +
                            ", looked up one of the more obscure Latin words, " +
                            "consectetur, from a Lorem Ipsum")
                    .build()
            );

            post_1.addComment(Comment.builder()
                    .username("William Smith")
                    .body("There are many variations of passages of Lorem Ipsum " +
                            "available, but the majority have suffered alteration " +
                            "in some form, by injected humour, or randomised words " +
                            "which don't")
                    .build()
            );

            post_1.addComment(Comment.builder()
                    .username("Nick White")
                    .body("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                            "sed do eiusmod tempor incididunt ut labore et dolore " +
                            "magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                            "exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
                            "consequat. Duis aute irure dolor in reprehenderit")
                    .build()
            );

            Post post_2 = Post.builder()
                    .title("Where does it come from?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .username("William")
                    .createdAt(LocalDateTime.now().minusMonths(10))
                    .views(20589L)
                    .build();

            Post post_3 = Post.builder()
                    .title("Where can I get some?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .username("Saransh")
                    .createdAt(LocalDateTime.now().minusMonths(5))
                    .views(245864L)
                    .build();

            post_3.addComment(Comment.builder()
                    .username("Charles Nickolas")
                    .body("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                            "sed do eiusmod tempor incididunt ut labore et dolore " +
                            "magna aliqua. Ut enim ad minim veniam, quis nostrud")
                    .build()
            );

            Post post_4 = Post.builder()
                    .title("Why do we use it?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .username("Aditya")
                    .createdAt(LocalDateTime.now().minusDays(10).minusYears(2))
                    .views(1754L)
                    .build();

            Post post_5 = Post.builder()
                    .title("The standard Lorem Ipsum passage, used since the 1500s")
                    .body("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                            "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                            "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                    .username("Aditya")
                    .createdAt(LocalDateTime.now().minusMonths(10).minusYears(5))
                    .views(1528L)
                    .build();

            Post post_6 = Post.builder()
                    .title("1914 translation by H. Rackham")
                    .body("But I must explain to you how all this mistaken idea of denouncing pleasure " +
                            "and praising pain was born and I will give you a complete account of the " +
                            "system, and expound the actual teachings of the great explorer of the truth, " +
                            "the master-builder of human happiness.")
                    .username("Saransh")
                    .createdAt(LocalDateTime.now().minusMonths(12))
                    .views(985L)
                    .build();

            post_1.setCategory(fashion);
            post_2.setCategory(personal);
            post_3.setCategory(technology);
            post_4.setCategory(personal);
            post_5.setCategory(gaming);
            post_6.setCategory(news);

            postRepository.save(post_1);
            postRepository.save(post_2);
            postRepository.save(post_3);
            postRepository.save(post_4);
            postRepository.save(post_5);
            postRepository.save(post_6);
            log.info("Loaded Posts: {}", postRepository.count());
        }
    }
}
