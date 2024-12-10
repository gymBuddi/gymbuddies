package dev.example.kinect;

import dev.example.kinect.model.Authority;
import dev.example.kinect.model.Role;
import dev.example.kinect.model.enums.AuthorityEnum;
import dev.example.kinect.model.enums.RoleEnum;
import dev.example.kinect.repository.AuthorityRepository;
import dev.example.kinect.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class KinectApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;
	private final AuthorityRepository authorityRepository;
	public KinectApplication(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(KinectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Authority authorityAdmin = Authority.builder()
						.authority(AuthorityEnum.ROLE_ADMIN)
						.build();
		authorityAdmin.setCreationDate(LocalDateTime.now());
		authorityAdmin.setLastModifiedDate(null);
		authorityRepository.save(authorityAdmin);
		*//* -------------------- *//*
		Authority authorityUser = Authority.builder()
				.authority(AuthorityEnum.ROLE_USER).build();
		authorityUser.setCreationDate(LocalDateTime.now());
		authorityUser.setLastModifiedDate(null);
		authorityRepository.save(authorityUser);

		List<Role> roles = Arrays.asList(
				Role.builder().role(RoleEnum.ADMIN)
						.authorities(Set.of(authorityAdmin))
						.build(),
				Role.builder().role(RoleEnum.USER)
						.authorities(Set.of(authorityUser))
						.build()
		);
		roleRepository.saveAll(roles);*/

	}
}
