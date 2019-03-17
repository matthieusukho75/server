package fr.iim.iwm.a5.matthieu.sukho

import fr.iim.iwm.a5.matthieu.sukho.MysqlModel
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ModelTests {

    val model = MysqlModel("jdbc:h2:mem:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use { connection ->
            connection.prepareStatement(
                """
    DROP TABLE IF EXISTS `article`;
    CREATE TABLE `article`(
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `title` varchar(255) DEFAULT '',
   `text` text,
    PRIMARY KEY (`id`)
);
INSERT INTO `article` (`id`, `title`, `text`)
VALUES
	(1,'Michel','Je suis un michel'),
	(2,'George','Je suis un George');
            """
            ).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testArticleInDB() {
        val article = model.getArticle(1)
        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("Michel", article.title)
        assertEquals("Je suis un michel", article.text)
    }

    @Test
    fun testArticleNotInDB() {
        val article = model.getArticle(4)
        assertNull(article)
    }
}