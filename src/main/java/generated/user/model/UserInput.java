package generated.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import generated.user.model.UserRoleInput;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserInput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-10T01:15:25.711826441+03:00[Europe/Kiev]")


public class UserInput   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("imageUrl")
  private String imageUrl = null;

  @JsonProperty("roles")
  @Valid
  private List<UserRoleInput> roles = null;

  public UserInput username(String username) {
    this.username = username;
    return this;
  }

  /**
   * User's username
   * @return username
   **/
  @Schema(required = true, description = "User's username")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserInput password(String password) {
    this.password = password;
    return this;
  }

  /**
   * User's password
   * @return password
   **/
  @Schema(required = true, description = "User's password")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserInput email(String email) {
    this.email = email;
    return this;
  }

  /**
   * User's email address
   * @return email
   **/
  @Schema(required = true, description = "User's email address")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserInput phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * User's phone number
   * @return phone
   **/
  @Schema(description = "User's phone number")
  
    public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public UserInput imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * URL for the user's profile image
   * @return imageUrl
   **/
  @Schema(description = "URL for the user's profile image")
  
    public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public UserInput roles(List<UserRoleInput> roles) {
    this.roles = roles;
    return this;
  }

  public UserInput addRolesItem(UserRoleInput rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * List of roles to assign to the user
   * @return roles
   **/
  @Schema(description = "List of roles to assign to the user")
      @Valid
    public List<UserRoleInput> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRoleInput> roles) {
    this.roles = roles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInput userInput = (UserInput) o;
    return Objects.equals(this.username, userInput.username) &&
        Objects.equals(this.password, userInput.password) &&
        Objects.equals(this.email, userInput.email) &&
        Objects.equals(this.phone, userInput.phone) &&
        Objects.equals(this.imageUrl, userInput.imageUrl) &&
        Objects.equals(this.roles, userInput.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, email, phone, imageUrl, roles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInput {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
