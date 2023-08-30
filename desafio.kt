enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario (val nome: String)

class GerenciadorUsuarios{
    val usuario: MutableList<Usuario> = mutableListOf(Usuario("Aluno1"), Usuario("Aluno2"),
        Usuario("Aluno3"))

    fun addNovoUsuario (novoUsuario: Usuario){
        usuario.add(novoUsuario)
    }
}

data class ConteudoEducacional(val nomeConteudoEducacional: String, val duracao: Int = 60, val nivel: Nivel)

class GerenciadorConteudos {
    companion object {
        val conteudoEducacional: MutableList<ConteudoEducacional> =
            mutableListOf(
                ConteudoEducacional("Versionamento de Código", 120, Nivel.BASICO),
                ConteudoEducacional("Linguagem Kotlin", 180, Nivel.INTERMEDIARIO),
                ConteudoEducacional("Desenvolvimento Mobile", 240, Nivel.DIFICIL)
            )
    }

    fun addNovoConteudo (novoConteudo: ConteudoEducacional){
        conteudoEducacional.add(novoConteudo)
    }

}

data class Formacao(val nomeFormacao: String, val gerenciadorUsuarios: GerenciadorUsuarios, val gerenciadorconteudos: GerenciadorConteudos) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        inscritos.addAll(usuarios)
    }
    fun mostrarInscritos() {
        println("Inscritos na formação $nomeFormacao:")
        inscritos.forEach { println(it.nome) }
    }
}

fun main() {

    val gerenciadorUsuarios = GerenciadorUsuarios()
    val gerenciadorConteudos = GerenciadorConteudos()

    gerenciadorUsuarios.addNovoUsuario(Usuario("Aluno4"))

    val novoConteudo = ConteudoEducacional("Novo Conteudo", 60, Nivel.BASICO)
    gerenciadorConteudos.addNovoConteudo(novoConteudo)
    
   
    println("Usuarios: ")
    gerenciadorUsuarios.usuario.forEach { println(it.nome)}

    println("Conteúdos:")
    GerenciadorConteudos.conteudoEducacional.forEach {
        println("Nome: ${it.nomeConteudoEducacional}, Duração: ${it.duracao}, Nível: ${it.nivel}")
    }
    val formacao = Formacao("Curso de Programação", gerenciadorUsuarios, gerenciadorConteudos)
    formacao.matricular(Usuario("Aluno4"))
    formacao.matricular(Usuario("Aluno1"))
    formacao.mostrarInscritos()
}