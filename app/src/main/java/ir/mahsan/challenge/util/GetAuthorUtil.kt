package ir.mahsan.challenge.util


private fun getDomainName(url: String): String {
    val domain = url.replace("http://", "").replace("https://", "").replace("www.", "")
    return domain.substring(0, domain.indexOf("."))
}

fun getAuthorValue(author: String?): String {
    if(author.isNullOrEmpty()) return "unknown"
    return if(author.contains("https://")) getDomainName(author) else author
}