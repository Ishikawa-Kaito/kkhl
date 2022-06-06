package cn.kaito.kkhl.entity

data class Channel(
    val id: String,
    val is_category: Boolean,
    val level: Int,
    val limit_amount: Int,
    val master_id: String,
    val name: String,
    val parent_id: String,
    val type: Int
)