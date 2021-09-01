package br.com.zupacademy.gabriel.ecommerce.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = {UniqueValueValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "Já existe um registro com este mesmo valor";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();
    Class<?> domainClass();
}
