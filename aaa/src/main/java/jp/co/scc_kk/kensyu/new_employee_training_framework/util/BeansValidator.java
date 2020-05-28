package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeansValidator {

    private BeansValidator() {
        // Do nothing
    }

    /**
     * JavaBeansのアノテーションに従ってバリデーションチェックを行います。
     *
     * @param target バリデーション対象のJavaBeansインスタンス
     * @return エラーメッセージのリスト
     */
    public static List<String> validate(Object target) {
        if (target == null) {
            throw new IllegalArgumentException();
        }

        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);

        final List<String> msgKeyList =
                constraintViolations.stream().map(ConstraintViolation<Object>::getMessage).collect(Collectors.toList());

        Collections.sort(msgKeyList);
        return msgKeyList.stream().map(MessageUtil::getDisplayMessage).collect(Collectors.toList());

    }
}
