package gsc.projects.usersservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int age;

    private String email;

    private String address;

    public static UserBuilder builder(){
        return new UserBuilder();
    }
    public static class UserBuilder {

        private User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder withName(String name){
            user.setName(name);
            return this;
        }

        public UserBuilder withAge(int age){
            user.setAge(age);
            return this;
        }

        public UserBuilder withEmail(String email){
            user.setEmail(email);
            return this;
        }

        public UserBuilder withAddress(String address){
            user.setAddress(address);
            return this;
        }

        public User build(){
            return user;
        }

    }
}
