package com.example.movieapp.domain.model


data class MovieDetailResult(
    val movieDetail: MovieDetail? = null,
    val errorMessage: String? = null,
    val status: Status = Status.LOADING
) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class ProductionCompany(
        val id: Int,
        val logoPath: String?,
        val name: String,
        val originCountry: String
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String
    )

    data class SpokenLanguage(
        val englishName: String,
        val iso6391: String,
        val name: String
    )
}
