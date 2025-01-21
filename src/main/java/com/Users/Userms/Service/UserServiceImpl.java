package com.Users.Userms.Service;

import com.Users.Userms.Model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl {


    @Autowired
    private final RestTemplate restTemplate;

    @Value("${DB.SERVICE.URL}")
    private final String DB_SERVICE_URL;

    public UserServiceImpl(@Value("${DB.SERVICE.URL}") String DBMicroserviceUrl
                        ,RestTemplate restTemplate) {
        this.DB_SERVICE_URL = DBMicroserviceUrl;
        this.restTemplate=restTemplate;
    }

    public UserDTO createUser(UserDTO user) {
        return restTemplate.postForObject(DB_SERVICE_URL + "/register", user, UserDTO.class);
    }

    public UserDTO getUserById(Long userId) {
        System.out.println("Get User url: "+DB_SERVICE_URL + "/" + userId);
        return restTemplate.getForObject(DB_SERVICE_URL + "/" + userId, UserDTO.class);
    }
    /*public UserDTO deleteById(Long user_id)
    {
        return restTemplate.getForObject(DB_SERVICE_URL+"/"+user_id,UserDTO.class);
    }*/
    public UserDTO updateUserById(Long userId,UserDTO user) {
        UserDTO existingUser = restTemplate.getForObject(DB_SERVICE_URL + "/" + userId, UserDTO.class);

        if (existingUser == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Merge the fields (keep `created_at` intact)
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone_number(user.getPhone_number());
        existingUser.setUpdated_at(LocalDateTime.now());
        String updateUrl = DB_SERVICE_URL+"/" + userId;
        restTemplate.put(updateUrl, existingUser);

        return existingUser; // Return the updated user
    }
    public String deleteUser(Long userId) {
        restTemplate.delete(DB_SERVICE_URL + "/" + userId);
        return "User with ID " + userId + " deleted successfully.";
    }
}
