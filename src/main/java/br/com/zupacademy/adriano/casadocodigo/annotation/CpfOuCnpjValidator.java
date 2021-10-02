package br.com.zupacademy.adriano.casadocodigo.annotation;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CpfOuCnpjValidator implements ConstraintValidator<CpfOuCnpj, Object> {

    private String nomeDoCampo;
    private Class<?> classeDaEntidade;

    @PersistenceContext
    private EntityManager entityManager;
    private String mensagem;

    @Override
    public void initialize(CpfOuCnpj constraintAnnotation) {
        nomeDoCampo = constraintAnnotation.nomeDoCampo();
        classeDaEntidade = constraintAnnotation.classeDaEntidade();
        mensagem = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object valorDoCampo, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT cliente FROM Cliente cliente WHERE cliente.documento = :pDocumento";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("pDocumento", valorDoCampo);
        List resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            mensagem = "já existe um cliente cadastrado com o documento " + valorDoCampo;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(mensagem).addConstraintViolation();
            return false;
        }
        return isCpfValido(valorDoCampo.toString()) || isCnpjValido(valorDoCampo.toString());
    }

    private boolean isCnpjValido(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        List<ValidationMessage> erros = cnpjValidator.invalidMessagesFor(cnpj);
        return erros.isEmpty();
    }

    private boolean isCpfValido(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
        return erros.isEmpty();
    }
}
