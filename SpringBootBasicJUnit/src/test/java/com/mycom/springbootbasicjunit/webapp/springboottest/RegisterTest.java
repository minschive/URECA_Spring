package com.mycom.springbootbasicjunit.webapp.springboottest;

import com.mycom.springbootbasicjunit.auth.controller.LoginController;
import com.mycom.springbootbasicjunit.auth.service.LoginService;
import com.mycom.springbootbasicjunit.user.controller.UserController;
import com.mycom.springbootbasicjunit.user.dto.UserResultDto;
import com.mycom.springbootbasicjunit.user.entity.User;
import com.mycom.springbootbasicjunit.user.repository.UserRepository;
import com.mycom.springbootbasicjunit.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {

//    @Autowired
//    private MockMvc mockMvc;

    // 개발 순서
    // repository -> service -> controller
    // 초기 설계를 마무리한 상태에서 실제 coding 순서

    // userRepository test 는 User Entity 만 save
    // Hibernate: insert into user (email,name,password) values (?,?,?)
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional // 자동 rollback
    public void testRegister() {
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
    }

    // userService test 는 UserRole Entity 와 User Entity 를 모두 save
    // Hibernate: insert into user_role (name) values (?)
    // Hibernate: insert into user (email,name,password) values (?,?,?)
    // Hibernate: insert into user_user_roles (user_id,user_roles_id) values (?,?)
    @Autowired
    private UserService userService;

    @Test
    @Transactional // 자동 rollback
    public void testRegister2() {
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        UserResultDto userResultDto = userService.insertUser(user);
        assertEquals("success", userResultDto.getResult());
    }

    @Autowired
    private UserController userController;

    @Test
    @Transactional
    public void testRegister3() {
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        userController.insertUser(user);

        UserResultDto userResultDto = userController.insertUser(user);
        assertEquals("success", userResultDto.getResult());
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void restRegister4() throws Exception {
        this.mockMvc.perform(
                post("/users/register")
                        .param("name", "홍길동")
                        .param("email", "hong@gildong.com")
                        .param("password", "1234")
                )
                .andExpect(status().isOk()
        );
    }
}
