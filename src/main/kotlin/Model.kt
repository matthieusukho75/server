package fr.iim.iwm.a5.matthieu.sukho

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun getArticleComments(id: Int): Any?
    fun createComment(id: Int, textComment: String): Any?
    fun authenticate (username: String, user_password: String): String?
}