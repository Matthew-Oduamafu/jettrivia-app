package mattie.freelancer.jettrivia.data

data class DataOrExceptions<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading:Boolean? = null,
    var e:E? = null
)
