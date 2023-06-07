package ucv.radioimagenologia.domain

data class Projection(
    val name: String,
    val images: List<String>,
    val indications: String,
    val positioning: List<String>,
    val technicalFactors: String,
    val qualityCriteria: List<String>
)