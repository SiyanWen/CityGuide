public class UserDto {
    private String username;
    private String email;
    private String password;
    private String destinationCity;

    // Constructor
    public UserDto(String username, String email, String password, String destinationCity) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.destinationCity = destinationCity;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
}
