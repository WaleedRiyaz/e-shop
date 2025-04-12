package com.wizcode.eshop.service.user;

import com.wizcode.eshop.dto.UserDTO;
import com.wizcode.eshop.model.User;
import com.wizcode.eshop.request.CreateUserRequest;
import com.wizcode.eshop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDTO convertUserToDto(User user);
}
