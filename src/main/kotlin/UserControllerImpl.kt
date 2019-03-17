package fr.iim.iwm.a5.matthieu.sukho

import fr.iim.iwm.a5.matthieu.sukho.templates.articleTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode
import io.ktor.auth.UserIdPrincipal

 class UserControllerImpl(private val model: Model): UserController {
     override fun login(username: String, password: String): String? {
         return model.authenticate(username, password)
     }

     override fun loginForm(): FreeMarkerContent {
         return FreeMarkerContent("login.ftl", null, "e")
     }

 }