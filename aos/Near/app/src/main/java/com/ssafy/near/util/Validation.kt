package com.ssafy.near.util

import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

object Validation {
    val ID = "^[a-zA-Z0-9]*\$"
    val NICKNAME = "^[a-zA-Z가-힣0-9]*\$"
    val PW = "^[a-zA-Z0-9!@#$%^&*]*\$"

    fun validateId(id: String, tiLayout: TextInputLayout): Boolean {
        if (Pattern.matches(ID, id) == false) {
            tiLayout.error = "사용할 수 없는 문자를 포함하고 있습니다."
            return false
        }
        if (id.length < 6 || id.length > 15) {
            tiLayout.error = "아이디는 6 ~ 15자입니다."
            return false
        }
        tiLayout.error = ""
        return true
    }

    fun validateNickname(nickname: String, tiLayout: TextInputLayout): Boolean {
        if (Pattern.matches(NICKNAME, nickname) == false) {
            tiLayout.error = "사용할 수 없는 문자를 포함하고 있습니다."
            return false
        }
        if (nickname.isEmpty() || nickname.length > 6) {
            tiLayout.error = "닉네임은 1 ~ 6자입니다."
            return false
        }
        tiLayout.error = ""
        return true
    }

    fun validateEmail(email: String, tiLayout: TextInputLayout): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS

        if (pattern.matcher(email).matches() == false) {
            tiLayout.error = "올바른 이메일 형식이 아닙니다."
            return false
        }
        tiLayout.error = ""
        return true
    }

    fun validatePw(pw: String, tiLayout: TextInputLayout) {
        if (Pattern.matches(PW, pw) == false) {
            tiLayout.error = "사용할 수 없는 문자를 포함하고 있습니다."
        }else if (pw.length < 8 || pw.length > 20) {
            tiLayout.error = "비밀번호는 8 ~ 20자입니다."
        }else {
            tiLayout.error = ""
        }
    }

    fun confirmPw(checkedPw: String, pw: String, tiLayout: TextInputLayout): Boolean {
        if (checkedPw == pw) {
            tiLayout.error = ""
            if (pw.length < 8 || pw.length > 20) {
                return false
            }
            return true
        }
        tiLayout.error = "비밀번호가 일치하지 않습니다."
        return false
    }
}