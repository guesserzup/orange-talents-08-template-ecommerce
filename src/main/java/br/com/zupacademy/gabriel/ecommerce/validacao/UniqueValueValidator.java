package br.com.zupacademy.gabriel.ecommerce.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> klass;
    private String campoParaValidar;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        campoParaValidar = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
        String query = "select 1 from " + klass.getName() + " where " + campoParaValidar + " = :param";
        Query sql = entityManager.createQuery(query);

        sql.setParameter("param", valor);

        List<?> resultado = sql.getResultList();

        Assert.isTrue(resultado.size() <= 1, "Foi encontrado mais de 1 registro na tabela " + klass.getName() + " " +
                "com o mesmo " + campoParaValidar + " = " + valor);

        return resultado.isEmpty();
    }
}
