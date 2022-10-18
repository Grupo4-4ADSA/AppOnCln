import com.autG.oncln.dtos.responses.Empresa

data class LoginResponse(
    val empresa: Empresa,
    val idGestor: Int,
    val login: String,
    val nome: String,
    val senha: String
)