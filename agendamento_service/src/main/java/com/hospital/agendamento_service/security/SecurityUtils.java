package com.hospital.agendamento_service.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public static boolean usuarioPossuiRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(r -> r.equals("ROLE_" + role)); // ex: ROLE_MEDICO
    }

    public static boolean isPaciente() {
        return usuarioPossuiRole("PACIENTE");
    }

    public static boolean isMedico() {
        return usuarioPossuiRole("MEDICO");
    }

    public static boolean isEnfermeiro() {
        return usuarioPossuiRole("ENFERMEIRO");
    }
}
