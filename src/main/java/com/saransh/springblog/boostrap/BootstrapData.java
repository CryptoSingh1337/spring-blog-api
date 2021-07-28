package com.saransh.springblog.boostrap;

import com.saransh.springblog.domain.Category;
import com.saransh.springblog.domain.Post;
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
        if (postRepository.count() == 0) {
            Category technology = Category.builder()
                    .name("Technology")
                    .build();

            Category engineering = Category.builder()
                    .name("Engineering")
                    .build();

            Category management = Category.builder()
                    .name("Management")
                    .build();

            categoryRepository.save(technology);
            categoryRepository.save(engineering);
            categoryRepository.save(management);

            Post post_1 = Post.builder()
                    .title("What is Lorem Ipsum?")
                    .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem " +
                            "book. It has survived not only five centuries")
                    .createdAt(LocalDateTime.now())
                    .build();

            Post post_2 = Post.builder()
                    .title("Where does it come from?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .createdAt(LocalDateTime.now().minusMonths(10))
                    .build();

            Post post_3 = Post.builder()
                    .title("Where can I get some?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .createdAt(LocalDateTime.now().minusMonths(5))
                    .build();

            Post post_4 = Post.builder()
                    .title("Why do we use it?")
                    .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                            "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                            "Richard McClintock")
                    .createdAt(LocalDateTime.now().minusMonths(5))
                    .build();

            post_1.addCategory(engineering);

            post_2.addCategory(technology);
            post_2.addCategory(engineering);

            post_3.addCategory(management);

            post_4.addCategory(engineering);

            postRepository.save(post_1);
            postRepository.save(post_2);
            postRepository.save(post_3);
            postRepository.save(post_4);
            log.info("Loaded Posts: {}", postRepository.count());
        }
    }
}
