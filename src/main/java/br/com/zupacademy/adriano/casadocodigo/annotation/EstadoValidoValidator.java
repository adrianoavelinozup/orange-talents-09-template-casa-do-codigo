package br.com.zupacademy.adriano.casadocodigo.annotation;

import br.com.zupacademy.adriano.casadocodigo.controller.form.ClienteForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EstadoValidoValidator implements ConstraintValidator<EstadoValido, ClienteForm> {
    private String message;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(EstadoValido constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(ClienteForm clienteForm, ConstraintValidatorContext constraintValidatorContext) {

        String jpql = "SELECT estado FROM Estado estado WHERE estado.pais.id = :pPaisId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("pPaisId", clienteForm.getPaisId());
        int quantidadeDeEstadosPorPais = query.getResultList().size();

        if (quantidadeDeEstadosPorPais > 0) {
            String jpqlEstadoPertenceAoPais = "SELECT estado FROM Estado estado WHERE estado.pais.id = :pPaisId AND estado.id = :pEstadoId";
            Query queryEstadoPertenceAoPais = entityManager.createQuery(jpqlEstadoPertenceAoPais);
            queryEstadoPertenceAoPais.setParameter("pPaisId", clienteForm.getPaisId());
            queryEstadoPertenceAoPais.setParameter("pEstadoId", clienteForm.getEstadoId());
            boolean estadoPertenceAoPais = !queryEstadoPertenceAoPais.getResultList().isEmpty();

            message = "Selecione um Estado válido";
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("estadoId").addConstraintViolation();
            return estadoPertenceAoPais;
        }
        message = "Estado não pertence a este País";
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("estadoId").addConstraintViolation();
        return clienteForm.getEstadoId() == null;
    }
}
