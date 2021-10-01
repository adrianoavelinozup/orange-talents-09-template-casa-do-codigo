package br.com.zupacademy.adriano.casadocodigo.annotation;

import br.com.zupacademy.adriano.casadocodigo.controller.form.EstadoForm;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NomeDeEstadoUnicoPorPaisValidator implements ConstraintValidator<NomeDeEstadoUnicoPorPais, EstadoForm> {
    private String message;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(NomeDeEstadoUnicoPorPais constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(EstadoForm estadoForm, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT estado FROM Estado estado WHERE estado.nome = :pValorDoNomeDoCampo AND estado.pais.id = :pPaisId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("pValorDoNomeDoCampo", estadoForm.getNome());
        query.setParameter("pPaisId", estadoForm.getIdPais());
        List resultList = query.getResultList();

        Assert.state(resultList.size() <=1, "Foi encontrado mais de um Estado com o atributo nome = "+ estadoForm.getNome() + "no mesmo país");

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("nome").addConstraintViolation();

        return resultList.isEmpty();
    }
}
