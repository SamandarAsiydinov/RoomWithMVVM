package com.samsdk.roomwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.samsdk.roomwithmvvm.adapter.RvAdapter
import com.samsdk.roomwithmvvm.database.UserEntity
import com.samsdk.roomwithmvvm.databinding.ActivityMainBinding
import com.samsdk.roomwithmvvm.utils.toast
import com.samsdk.roomwithmvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        rvAdapter = RvAdapter()
        setupRecyclerView()

        viewModel.getAllUsersObserver().observe(this) {
            rvAdapter.submitList(it)
        }

        binding.saveButton.setOnClickListener {
            saveUserToDatabase()
        }
    }

    private fun saveUserToDatabase() {
        val name = binding.nameInput.text.toString().trim()
        val email = binding.emailInput.text.toString().trim()
        val phone = binding.phoneInput.text.toString().trim()
        if (isNotEmpty(name, email, phone)) {
            val user = UserEntity(0, name, email, phone)
            viewModel.insertUserInfo(user)
            toast("Data Saved")
            binding.nameInput.text?.clear()
            binding.emailInput.text?.clear()
            binding.phoneInput.text?.clear()
        } else {
            toast("Please enter data!")
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = rvAdapter
        val divider = DividerItemDecoration(this@MainActivity, VERTICAL)
        addItemDecoration(divider)
    }

    private fun isNotEmpty(s1: String, s2: String, s3: String): Boolean {
        return !(TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2) && TextUtils.isEmpty(s3))
    }
}