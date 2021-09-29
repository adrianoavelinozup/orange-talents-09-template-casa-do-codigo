package br.com.zupacademy.adriano.casadocodigo.annotation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteValidator implements ConstraintValidator<Existe, Object> {
    private String nomeDoCampo;
    private Class<?> classeDaEntidade;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(Existe constraintAnnotation) {
        nomeDoCampo = constraintAnnotation.nomeDoCampo();
        classeDaEntidade = constraintAnnotation.classeDaEntidade();
    }

    @Override
    public boolean isValid(Object valorDoNomeDoCampo, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT e FROM " + classeDaEntidade.getName() + " e WHERE " + nomeDoCampo + " = :pValorDoNomeDoCampo";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("pValorDoNomeDoCampo", valorDoNomeDoCampo);
        List resultList = query.getResultList();
        return !resultList.isEmpty();
    }
}
