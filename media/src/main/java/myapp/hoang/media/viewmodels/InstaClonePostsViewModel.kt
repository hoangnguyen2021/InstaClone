package myapp.hoang.media.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import myapp.hoang.media.models.CommentForm
import myapp.hoang.media.repositories.PostRepository
import myapp.hoang.settings.repositories.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class InstaClonePostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(InstaClonePostsUiState())
    val uiState = _uiState.asStateFlow()

    private var state: InstaClonePostsUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private var getAllPostsByUserJob: Job? = null
    private var likePostJob: Job? = null
    private var unlikePostJob: Job? = null
    private var getPostJob: Job? = null
    private var commentOnPostJob: Job? = null

    fun getAllPostsByUser() {
        getAllPostsByUserJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getAllPostsByUserJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val posts = postRepository.getPostsByUserId(userId)
                        state = state.copy(
                            posts = posts,
                            areLiked = posts.map { it.likes.contains(userPreferences.id) },
                            isLoading = false
                        )
                        Log.d(TAG, posts.toString())
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun likePost(postId: String) {
        likePostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        likePostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        postRepository.likePost(postId, userId)
                        getAllPostsByUser()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun unlikePost(postId: String) {
        unlikePostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        unlikePostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        postRepository.unlikePost(postId, userId)
                        getAllPostsByUser()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getPost(id: String) {
        getPostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        getPostJob = viewModelScope.launch {
            state = try {
                val post = postRepository.getPostById(id)
                Log.d(TAG, post.toString())
                state.copy(
                    post = post,
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun commentOnPost(postId: String, content: String) {
        commentOnPostJob?.cancel()

        state = state.copy(
            isLoading = true
        )
        commentOnPostJob = viewModelScope.launch {
            try {
                userPreferencesRepository.getUserPreferences().collect { userPreferences ->
                    val userId = userPreferences.id
                    if (userId == null) {
                        state = state.copy(
                            isLoading = false
                        )
                    } else {
                        val commentForm = CommentForm(
                            authorId = userId,
                            postId = postId,
                            content = content,
                            createdAt = Clock.System.now(),
                            lastEditedAt = Clock.System.now()
                        )
                        postRepository.commentOnPost(commentForm)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    companion object {
        private val TAG = InstaClonePostsViewModel::class.java.simpleName
    }
}