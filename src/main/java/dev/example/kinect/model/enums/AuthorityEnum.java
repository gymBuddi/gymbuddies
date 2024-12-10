package dev.example.kinect.model.enums;

public enum AuthorityEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN");

    AuthorityEnum(final String authority) {
        this.authority = authority;
    }

    private String authority;

    public String getAuthority() {
        return authority;
    }
}
