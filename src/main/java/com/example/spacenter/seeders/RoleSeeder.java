package com.example.spacenter.seeders;


import com.example.spacenter.model.entity.Role;
import com.example.spacenter.model.entity.RoleEnum;
import com.example.spacenter.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (this.roleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values())
                    .forEach(roleEnum -> {
                        this.roleRepository.save(new Role(roleEnum.name()));
                    });
        }
    }
}
