package com.skeep.mahjongvision

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.skeep.mahjongvision.databinding.ActivityConfirmationBinding
import com.skeep.mahjongvision.databinding.ActivityGuideBinding

class ConfirmationActivity : AppCompatActivity() {

    val sonPaiResouces = listOf(
        R.drawable.majan1_manzu4,
        R.drawable.majan1_manzu5,
        R.drawable.majan1_manzu6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = "https://jsonplaceholder.typicode.com/users" // 주소

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("get", it)
                Toast.makeText(this, "get", Toast.LENGTH_SHORT).show()
                //val intent= Intent(this, SurveyActivity::class.java)
                //startActivity(intent)
            },
            { error->
                Log.d("get", "error $error")
            }
        )
        //GET

        //queue.add(stringRequest)
        binding.testbtn.setOnClickListener{
            queue.add(stringRequest)
        }

        //패 넣는 부분 목업 코드
        for (resource in sonPaiResouces) {
            val pai = ImageView(this)
            pai.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            pai.setImageResource(resource)

            // 이미지뷰를 컨테이너에 추가합니다.
            binding.sonPai.addView(pai)
        }

        //맞아 틀려 버튼
        binding.acceptBtn.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
        }

        binding.declineBtn.setOnClickListener {
            //val intent = Intent(this, CameraActivity::class.java)
            //startActivity(intent)
            finish()
        }
    }
}