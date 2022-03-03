import java.io.File
import java.util.*

class JogoDaForca(){

    var N: Int = 0
    lateinit var palavras: Array<String?>
    lateinit var dicas: Array<String?>
    private lateinit var palavra: String
    var indice = -1
    var acertos: Int = 0
    var erros: Int = 0
    val penalidades = arrayOf("perna", "perna", "braço", "braço", "tronco", "cabeça")

    fun JogoDaForca(nomearquivo: String){


        try {
            val arquivo = Scanner(File(nomearquivo))
            N = arquivo.nextLine().toInt()
            indice = Random().nextInt(N)
            palavras = arrayOfNulls(N)
            dicas = arrayOfNulls(N)
            var linha: String
            var counter = 0
            while (arquivo.hasNextLine()) {
                linha = arquivo.nextLine()
                val partes = linha.split(";".toRegex()).toTypedArray()
                palavras[counter] = partes[0]
                dicas[counter] = partes[1]
                //String[] letras = palavra.split("");
                counter++
            }
            arquivo.close()
        } catch (e: Exception) {
            throw Exception("Arquivo inexistente")
        }
    }

    fun iniciar() {
        palavra = "*".repeat(palavras[indice]!!.length)
        val palavra2 = palavra.toCharArray()
        palavra = String(palavra2)
    }

    fun adivinhou(letra: String): Boolean {
        var letra = letra
        letra = letra.uppercase(Locale.getDefault())
        val palavraReal = palavras[indice]!!.toUpperCase()
        return if (palavraReal.contains(letra)) {
            acertos++
            val palavraArray = palavra.toCharArray()
            val palavraRealArray = palavraReal.toCharArray()
            for (i in 0 until palavraReal.length) {
                if (letra[0] == palavraRealArray[i]) {
                    palavraArray[i] = letra[0]
                }
            }
            palavra = String(palavraArray)
            true
        } else {
            erros++
            false
        }
    }
    fun terminou(): Boolean {
        return if (erros == 6 || palavras[indice].equals(palavra)) {
            true
        } else {
            false
        }
    }
    fun getPalavra(): String? {
        return palavra
    }

    fun getDica(): String? {
        return dicas[indice]
    }


    fun getPenalidade(): String? {
        return penalidades[getErros() - 1]
    }


    @JvmName("getAcertos1")
    fun getAcertos(): Int {
        return acertos
    }

    @JvmName("getErros1")
    fun getErros(): Int {
        return erros
    }

    fun getResultado(): String? {
        return if (acertos > erros) {
            "Voc� ganhou"
        } else {
            "Voc� foi enforcado"
        }
    }
}