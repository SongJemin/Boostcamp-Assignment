package com.jemin.assignment.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.jemin.assignment.R

class MoviewListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var movieImg : ImageView = itemView!!.findViewById(R.id.item_movie_img)
    var movieTitle : TextView = itemView!!.findViewById(R.id.item_movie_title)
    var movieRatingBar : RatingBar = itemView!!.findViewById(R.id.item_movie_ratingbar)
    var moviePubDate : TextView = itemView!!.findViewById(R.id.item_movie_pubdate)
    var movieDirector : TextView = itemView!!.findViewById(R.id.item_movie_director)
    var movieActor: TextView = itemView!!.findViewById(R.id.item_movie_actor)

}