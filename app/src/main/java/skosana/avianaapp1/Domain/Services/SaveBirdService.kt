package skosana.avianaapp1.Domain.Services

import com.google.android.gms.tasks.Task
import skosana.avianaapp1.Domain.Models.UserBirds.UserBirdSighting

interface SaveBirdService {

    suspend fun addTodo(bird: UserBirdSighting): Task<Void>

}