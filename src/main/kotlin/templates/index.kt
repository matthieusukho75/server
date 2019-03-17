package fr.iim.iwm.a5.matthieu.sukho.templates

import fr.iim.iwm.a5.matthieu.sukho.Article
import kotlinx.html.*


fun HTML.indexTemplate(articles: List<Article>) {
    head {
        title("Liste des articles")
    }
    body {
        articles.forEach {
            p {
                a(href= "article/${it.id}") {
                    text(it.title)
                }
            }
        }
    }
}