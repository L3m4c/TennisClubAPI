package enums;

public enum RoleEnum {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    RoleEnum(String role) {
        this.role = role;
    }
    public String getValue() {
        return this.role;
    }
    private String role;
}
