package medi.voli.api.domain.usuarios.dto;

public record Loginresponse(
        String token,
        String expirationToken,
        String time
) {
}
