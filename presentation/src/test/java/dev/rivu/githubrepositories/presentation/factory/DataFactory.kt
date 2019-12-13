package dev.rivu.githubrepositories.presentation.factory

/**
 * Factory class for data instances
 */
object DataFactory {

    fun randomInt(): Int {
        return kotlin.random.Random.nextInt(0, 1000 + 1)
    }

    fun randomInt(start: Int, end: Int): Int {
        return kotlin.random.Random.nextInt(start, end)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun randomString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..500)
            .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun randomBoolean(): Boolean {
        return kotlin.random.Random.nextBoolean()
    }

}