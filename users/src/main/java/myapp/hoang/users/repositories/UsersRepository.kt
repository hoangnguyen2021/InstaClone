package myapp.hoang.users.repositories

import myapp.hoang.core.models.InstaCloneUser

interface UsersRepository {
    suspend fun getUserById(id: String): InstaCloneUser
    suspend fun getCommentorsByPostId(postId: String): List<InstaCloneUser>
}