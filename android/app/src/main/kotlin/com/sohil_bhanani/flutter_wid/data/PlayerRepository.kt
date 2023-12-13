package com.sohil_bhanani.flutter_wid.data


class PlayerRepository(

) {
    fun getData(): PlayerItem =
        PlayerItem(
            title = "TitleRepo",
            artist = "ArtistRepo",
            albumUrl = "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_1280.jpg",
        )
}