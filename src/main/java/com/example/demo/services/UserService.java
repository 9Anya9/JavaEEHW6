package com.example.demo.services;

import com.example.demo.entities.BookEntity;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public UserEntity registerUser(final String login,
                                   final String password,
                                   final String customAuthField,
                                   final List<PermissionEntity> permissions) {
        final UserEntity newUser = new UserEntity();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setCustomAuthField(customAuthField);
        newUser.setPermissions(permissions);
        newUser.setFavbooks(new HashSet<>());
        return userRepository.saveAndFlush(newUser);
    }

    @Transactional
    public Set<BookEntity> getFavoritebook(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        return user.getFavbooks();
    }


    @Transactional
    public UserEntity addFavoriteBook(final String username, final int bookID) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        final BookEntity bookToAdd = bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("No book with ID: " + bookID));

        Set<BookEntity> newFavBooks = user.getFavbooks();
        newFavBooks.add(bookToAdd);
        user.setFavbooks(newFavBooks);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public UserEntity delFavoriteBook(final String username, final int bookID) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        Set<BookEntity> newFavBooks = user.getFavbooks();
        newFavBooks.removeIf(book -> book.getId() == bookID);
        user.setFavbooks(newFavBooks);
        userRepository.saveAndFlush(user);
        return user;
    }

}
