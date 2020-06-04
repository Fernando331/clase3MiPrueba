package sw2.clase03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.formLogin()
        .loginPage("/loginForm")
        .loginProcessingUrl("/processLogin")
        //.usernameParameter("dni")
        //.passwordParameter("ctm")
        .defaultSuccessUrl("/redirectByRole",true);

        http.logout().logoutUrl("/cerrar").logoutSuccessUrl("/territory")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);


        http.authorizeRequests().antMatchers("/shipper","/shipper/*").hasAuthority("admin");
        http.authorizeRequests().antMatchers("/category","/category/*").hasAnyAuthority("admin","logistica");

        /*
        http.authorizeRequests().
                antMatchers("/shipper","/shipper/*").authenticated().
                antMatchers("/category","/category/*").authenticated();
        */
        http.authorizeRequests().anyRequest().permitAll();

    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT email,pwd,activo FROM northwind.usuario WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT u.email, r.nombre FROM northwind.usuario u, northwind.rol r WHERE u.idrol = r.idrol AND u.activo = 1 AND u.email = ?");
    }
}
