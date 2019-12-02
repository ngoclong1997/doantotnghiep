package com.drato.graduationthesis.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user",schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min = 4, max = 50, message = "{constraints.min-4-max-50}")
    private String username;

    @Column(name = "firstname")
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 50, message = "{constraints.length-smaller-50}")
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 100, message = "{constraints.length-smaller-100}")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=5, max = 250, message = "{constraints.min-5-max-250}")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "{constraints.not-empty}")
    @Size(min=5, max = 250, message = "{constraints.min-5-max-250}")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "added_by")
    private String addBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @NotEmpty(message = "Trường này không được bỏ trống")
    private Collection<Role> roles;

    public User() {
    }

    public User(@NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 3, max = 50, message = "Trường này phải lớn hơn 4 và nhỏ hơn 50 ký tự") String username, @NotEmpty(message = "Trường này không được bỏ trống") @Size(max = 50, message = "Trường này phải nhỏ hơn 50 ký tự") String firstName, @NotEmpty(message = "Trường này không được bỏ trống") @Size(max = 50, message = "Trường này phải nhỏ hơn 50 ký tự") String lastName, @NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 5, max = 50, message = "Trường này phải lớn hơn 5 và nhỏ hơn 50 ký tự") String email, @NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 5, max = 50, message = "Trường này phải lớn hơn 5 và nhỏ hơn 50 ký tự") String password, boolean enabled) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public User(@NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 3, max = 50, message = "Trường này phải lớn hơn 4 và nhỏ hơn 50 ký tự") String username, @NotEmpty(message = "Trường này không được bỏ trống") @Size(max = 50, message = "Trường này phải nhỏ hơn 50 ký tự") String firstName, @NotEmpty(message = "Trường này không được bỏ trống") @Size(max = 50, message = "Trường này phải nhỏ hơn 50 ký tự") String lastName, @NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 5, max = 50, message = "Trường này phải lớn hơn 5 và nhỏ hơn 50 ký tự") String email, @NotEmpty(message = "Trường này không được bỏ trống") @Size(min = 5, max = 50, message = "Trường này phải lớn hơn 5 và nhỏ hơn 50 ký tự") String password, boolean enabled, @NotEmpty(message = "Trường này không được bỏ trống") Collection<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
