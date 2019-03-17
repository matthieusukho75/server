package fr.iim.iwm.a5.matthieu.sukho

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode

interface ArticleController {
        fun startFM(id: Int): Any
        fun startHD(id: Int): Any
        fun addComment(id: Int, textComment: String): Any?
    }

