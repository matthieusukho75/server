package fr.iim.iwm.a5.matthieu.sukho

import fr.iim.iwm.a5.matthieu.sukho.templates.indexTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent

class ArticleListControllerImpl(private val model: Model) : ArticleListController {

    override fun startFM():FreeMarkerContent {
        val articles = model.getArticleList()
        return FreeMarkerContent("index.ftl", articles)
    }

    override fun startHD(): HtmlContent {
        val articles = model.getArticleList()
        return HtmlContent {indexTemplate(articles)}
    }
}