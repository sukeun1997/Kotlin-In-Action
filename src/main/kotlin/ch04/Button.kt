package ch04

open class Button : Clickable , Focusable{

    override fun click() {
        println("Button CLicked")
    }

    final override fun showOff() {
        super<Focusable>.showOff()
        super<Clickable>.showOff()
    }
}