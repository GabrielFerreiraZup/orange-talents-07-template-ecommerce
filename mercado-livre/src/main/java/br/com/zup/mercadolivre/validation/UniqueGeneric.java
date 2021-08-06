package br.com.zup.mercadolivre.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueGenericValidation.class)
public @interface UniqueGeneric {
    String message() default "Campo deve ser único";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();

}
