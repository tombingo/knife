package com.knife.core.validator;

import com.knife.core.validator.util.ValidatorUtils;

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
@Constraint(validatedBy = Chinese.HasChineseValidator.class)
public @interface Chinese {

    String message() default "必须是中文";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Null[] value();
    }
    class HasChineseValidator implements ConstraintValidator<Chinese, String>
    {
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            char[] ch = s.toCharArray();
            boolean flag = false;
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                if (ValidatorUtils.isChinese(c)) {
                    flag = true ;
                    break;
                }
            }
            return flag;
        }
    }

}
