package fr.iim.iwm.a5.matthieu.sukho

import fr.iim.iwm.a5.matthieu.sukho.templates.articleTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model ) : ArticleController {

    override fun startFM(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        if (article != null) {
            return  FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments), "e")
        }
        return HttpStatusCode.NotFound
    }

    override fun startHD(id: Int): Any {
        val article = model.getArticle(id)
        if (article != null) {
            return HtmlContent {articleTemplate(article)}
        }
        return HttpStatusCode.NotFound
    }

    override fun addComment(id: Int, textComment: String): Any? {
        return  model.createComment(id, textComment)
    }
}