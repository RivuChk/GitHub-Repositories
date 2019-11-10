package dev.rivu.githubrepositories.remote.model

data class TrendingProjectsResponse(
    val author: String, // ansible
    val name: String, // ansible
    val avatar: String, // https://github.com/ansible.png
    val url: String, // https://github.com/ansible/ansible
    val description: String, // Ansible is a radically simple IT automation platform ...
    val language: String, // Python
    val languageColor: String, // #3572A5
    val stars: Int, // 40218
    val forks: Int, // 17352
    val currentPeriodStars: Int, // 51
    val builtBy: List<BuiltBy>
) {
    data class BuiltBy(
        val username: String, // mattclay
        val href: String, // https://github.com/mattclay
        val avatar: String // https://avatars2.githubusercontent.com/u/806360
    )
}