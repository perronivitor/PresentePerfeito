package com.hacka.presenteperfeito.core.common.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}