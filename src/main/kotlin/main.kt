package fr.iim.iwm.a5.matthieu.sukho


import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.auth.*
import io.ktor.sessions.*


class App

data class IndexData(val articles: List<Article>)

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleController,
    userController: UserControllerImpl
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }


    install(Sessions) {
        cookie<Session>("logged")
    }

    install(Authentication) {
        form("login") {
            userParamName = "username"
            passwordParamName = "password"
            challenge = FormAuthChallenge.Redirect { "/login" }
            validate { credentials ->

                val username = userController.login(credentials.name, credentials.password)

                if (username != null) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }

        routing {
            get("/") {
                val content = articleListController.startHD()
                call.respond(content)
            }

            get("/article/{id}") {
                val articleId = call.parameters["id"]!!.toInt()
                val content = articleController.startHD(articleId)
                call.respond(content)
            }

            post("/article/{id}") {
                val id = call.parameters["id"]!!.toInt()

                val postParameters: Parameters = call.receiveParameters()

                val text = postParameters["comment"]

                if (text !== null) {
                    articleController.addComment(id, text)
                }
                call.respondRedirect("/article/$id")
            }

            get("/login") {
                val template = userController.loginForm()

                call.respond(template)
            }

            authenticate("login") {
                post("/login") {
                    val principal = call.authentication.principal<UserIdPrincipal>()
                    call.sessions.set(Session(principal!!.name))
                    call.respondRedirect("/user")
                }
            }


            static("/assets") {
                resources("assets")
            }
        }
    }

}

fun main(args: Array<String>) {

    val model = MysqlModel("jdbc:mysql://localhost:3306/CMS", "root", "rootroot")
    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val userController = UserControllerImpl(model)


    embeddedServer(Netty, 8080) {
        cmsApp(articleListController, articleController, userController)
    }.start(true)

}
