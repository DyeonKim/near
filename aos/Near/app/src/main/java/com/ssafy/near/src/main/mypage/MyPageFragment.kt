package com.ssafy.near.src.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ssafy.near.R
import com.ssafy.near.config.ApplicationClass.Companion.sSharedPreferences
import com.ssafy.near.config.BaseFragment
import com.ssafy.near.databinding.FragmentMyPageBinding
import com.ssafy.near.repository.UserRepository
import com.ssafy.near.src.UserViewModel
import com.ssafy.near.src.UserViewModelFactory
import com.ssafy.near.src.edituserinfo.EditUserInfoActivity
import com.ssafy.near.src.login.LoginActivity

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initEvent()
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(requireActivity(), UserViewModelFactory(UserRepository()))
            .get(UserViewModel::class.java)

        userViewModel.getUserInfo().observe(viewLifecycleOwner, {
            when(it) {
                null -> setLogoutState()
                else -> {
                    binding.tvNickName.text = it.nickname
                    setLoginState()
                }
            }
        })
    }

    private fun initView() {
        userViewModel.loadUserInfo(sSharedPreferences.getUserToken())
    }

    private fun initEvent() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        binding.tvLogout.setOnClickListener {
            sSharedPreferences.deleteUser()
            showToastMessage("로그아웃 되었습니다")
            setLogoutState()
        }

        binding.layoutMemberModify.setOnClickListener {
            startActivity(Intent(requireContext(), EditUserInfoActivity::class.java))
        }
    }

    private fun setLoginState() {
        binding.tvLogin.visibility = View.GONE
        binding.tvLogout.visibility = View.VISIBLE
    }

    private fun setLogoutState() {
        binding.tvNickName.text = "로그인이 필요합니다"
        binding.tvLogin.visibility = View.VISIBLE
        binding.tvLogout.visibility = View.GONE
    }
}