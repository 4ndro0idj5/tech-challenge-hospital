package com.hospital.agendamento_service;

import com.hospital.agendamento_service.model.Usuario;
import com.hospital.agendamento_service.model.role.Role;
import com.hospital.agendamento_service.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class AgendamentoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioRepository repo, PasswordEncoder encoder) {
		return args -> {
			repo.save(Usuario.builder()
					.username("medico1")
					.password(encoder.encode("1234"))
					.roles(Set.of(Role.ROLE_MEDICO))
					.build());

			repo.save(Usuario.builder()
					.username("enfermeiro1")
					.password(encoder.encode("1234"))
					.roles(Set.of(Role.ROLE_ENFERMEIRO))
					.build());

			repo.save(Usuario.builder()
					.username("paciente1")
					.password(encoder.encode("1234"))
					.roles(Set.of(Role.ROLE_PACIENTE))
					.build());
		};
	}

}
