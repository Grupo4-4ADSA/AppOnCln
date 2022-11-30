import com.autG.oncln.dtos.responses.Empresa

data class LoginResponse(
    val idGestor: Int,
    val nome: String,
    val idPredio: Int,
)