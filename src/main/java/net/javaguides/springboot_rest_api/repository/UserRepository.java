package net.javaguides.springboot_rest_api.repository;

import net.javaguides.springboot_rest_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
