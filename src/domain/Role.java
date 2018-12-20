package domain;

public enum Role {
    GUEST("role.guest"), CLIENT("role.client"), ADMIN("role.admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }

}
