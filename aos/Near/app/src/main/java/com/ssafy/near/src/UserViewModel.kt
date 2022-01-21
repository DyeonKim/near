package com.ssafy.near.src

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.near.dto.SignResponse
import com.ssafy.near.dto.UserInfo
import com.ssafy.near.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val signResponse = userRepository._signResponse
    private val userInfo = userRepository._userInfo
    private val checkedId = userRepository._checkedId
    private val checkedNickname = userRepository._checkedNickname
    private val checkedEmail = userRepository._checkedEmail


    fun getSignResponse(): LiveData<SignResponse> {
        return signResponse
    }

    fun getUserInfo(): LiveData<UserInfo?> {
        return userInfo
    }

    fun getCheckedId(): LiveData<Boolean> {
        return checkedId
    }

    fun getCheckedNickname(): LiveData<Boolean> {
        return checkedNickname
    }

    fun getCheckedEmail(): LiveData<Boolean> {
        return checkedEmail
    }

    fun login(id: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.login(id, pw)
        }
    }

    fun signUp(id: String, nickname: String, email: String, pw: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.signUp(id, nickname, email, pw)
        }
    }

    fun loadUserInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.loadUserInfo(token)
        }
    }

    fun checkDuplicatedId(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedId(id)
        }
    }

    fun checkDuplicatedNickname(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedNickname(nickname)
        }
    }

    fun checkDuplicatedEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.checkDuplicatedEmail(email)
        }
    }
}