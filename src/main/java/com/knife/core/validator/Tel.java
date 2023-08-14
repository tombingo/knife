package com.knife.core.validator;

import cn.hutool.core.util.PhoneUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Null;
import java.lang.annotation.*;


/**
 * @author 86151
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = Tel.IsPhoneValidator.class)
public @interface Tel {

    String value() default "isPhone";

    String message() default "不正确的手机号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Null[] value();
    }

    class IsPhoneValidator implements ConstraintValidator<Tel, String>
    {

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return PhoneUtil.isMobile(s);
        }
    }
}
