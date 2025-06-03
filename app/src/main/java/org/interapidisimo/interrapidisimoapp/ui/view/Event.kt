package org.interapidisimo.interrapidisimoapp.ui.view

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /** Devuelve el contenido si aún no ha sido manejado, de lo contrario devuelve null. */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /** Devuelve el contenido incluso si ya fue manejado. */
    fun peekContent(): T = content
}