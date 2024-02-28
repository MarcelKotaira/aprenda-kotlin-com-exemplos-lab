// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel {
    BASICO,
    INTERMEDIARIO,
    AVANCADO
}

data class Usuario(val nome: String, var email: String)

data class ConteudoEducacional(
        val nome: String,
        val duracao: Int = 60,
        val nivel: Nivel = Nivel.BASICO
)

data class Formacao(
        val nome: String,
        val conteudos: List<ConteudoEducacional>,
        val nivel: Nivel = Nivel.BASICO
) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario): String {
        // TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de
        // $inscritos).")
        val (nome, email) = usuario
        if (nome.isBlank()) return "Nome inválido!"
        if (email.isBlank()) return "Email inválido!"
        if (inscritos.contains(usuario) || inscritos.firstOrNull { it.email == email } !== null)
                return "Usuário $nome já cadastrado ou e-mail $email já utilizado"
        inscritos.add(usuario)
        return "Usuario $nome matriculado como sucesso"
    }

    fun exibirFormacaoDetalhado(): String {
        var saida = "FORMAÇÃO: $nome - Nível $nivel\n"
        saida += "=CONTEUDO=\n"
        if (conteudos.isEmpty()) {
            saida += "Sem Conteudo"
        } else {
            for (f in conteudos) saida += "${f.nome} - ${(f.duracao / 60)}hrs (${f.nivel})\n"
        }
        saida += "=USUARIOS=\n"
        if (inscritos.isEmpty()) {
            saida += "Sem Usuario\n"
        } else {
            for (u in inscritos) saida += "${u.nome} - ${u.email}\n"
        }
        return saida
    }
}

fun main() {
    // TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de
    // evoluí-las.")
    // TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em
    // questão.")

    val usuarios = criarListaUsuario()
    println("Criando formação e conteudo...")
    val formacao = Formacao("DIO - Kotlin", criarListaConteudos())
    println(formacao.exibirFormacaoDetalhado())
    println("Formação criado com sucesso!")
    println("Matriculando Usuários...")
    for (user in usuarios) {
        println(formacao.matricular(user))
    }
    println("\nDetalhes da Formação")
    println(formacao.exibirFormacaoDetalhado())
}

// Cria uma lista de Usuários para TESTE
fun criarListaUsuario(): List<Usuario> {
    return mutableListOf(
            Usuario("Bia", "bia@email.com"),
            Usuario("Paulo", "paulo@email.com"),
            Usuario("Andre", "andre@email.com"),
            Usuario("Diana", "diana@email.com"),
    )
}

// Cria uma Lista de Conteúados para TESTE
fun criarListaConteudos(): List<ConteudoEducacional> {
    return mutableListOf(
            ConteudoEducacional(nome = "Conteudo A", nivel = Nivel.BASICO),
            ConteudoEducacional("Conteudo B", 120, Nivel.BASICO),
            ConteudoEducacional("Conteudo C", nivel = Nivel.INTERMEDIARIO),
            ConteudoEducacional("Conteudo D", 90, nivel = Nivel.AVANCADO),
    )
}
