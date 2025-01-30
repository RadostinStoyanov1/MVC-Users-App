package usersmvc.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class AppMasterUserDetails extends User {

    private final UUID uuid;
    private final String firstName;
    private final String lastName;


    public AppMasterUserDetails(UUID uuid, String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName) {
        super(username, password, authorities);
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
