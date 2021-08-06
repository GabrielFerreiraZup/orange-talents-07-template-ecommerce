package br.com.zup.mercadolivre.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueGenericValidation implements ConstraintValidator<UniqueGeneric,String> {
    @Autowired
    EntityManager entityManager;

    private Class<?> classeUsada;
    private String campo;

    @Override
    public void initialize(UniqueGeneric constraintAnnotation) {
        classeUsada = constraintAnnotation.domainClass();
        campo = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery("Select 1 from "+ classeUsada.getName() + " where " +campo +"=:value");
        query.setParameter("value",value);
        List<?> lista = query.getResultList();
        if (lista.size()<1){
            return true;
        }
        else{
            return false;
        }

    }

}
