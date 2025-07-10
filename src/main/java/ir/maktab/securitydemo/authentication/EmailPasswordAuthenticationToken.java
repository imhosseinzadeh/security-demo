package ir.maktab.securitydemo.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {
    private final String email;

    private String password;

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>EmailPasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     */
    private EmailPasswordAuthenticationToken(String email, String password) {
        super(null);
        this.email = email;
        this.password = password;
        super.setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     */
    public EmailPasswordAuthenticationToken(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
        this.password = password;
        super.setAuthenticated(true);
    }

    public static EmailPasswordAuthenticationToken unauthenticated(String email, String password) {
        return new EmailPasswordAuthenticationToken(email, password);
    }

    @Override
    public String getPrincipal() {
        return this.email;
    }

    @Override
    public String getCredentials() {
        return this.password;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EmailPasswordAuthenticationToken that = (EmailPasswordAuthenticationToken) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }
}
