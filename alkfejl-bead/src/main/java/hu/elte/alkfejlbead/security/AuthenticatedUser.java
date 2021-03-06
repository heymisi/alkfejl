package hu.elte.alkfejlbead.security;



import hu.elte.alkfejlbead.entities.Student;
import hu.elte.alkfejlbead.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;



@RequestScope
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUser {
    private Student user;
}
