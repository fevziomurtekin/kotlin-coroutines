package com.fevziomurtekin.kotlin_coroutines_example

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Credentials
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.fevziomurtekin.kotlin_coroutines_example.Adapter.ReposAdapter
import com.fevziomurtekin.kotlin_coroutines_example.Retro.RetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_layout.*

class MainActivity : AppCompatActivity(){

    private val username : String ="GITHUB USERNAME"
    private val password : String ="GITHUB PASSWORD"

    private lateinit var avatar :CircleImageView
    private lateinit var usernames: TextView
    private lateinit var bio : TextView
    private lateinit var follower_count : TextView
    private lateinit var following_count : TextView
    private lateinit var repos_count : TextView
    private lateinit var recycler : RecyclerView
    private lateinit var context:Context

    private val service by lazy{
        RetrofitInterface.create()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        avatar = findViewById(R.id.avatar)
        usernames = findViewById(R.id.usernmae)
        bio = findViewById(R.id.bio)
        follower_count = findViewById(R.id.follower_count)
        following_count = findViewById(R.id.following_count)
        repos_count = findViewById(R.id.repos_count)
        recycler = findViewById(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        GlobalScope.launch(Dispatchers.Main) {
            val userInfo = service.getUserInfo(okhttp3.Credentials.basic(username,password))
                .await()
            val repos = service.getRepos(okhttp3.Credentials.basic(username,password))
                .await()


            Picasso.get().load(userInfo.avatar_url).into(avatar)

            usernames.setText(userInfo.name)
            follower_count.setText(userInfo.followers.toString())
            following_count.setText(userInfo.following.toString())
            bio.setText(Html.fromHtml(userInfo.bio,Html.FROM_HTML_MODE_LEGACY))

            recycler.adapter = ReposAdapter(repos)

            Log.e("deneme",userInfo.toString())
            Log.e("deneme",repos.toString())

        }
    }

}