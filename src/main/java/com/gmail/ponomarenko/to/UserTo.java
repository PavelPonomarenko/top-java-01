package com.gmail.ponomarenko.to;

import com.gmail.ponomarenko.model.Role;
import com.gmail.ponomarenko.model.User;
import com.gmail.ponomarenko.util.HasPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserTo implements HasPassword {
    protected int id;

    @NotEmpty
    protected String name;

    @NotEmpty
    @Email
    protected String email;

    @Size(min = 5, max = 15, message = " must between 5 and 15 characters")
    protected String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(User user) {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }

    public User asNewUser() {
        return new User(null, name, email, password, true, Role.ROLE_USER);
    }
}
