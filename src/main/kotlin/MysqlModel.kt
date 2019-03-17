package fr.iim.iwm.a5.matthieu.sukho

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class MysqlModel(url: String, user: String?, password: String?) : Model {

    val connectionPool = ConnectionPool("jdbc:mysql://localhost:3306/CMS", "root", "rootroot")

    override fun getArticleList(): List<Article> {
        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            connection.prepareStatement(" SELECT id, title FROM article ;").use {stmt ->
                val results = stmt.executeQuery()

                while(results.next()) {
                    articles += Article(results.getInt("id"), results.getString("title"))
                }
            }
        }
        return articles
    }

    override fun getArticle(id: Int): Article? {
        connectionPool.use { connection ->
            connection.prepareStatement(" SELECT * FROM article where id = ? ").use { stmt ->
                stmt.setInt(1, id)
                val results = stmt.executeQuery()
                val found = results.next()

                if (found) {
                    return Article(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("text")
                    )
                }
            }
        }
        return null
    }

    override fun getArticleComments (id: Int): List<Comment> {

        val comments = ArrayList<Comment>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM commentaire WHERE idArticle = ?").use { stmt ->
                stmt.setInt(1, id)

                val results = stmt.executeQuery()

                while (results.next()) {
                    val comment = Comment(
                        results.getInt("id"),
                        results.getInt("idArticle"),
                        results.getString("text")
                    )

                    comments.add(comment)
                }
            }
        }
        return comments
    }

    override fun createComment (id: Int, textComment: String): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO commentaire (idArticle, text) VALUES (?, ?);").use { stmt ->
                stmt.setInt(1, id)
                stmt.setString(2, textComment)

                return stmt.execute()
            }
        }
        return null
    }

    override fun authenticate (username: String, password: String): String? {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT username FROM users WHERE username = ? AND password = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.setString(2, password)

                val results = stmt.executeQuery()

                val found = results.next()

                if (found) {
                    return results.getString("username")
                }
            }
        }
        return null
    }
}