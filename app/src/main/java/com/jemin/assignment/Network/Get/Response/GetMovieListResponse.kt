package com.jemin.assignment.Network.Get.Response

import com.jemin.assignment.Network.Get.GetMovieList

data class GetMovieListResponse (
        var lastBuildDate : String?,
        var total : Int?,
        var start : Int?,
        var items : ArrayList<GetMovieList>
)