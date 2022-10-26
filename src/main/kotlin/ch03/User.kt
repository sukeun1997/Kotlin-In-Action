package ch03

class User(private val address: String, private val name: String, private val id: Int) {

    fun saveUser() {
        validateBeforeSave()
    }

    fun User.validateBeforeSave() {
        fun validateUser(value: String, field: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Ex $value with $field")
            }
        }

        validateUser(name, "Name")
        validateUser(address, "Address")
    }
}