package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

// Register 단계
// UserRepository - save
// UserRoleRepository - find, save (2가지)
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserResultDto insertUser(User user) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            // #1. 기존 UserRole 을 find, name = ROLE_CUSTOMER
    //        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER");
    //        List<UserRole> userRoles = List.of(userRole);
    //        user.setUserRoles(userRoles);
    //        User savedUser = userRepository.save(user);

            // #2. 새로운 UserRole 생성

            // #2-1. userRole 객체 save 하지 않음 -> 영속화 컨텍스트에 없음
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            List<UserRole> userRoles = List.of(userRole);
//            user.setUserRoles(userRoles);
//            User savedUser = userRepository.save(user);

            // #2-2. userRole 객체 save O -> 영속화 O
            UserRole userRole = new UserRole();
            userRole.setName("role_test");
            List<UserRole> userRoles = List.of(userRole);
            user.setUserRoles(userRoles);
            userRoleRepository.save(userRole); // 위와 다른 부분
            User savedUser = userRepository.save(user);

            userRoleRepository.flush();
            userRepository.flush();

    //        // #3. transaction + #1 상황
    //        UserRole userRole = userRoleRepository.findByName("ROLE_CUSTOMER");
    //        List<UserRole> userRoles = List.of(userRole);
    //        user.setUserRoles(userRoles);
    //        User savedUser = userRepository.save(user);
    //
    //        String s = null;
    //        s.length();
    //
    //        System.out.println(savedUser);

            // #4. transactional + #2-2 상황
    //        UserRole userRole = new UserRole();
    //        userRole.setName("role_test");
    //        List<UserRole> userRoles = List.of(userRole);
    //        user.setUserRoles(userRoles);
    //        userRoleRepository.save(userRole);
    //
    //        String s = null;
    //        s.length();
    //
    //        User savedUser = userRepository.save(user);

            // #5. #2-1 오류 발생을 OneToMany 의 persist 주는 것으로 처리
    //        UserRole userRole = new UserRole();
    //        userRole.setName("role_test");
    //        List<UserRole> userRoles = List.of(userRole);
    //        user.setUserRoles(userRoles);
    //        User savedUser = userRepository.save(user);

            // #6
            // @Transactional 상황에서는 TransactionAspect 가 관여하고, Proxy 객체를 통해 우리의 코드를
            // 대신 호출하고 최종적인 예외가 발생하지 않으면 TransactionAspect 가 commit() 을 수행하는데
            // 이 과정에서 예외가 발생하므로 우리 코드 밖에서 생기는 예외를 우리 코드에서 catch 하지 못하는 상황 발생
//            UserRole userRole = new UserRole();
//            userRole.setName("role_test");
//            List<UserRole> userRoles = List.of(userRole);
//            user.setUserRoles(userRoles);
//            User savedUser = userRepository.save(user);
//            userRepository.flush(); // 코드 안에서 commit 부분 수행함

            userResultDto.setResult("success");

        } catch (Exception e) {

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            System.out.println("-----------------------------");
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }

//    @Transactional
//    public void txAction() {
//        User savedUser = userRepository.save(user);
//        userRepository.flush();
//    }

}
