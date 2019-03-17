package fr.iim.iwm.a5.matthieu.sukho

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent

interface ArticleListController {
    fun startFM(): FreeMarkerContent
    fun startHD(): HtmlContent
}