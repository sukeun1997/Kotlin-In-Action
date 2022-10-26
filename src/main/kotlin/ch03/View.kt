package ch03

open class View {
    open fun click() = println("View Click")
}

class Button : View() {

    override fun click() = println("Button Click")
}