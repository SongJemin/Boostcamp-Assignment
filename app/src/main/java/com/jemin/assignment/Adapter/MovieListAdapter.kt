package com.jemin.assignment.Adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.jemin.assignment.Item.MovieItem
import com.jemin.assignment.R
import com.jemin.assignment.ViewHolder.MoviewListViewHolder

class MovieListAdapter (private var movieItem : ArrayList<MovieItem>, var requestManager : RequestManager) : RecyclerView.Adapter<MoviewListViewHolder>(){

    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListener(I : View.OnClickListener)
    {
        onItemClick = I
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewListViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        mainView.setOnClickListener(onItemClick)
        return MoviewListViewHolder(mainView)
    }

    override fun getItemCount(): Int = movieItem.size

    //데이터클래스와 뷰홀더를 이어준다.
    override fun onBindViewHolder(holder: MoviewListViewHolder, position: Int) {

        requestManager.load(movieItem[position].movieImageUrl).centerCrop().into(holder.movieImg)
        holder.movieTitle.setText(Html.fromHtml(movieItem[position].movieTitle))
        holder.movieRatingBar.rating = (movieItem[position].movieRating!!/2)
        holder.moviePubDate.text = movieItem[position].moviePubDate.toString()
        holder.movieDirector.text = movieItem[position].movieDirector
        holder.movieActor.text = movieItem[position].movieActor
    }
}