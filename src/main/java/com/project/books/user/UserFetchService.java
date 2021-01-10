package com.project.books.user;

import com.project.books.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFetchService {

    private final UserRepository userRepository;

    public User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie ma użytownika o podanym id " + id));
    }

    public List<User> fetchAllUsers(){
            return userRepository.findAll();
        }

    public User fetchUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    }

