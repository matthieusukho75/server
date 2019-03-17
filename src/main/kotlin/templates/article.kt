package fr.iim.iwm.a5.matthieu.sukho.templates

import fr.iim.iwm.a5.matthieu.sukho.Article
import kotlinx.html.*


fun HTML.articleTemplate(article: Article) {
    head {
        title("Mon article")
    }
    body {
        h1 {
            text(article.title)
        }
        p {
            text(article.text!!)
        }
    }
}