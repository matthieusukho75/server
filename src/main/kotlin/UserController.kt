package fr.iim.iwm.a5.matthieu.sukho

import io.ktor.freemarker.FreeMarkerContent

interface UserController {
    fun login(username: String, password: String): String?
    fun loginForm(): FreeMarkerContent
}