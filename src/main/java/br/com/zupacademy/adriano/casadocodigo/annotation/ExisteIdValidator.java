package br.com.zupacademy.adriano.casadocodigo.annotation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {
    private String nomeDoCampo;
    private Class<?> classeDaEntidade;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        nomeDoCampo = constraintAnnotation.nomeDoCampo();
        classeDaEntidade = constraintAnnotation.classeDaEntidade();
    }

    @Override
    public boolean isValid(Object valorDoNomeDoCampo, ConstraintValidatorContext constraintValidatorContext) {
        if (valorDoNomeDoCampo == null) return true;
        String jpql = "SELECT e FROM " + classeDaEntidade.getName() + " e WHERE " + nomeDoCampo + " = :pValorDoNomeDoCampo";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("pValorDoNomeDoCampo", valorDoNomeDoCampo);

        List resultList = query.getResultList();
        Assert.isTrue(resultList.size() <=1, "aconteceu algo estranho e você tem mais de um " + classeDaEntidade + " com o atributo " + nomeDoCampo + " com o valor = " + valorDoNomeDoCampo);
        return !resultList.isEmpty();
    }
}
