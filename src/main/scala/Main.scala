import module1.homework.LinearAlgebraOps


object Main {

  def main(args: Array[String]): Unit = {
    println("Hello, World!")
    val result = LinearAlgebraOps.sum(Array(1, 2, 3), Array(2, 3, 0))
    println(result.mkString("Array(", ", ", ")"))

    println(LinearAlgebraOps.axpy(2, Array(0, 1, 1), Array(2, 1, 2)).mkString("Array(", ", ", ")"))
  }
}

