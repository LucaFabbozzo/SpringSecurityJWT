package com.fabbluca;

import com.fabbluca.models.Erole;
import com.fabbluca.models.RoleEntity;
import com.fabbluca.models.UserEntity;
import com.fabbluca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;


	@Bean
	CommandLineRunner init(){
		return args -> {
			UserEntity userEntity = UserEntity.builder()
					.email("santiango@gmail.com")
					.username("santiago")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(Erole.valueOf(Erole.ADMIN.name()))
							.build()))
					.build();


			UserEntity userEntity2 = UserEntity.builder()
					.email("luca@gmail.com")
					.username("Luca")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(Erole.valueOf(Erole.USER.name()))
							.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("andrea@gmail.com")
					.username("Andrea")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(Erole.valueOf(Erole.INVITED.name()))
							.build()))
					.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);

		};
	}

}
