package com.henrimakela.mvvmaac.data.network.response

data class ChordResponse(
    val errMsg: String?,
    val chordName: String?,
    val enharmonicChordName: String?,
    val fingering: String?,
    val strings: String?,
    val tones: String?,
    val voicingID: String?
)