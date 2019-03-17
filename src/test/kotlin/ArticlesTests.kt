package fr.iim.iwm.a5.matthieu.sukho

import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FakeModel : Model {
    override fun getArticleList(): List<Article> {
        return listOf(Article(42, "titre"))
    }

    override fun getArticle(id: Int): Article? {
        return if (id == 42)
            Article(42, "titre", "text")
        else
            null
    }
}

class  ArticlesTests {
    @Test
    fun testArticleFound(){
        val controller = ArticleControllerImpl(FakeModel())

        val result = controller.startHD(21)

        assertTrue(result is HtmlContent)
    }

    @Test
    fun testArticleNotFound() {
        val controller = ArticleControllerImpl(FakeModel())

        val result = controller.startHD(21)

        assertEquals(HttpStatusCode.NotFound, result)
    }
}