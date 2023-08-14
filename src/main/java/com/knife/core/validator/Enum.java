package com.knife.core.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.Null;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author tlj
 * @date 2019/7/9
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Constraint(validatedBy = Enum.EnumValidatorHandle.class)
public @interface Enum {
    Class<?> value();
    String message() default "入参值不在正确枚举中";
    String method() default "name";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    /**
     * @author tlj
     * @date 2019/7/9
     */
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Null[] value();
    }
    class EnumValidatorHandle implements ConstraintValidator<Enum, Object>, Annotation {
        //存放所有的枚举值
        private final ArrayList<Object> values = new ArrayList<>();
        @Override
        public void initialize(Enum enumValidator) {
            Class<?> clz = enumValidator.value();
            //获取所有的枚举元素
            Object[] objects = clz.getEnumConstants();
            try {
                //拿到对应的方法
                Method method = clz.getMethod(enumValidator.method());
                if (Objects.isNull(method)) {
                   throw  new ValidatorException(
                           String.format("枚举对象%s缺少名为%s的方法",clz.getName(),enumValidator.method())
                   );
                }
                Object value;
                for (Object obj : objects) {
                    value = method.invoke(obj);
                    values.add(value);
                }
            } catch (Exception e) {
                //log.error("处理枚举校验异常:{}", e);
            }
        }


        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
//            if (value instanceof String) {
//                String valueStr = (String)value;
//                return StringUtils.isEmpty(valueStr)|| values.contains(value);
//            }
//            return Objects.isNull(value) || values.contains(value);
            return values.contains(value);
        }
    }

}