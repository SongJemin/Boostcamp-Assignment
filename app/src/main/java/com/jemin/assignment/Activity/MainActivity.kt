package com.jemin.assignment.Activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jemin.assignment.Adapter.MovieListAdapter
import com.jemin.assignment.Item.MovieItem
import com.jemin.assignment.Network.ApiClient
import com.jemin.assignment.Network.Get.GetMovieList
import com.jemin.assignment.Network.Get.Response.GetMovieListResponse
import com.jemin.assignment.Network.NetworkService
import com.jemin.assignment.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var movieListAdapter : MovieListAdapter
    var movieListItem = ArrayList<MovieItem>()
    lateinit var requestManager : RequestManager // 이미지를 불러올 때 처리하는 변수
    lateinit var networkService : NetworkService
    var movieListData = ArrayList<GetMovieList>()
    var insertString : String  = ""
    var display : Int = 100
    var selectedLink : String = ""

    override fun onClick(v: View) {
        var idx = main_movie_recyclerview!!.getChildAdapterPosition(v)
        selectedLink = movieListData[idx].link!!
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedLink))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestManager = Glide.with(this)

        main_search_btn.setOnClickListener {
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(main_search_edit.getWindowToken(), 0)
            insertString = main_search_edit.text.toString()
            getMovieList();
        }
    }

    // 영화 리스트 가져오기
    private fun getMovieList() {
        movieListItem.clear()
        try {
            networkService = ApiClient.getRetrofit()!!.create(NetworkService::class.java)
            var getMovieListResponse = networkService.getMovieSearch("3f4er7aEKcHdEdBmlD9G","oTVOx72HSs",insertString, display) // 네트워크 서비스의 getContent 함수를 받아옴
            getMovieListResponse.enqueue(object : Callback<GetMovieListResponse> {
                override fun onResponse(call: Call<GetMovieListResponse>?, response: Response<GetMovieListResponse>?) {
                    if(response!!.isSuccessful)
                    {
                        // 결과 = 0
                        if(response.body()!!.items.size == 0)
                        {
                            Toast.makeText(applicationContext, "검색 결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show()
                        }
                        // 결과 > 0
                        else
                        {
                            movieListData = response.body()!!.items
                            for(i in 0..movieListData.size-1) {
                                // 참여중인 인원 = 0
                                movieListItem.add(MovieItem(movieListData[i].image, movieListData[i].title!!, Integer.parseInt(movieListData[i].pubDate), movieListData[i].director, movieListData[i].actor, movieListData[i].userRating!!.toFloat()))
                            }
                            movieListAdapter = MovieListAdapter(movieListItem, requestManager)
                            movieListAdapter.setOnItemClickListener(this@MainActivity)
                            main_movie_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                            main_movie_recyclerview.adapter = movieListAdapter
                            main_movie_recyclerview.setNestedScrollingEnabled(false)
                        }
                    }
                }

                override fun onFailure(call: Call<GetMovieListResponse>?, t: Throwable?) {
                    Log.v("TAG","영화 검색 서버 통신 실패" + t.toString())
                }
            })
        } catch (e: Exception) {
        }

    }
}
