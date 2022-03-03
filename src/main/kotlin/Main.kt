import java.util.*

fun main(args: Array<String>) {
    println("*********************************")
    println("***Bem vindo ao jogo da Forca!***")
    println("*********************************")

    val teclado = Scanner(System.`in`)
    val jogo = JogoDaForca()
    jogo.JogoDaForca("lista-palavras")
    jogo.iniciar()
    var letra: String
    do {
        println("palavra=" + jogo.getPalavra())
        println("dica=" + jogo.getDica())
        println("digite uma letra da palavra ")
        letra = teclado.nextLine()
        if (jogo.adivinhou(letra)) {
            println("voce acertou a letra $letra")
            println("total de acertos=" + jogo.acertos)
        } else {
            println("Voce errou a letra $letra")
            println("total de erros=" + jogo.erros)
            println("Penalidade: " + jogo.getPenalidade())
        }
    } while (!jogo.terminou())
    teclado.close()
    println("game over")
    println("resultado final=" + jogo.getResultado())
}