package ir.jetpack.challenge.util


private fun getDomainName(url: String): String {
    val domain = url
        .replace("http://", "")
        .replace("https://", "")
        .replace("www.", "")

    return domain.substring(0, domain.indexOf("."))
}

private fun getDomainNameFromEmail(email: String): String {
    val emailStr = email.replace("www.", "");

    return emailStr.substring(emailStr.indexOf("@") + 1, emailStr.indexOf("."))
}

fun getAuthorValue(author: String?): String {
    if(author.isNullOrEmpty()) return "unknown"

    if(author.contains("@")) return getDomainNameFromEmail(author)
    else if(author.contains("https://")) return getDomainName(author)

    return author
}