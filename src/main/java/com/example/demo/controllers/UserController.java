package com.example.demo.controllers;

import com.example.demo.entities.BookEntity;
import com.example.demo.entities.CustomEntity;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.Permission;
import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<CustomEntity> userDetails() {
        final CustomEntity userDetails = (CustomEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/fav-books")
    public ResponseEntity<Set<BookEntity>> userFavBooks() {
        final CustomEntity userDetails = (CustomEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getFavoritebook(userDetails.getUsername()));
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/fav-books/{bookId}", method = RequestMethod.POST)
    public ResponseEntity<Set<BookEntity>> bookFormControllerAdd(@PathVariable String bookId) {
        final CustomEntity userDetails = (CustomEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userService.addFavoriteBook(userDetails.getUsername(), Integer.parseInt(bookId));
        return ResponseEntity.ok(userEntity.getFavbooks());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/fav-books/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<Set<BookEntity>> bookFormControllerDelete(@PathVariable String bookId) {
        final CustomEntity userDetails = (CustomEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userService.delFavoriteBook(userDetails.getUsername(), Integer.parseInt(bookId));
        return ResponseEntity.ok(userEntity.getFavbooks());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody final UserDto userDto) {
        List<PermissionEntity> permissions = new ArrayList<>();
        permissions.add(new PermissionEntity(Permission.VIEW_BOOKS, 1));
        permissions.add(new PermissionEntity(Permission.ADD_TO_FAVORITES, 1));
        UserEntity userToRegister = userService.registerUser(userDto.getLogin(),
                                                             userDto.getPassword(),
                                                             userDto.getCustomAuthField(),
                                                             permissions);
        UserDto resUserDto = new UserDto();
        resUserDto.setId(userToRegister.getId());
        resUserDto.setLogin(userToRegister.getLogin());
        resUserDto.setPassword(null);
        resUserDto.setCustomAuthField(userToRegister.getCustomAuthField());
        return ResponseEntity.ok(resUserDto);
    }
}
