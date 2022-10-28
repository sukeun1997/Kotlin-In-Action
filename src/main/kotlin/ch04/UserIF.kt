package ch04

interface UserIF {

    val nickname : String
}

class PrivateUser(val email : String) : UserIF {

    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : UserIF {

    override val nickname = getFacebookName()

    private fun getFacebookName(): String = accountId.toString().plus("FaceBook")
}

class OverrideUser(override val nickname: String) : UserIF {

}